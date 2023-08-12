package com.changmin.cm_backend.config;

import com.changmin.cm_backend.config.auth.LoginRequiredInterceptor;
import com.changmin.cm_backend.config.auth.RoleRequiredInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @author ren
 * @since 2021/7/6
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
  @Autowired
  LoginRequiredInterceptor loginRequiredInterceptor;
  @Autowired
  RoleRequiredInterceptor roleRequiredInterceptor;

  @Override
  public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {}

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    // 所有接口检测是否有login required注解
    registry
        .addInterceptor(loginRequiredInterceptor)
        .excludePathPatterns("/error", "/swagger-ui.html", "/swagger-resources/**")
        .addPathPatterns("/**");
    registry
        .addInterceptor(roleRequiredInterceptor)
        .excludePathPatterns("/error", "/swagger-ui.html", "/swagger-resources/**")
        .addPathPatterns("/**");
  }

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry
        .addMapping("/**")
        .allowedMethods("*")
        .allowedOriginPatterns("*")
        .allowedHeaders("*")
        .allowCredentials(true);
  }
}
