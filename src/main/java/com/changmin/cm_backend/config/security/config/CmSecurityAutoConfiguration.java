package com.changmin.cm_backend.config.security.config;


import javax.annotation.Resource;

import com.changmin.cm_backend.config.security.AuthenticationEntryPointImpl;
import com.changmin.cm_backend.config.security.TokenAuthenticationFilter;
import com.changmin.cm_backend.config.security.core.service.SecurityFrameworkService;
import com.changmin.cm_backend.config.security.core.service.SecurityFrameworkServiceImpl;
import com.changmin.cm_backend.service.PermissionService;
import com.changmin.cm_backend.service.TokenService;
import org.springframework.beans.factory.config.MethodInvokingFactoryBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;

/**
 * Spring Security 自动配置类，主要用于相关组件的配置
 *
 * <p>注意，不能和 {@link CmWebSecurityConfigurerAdapter} 用一个，原因是会导致初始化报错。 参见
 * https://stackoverflow.com/questions/53847050/spring-boot-delegatebuilder-cannot-be-null-on-autowiring-authenticationmanager
 * 文档。
 *
 * @author changmin源码
 */
@Configuration
@EnableConfigurationProperties(SecurityProperties.class)
public class CmSecurityAutoConfiguration {

  @Resource private TokenService tokenService;

  @Resource private SecurityProperties securityProperties;

  /** 认证失败处理类 Bean */
  @Bean
  public AuthenticationEntryPoint authenticationEntryPoint() {
    return new AuthenticationEntryPointImpl();
  }

  /** 权限不够处理器 Bean */
  @Bean
  public AccessDeniedHandler accessDeniedHandler() {
    return new AccessDeniedHandlerImpl();
  }

  /**
   * Spring Security 加密器 考虑到安全性，这里采用 BCryptPasswordEncoder 加密器
   *
   * @see <a href="http://stackabuse.com/password-encoding-with-spring-security/">Password Encoding
   *     with Spring Security</a>
   */
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  /** Token 认证过滤器 Bean */
  @Bean
  public TokenAuthenticationFilter authenticationTokenFilter() {
    return new TokenAuthenticationFilter(tokenService, securityProperties);
  }

  @Bean("ss") // 使用 Spring Security 的缩写，方便使用
  public SecurityFrameworkService securityFrameworkService(PermissionService permissionService) {
    // todo ouzhenxiong 传入api
    //    PermissionApi permissionApi
    return new SecurityFrameworkServiceImpl(permissionService);
  }
}
