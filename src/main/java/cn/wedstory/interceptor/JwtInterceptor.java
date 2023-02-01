package cn.wedstory.interceptor;

import cn.wedstory.daoMain.User;
import cn.wedstory.utils.Result;
import com.alibaba.fastjson2.JSON;
import io.jsonwebtoken.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;
import java.io.PrintWriter;

/*
* 注册权限拦截器
* */
@Component
@CrossOrigin
public class JwtInterceptor implements HandlerInterceptor {
  @Value("${token.jwtKey}")
  private String jwtkey;
  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    String token = request.getHeader("Authorization");
    if (token!=null) {
      try {
        JwtParser jwtParser = Jwts.parser();
        jwtParser.setSigningKey(this.jwtkey);
        token = token.split(" ")[1];
        Jws<Claims> claimsJws = jwtParser.parseClaimsJws(token);
        Claims body = claimsJws.getBody();
        String user = (String)body.get("user");
        User parseUser = JSON.parseObject(user, User.class);
        request.setAttribute("user", parseUser);
        return true;
      }catch (ExpiredJwtException e){
        doResponse(response,"Token已过期，请重新登陆！");
        return false;
      }catch (UnsupportedJwtException e){
        doResponse(response,"登入身份验证失败，请重新登入！");
        return false;
      }catch (Exception e){
        doResponse(response,"登入身份验证失败，请重新登入！");
        return false;
      }
    }
    doResponse(response,"登入身份验证失败，请重新登陆！");
    return false;
  }
  public void doResponse(HttpServletResponse response,String info) throws IOException {
    response.setContentType("application/json");
    response.setCharacterEncoding("utf-8");

    PrintWriter writer = response.getWriter();
    Result result = new Result(201, info, null);
    String json = JSON.toJSONString(result);
    writer.write(json);
    writer.flush();
    writer.close();
  }

}
