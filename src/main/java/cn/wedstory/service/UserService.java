package cn.wedstory.service;

import cn.wedstory.daoMain.User;

import java.util.List;

public interface UserService {
  User getUserNameOrPassword(User user);
  List<User> getUserAll();

  Boolean addUser(User user);

  Boolean deleteUser(Integer id);

  Boolean updateUser(User user);

  List<User> searchUser(String username);

  User getByUserName(String username);
}
