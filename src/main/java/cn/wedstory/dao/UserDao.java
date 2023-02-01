package cn.wedstory.dao;

import cn.wedstory.daoMain.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserDao {
  @Select("select * from tb_users where username = #{username} and password = #{password}")
  User getUserNameOrPassword( User user);
  @Select("select * from tb_users")
  List<User> getUserAll();
  Integer addUser(User user);

  @Delete("delete from tb_users where id = #{id};")
  Integer deleteUser(Integer id);

  Integer updateUser(User user);

  @Select("select * from tb_users where username like #{username};")

  List<User> searchUser(String username);

  @Select("select * from tb_users where username = #{username};")
  User getByUserName(String username);
}
