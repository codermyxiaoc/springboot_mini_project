package cn.wedstory.daoMain;

import lombok.Data;

@Data
public class User {
  private Integer id;
  private String username;
  private String password;
  private String head_pic;

  public User(Integer id, String username, String password, String head_pic) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.head_pic = head_pic;
  }

  public User(String username, String password) {
    this.username = username;
    this.password = password;
  }

  public User(String username, String password, String head_pic) {
    this.username = username;
    this.password = password;
    this.head_pic = head_pic;
  }

  public User() {
  }
}
