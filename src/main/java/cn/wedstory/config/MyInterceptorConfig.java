package cn.wedstory.config;

import cn.wedstory.interceptor.JwtInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class MyInterceptorConfig implements WebMvcConfigurer {
/*
* 权限拦截器
* */
  @Autowired
  private JwtInterceptor jwtInterceptor;
  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    List<String> pathPatterns=new ArrayList<>();
    pathPatterns.add("/user/**");
    pathPatterns.add("/brand/**");
    List<String> excludePathPatterns=new ArrayList<>();
    excludePathPatterns.add("/auto/**");
    registry.addInterceptor(jwtInterceptor) //添加拦截器
            .addPathPatterns(pathPatterns) //添加拦截url
            .excludePathPatterns(excludePathPatterns); //添加不拦截url
  }
}
