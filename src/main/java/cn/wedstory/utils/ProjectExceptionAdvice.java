package cn.wedstory.utils;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ProjectExceptionAdvice {
  @ExceptionHandler
  public Result toException(Exception e) {
    e.printStackTrace();
    return new Result(201,e.getMessage(),null);
  }
}
