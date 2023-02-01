package cn.wedstory.Controller;

import cn.wedstory.daoMain.User;
import cn.wedstory.service.serviceImpl.UserServiceImpl;
import cn.wedstory.utils.Result;
import com.alibaba.fastjson2.JSON;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/auth")
public class LoginController {
  @Autowired
  private UserServiceImpl userMapper;

  @Value("${token.jwtKey}")
  private String jwtkey;
  @Value("${token.validity}")
  private Integer validity;
  @PostMapping("/login")
  public Result Login(@RequestBody User reqUser) {
    User user = userMapper.getUserNameOrPassword(reqUser);
    if(user == null) {
      return new Result(201,"登入失败", null);
    } else {
      user.setPassword("");
      String userJson = JSON.toJSONString(user);
      JwtBuilder jwtBuilder = Jwts.builder();
      String token = jwtBuilder
              .setIssuedAt(new Date()) //设置jwt生成时间
              .setId(UUID.randomUUID().toString()) //设置id为token id
              .claim("user", userJson) //通过map传值
              .setExpiration(new Date(System.currentTimeMillis() + this.validity)) //设置token有效期
              .signWith(SignatureAlgorithm.HS256, this.jwtkey) //设置token加密方式和密码
              .compact();
      return new Result(200,"登入成功","Bearer " + token);
    }
  }
}
