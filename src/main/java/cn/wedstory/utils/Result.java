package cn.wedstory.utils;

import lombok.Data;

@Data
public class Result {
  private Integer code;
  private String message;
  private Object data;

  public Result(Integer code, String message, Object data) {
    this.code = code;
    this.message = message;
    this.data = data;
  }
}
