package cn.wedstory.Controller;

import cn.wedstory.daoMain.User;
import cn.wedstory.service.serviceImpl.UserServiceImpl;
import cn.wedstory.utils.Result;
import cn.wedstory.utils.Search;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.http.HttpRequest;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {
  @Autowired
  private UserServiceImpl userMapper;
  @Value("${file.url}")
  private String baseUrl;
  @Value("${file.path}")
  private String basePath;
  @GetMapping("/getUserAll")
  public Result getUserAll() {
    List<User> userAll = userMapper.getUserAll();
    return new Result(200,"查询成功",userAll);
  }
  @PostMapping("/addUser")
  public Result addUser(@RequestBody User user) {
    if(user.getPassword() == null || user.getPassword() == null) {
      throw new RuntimeException("用户名和密码不能为空");
    }
    if (userMapper.getByUserName(user.getUsername()) != null) {
      throw new RuntimeException("该用户名重复");
    }
    Boolean status = userMapper.addUser(user);
    if(status) {
      return new Result(200, "添加成功", null);
    } else {
      return new Result(201, "添加失败", null);
    }
  }
  @GetMapping("/userInfo")
  public Result getUserInfo(HttpServletRequest request) {
    User user = (User)request.getAttribute("user");
    User byUserName = userMapper.getByUserName(user.getUsername());

    return new Result(200,"查询成功", byUserName);
  }
  @GetMapping ("/deleteUser/{id}")
  public Result deleteUser(@PathVariable Integer id) {
    Boolean status = userMapper.deleteUser(id);
    if(status) {
      return new Result(200,"删除成功",null);
    } else {
      return new Result(201,"删除失败",null);
    }
  }

  @PostMapping("/updateUser")
  public Result updateUser(@RequestBody User user) {
    System.out.println(user.toString());
    if(user.getId() == null) {
      throw new RuntimeException("id不能为空");
    }
    Boolean status = userMapper.updateUser(user);
    if(status) {
      return new Result(200,"修改成功",null);
    } else {
      return new Result(201,"修改失败",null);
    }
  }
  @PostMapping("/searchUser")
 public Result searchUser(@RequestBody Search search) {
    String keyword = "%" + search.getKeyword() + "%";
    List<User> users = userMapper.searchUser(keyword);
    return new Result(201,"查询成功",users);
 }
 @PostMapping("/changeHeadPortrait")
 public Result changeHeadPortrait(@PathParam("img") MultipartFile file, HttpServletRequest request) throws IOException {
   try {
     User user = (User)request.getAttribute("user");
     String originalFilename = file.getOriginalFilename();
     String substring = originalFilename.substring(originalFilename.lastIndexOf("."));
     String fileName = UUID.randomUUID().toString() + substring;
     File path = new File(this.basePath,fileName);
     file.transferTo(path);
     String url = this.baseUrl + fileName;

     User byUserName = userMapper.getByUserName(user.getUsername());
     String head_pic = byUserName.getHead_pic();
     URL newUrl = new URL(head_pic);
     String delPath = this.basePath + newUrl.getFile().replace("/", "\\");
     File delFile = new File(delPath);
     delFile.delete();

     user.setHead_pic(url);
     Boolean status = userMapper.updateUser(user);
     if(status) {
       return new Result(200, "修改成功", user);
     } else {
       return new Result(201, "修改失败", null);
     }
   } catch (Exception e) {
     return new Result(201, "修改失败", null);
   }
 }
}
