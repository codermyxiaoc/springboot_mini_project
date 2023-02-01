package cn.wedstory.service.serviceImpl;

import cn.wedstory.daoMain.User;
import cn.wedstory.dao.UserDao;
import cn.wedstory.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
  @Autowired
  private UserDao userDao;
  @Override
  public User getUserNameOrPassword(User user) {
    return userDao.getUserNameOrPassword(user);
  }
  @Override
  public List<User> getUserAll() {
    return  userDao.getUserAll();
  }

  @Override
  public Boolean addUser(User user) {
    Integer integer = userDao.addUser(user);
    return integer.intValue() == 1;
  }

  @Override
  public Boolean deleteUser(Integer id) {
    Integer integer = userDao.deleteUser(id);
    return integer.intValue() == 1;
  }

  @Override
  public Boolean updateUser(User user) {
    Integer integer = userDao.updateUser(user);
    return integer.intValue() == 1;
  }

  @Override
  public List<User> searchUser(String username) {
    return userDao.searchUser(username);
  }

  @Override
  public User getByUserName(String username) {
    return userDao.getByUserName(username);
  }

}
