package cn.wedstory.service;

import cn.wedstory.daoMain.User;
import cn.wedstory.service.serviceImpl.UserServiceImpl;
import com.alibaba.fastjson2.JSON;
import io.jsonwebtoken.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;


@SpringBootTest
public class UserServiceTest {

  @Autowired
  private UserServiceImpl userMapper;
  @Test
  void getUserAll() {
    List<User> userAll = userMapper.getUserAll();
    System.out.println(userAll);
  }
  @Test
  void get() {
    User user = userMapper.getUserNameOrPassword(new User("czl", "123456"));
    if (user != null) {
      String userJson = JSON.toJSONString(user);
      JwtBuilder jwtBuilder = Jwts.builder();
      String token = jwtBuilder.setSubject(userJson) //设置用户数据
              .setIssuedAt(new Date()) //设置jwt生成时间
              .setId(user.getId() + "") //设置id为token id
              .claim("user", userJson) //通过map传值
              .setExpiration(new Date(System.currentTimeMillis() + 500000)) //设置token有效期
              .signWith(SignatureAlgorithm.HS256, "chenzhilong") //设置token加密方式和密码
              .compact();
      System.out.println( "Bearer " + token);
    } else {
      System.out.println("登入失败");
    }
  }
  @Test
  void tokenParameter() {
    String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ7XCJoZWFkX3BpY1wiOlwiaHR0cDovLzEyNzAuMC4xOjgwODEvbmV3dXNlci5qcGdcIixcImlkXCI6MSxcInBhc3N3b3JkXCI6XCIxMjM0NTZcIixcInVzZXJuYW1lXCI6XCJjemxcIn0iLCJpYXQiOjE2NzQ0NTYwNDMsImp0aSI6IjEiLCJ1c2VyIjoie1wiaGVhZF9waWNcIjpcImh0dHA6Ly8xMjcwLjAuMTo4MDgxL25ld3VzZXIuanBnXCIsXCJpZFwiOjEsXCJwYXNzd29yZFwiOlwiMTIzNDU2XCIsXCJ1c2VybmFtZVwiOlwiY3psXCJ9IiwiZXhwIjoxNjc0NDU2NTQzfQ.fQKHAkooSNU2kcYhbnmDfFREpDS1mivMmC5U0-D6JC8";
    JwtParser jwtParser = Jwts.parser();
    jwtParser.setSigningKey("chenzhilong");
    Jws<Claims> claims = jwtParser.parseClaimsJws(token);
    Claims claim = claims.getBody();
    String id = claim.getId();
    System.out.println(id);
    String user = (String)claim.get("user");
    User user2 = JSON.parseObject(user, User.class);
    System.out.println(user2.toString());
  }
  @Test
  void add() {
    userMapper.addUser(new User("zyh","123456","htp"));
  }
  @Test
  void del() {
    userMapper.deleteUser(2);
  }
  @Test
  void upd() {
    User user = new User();
    user.setId(1);
    user.setUsername("czl");
    user.setPassword("1234567");
    userMapper.updateUser(user);
  }
  @Test
  void search() {

  }
}
