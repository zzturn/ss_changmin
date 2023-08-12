package com.changmin.cm_backend.config.req;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

public class RequestResponseLoggingBean {

  @Bean
  public FilterRegistrationBean<RequestResponseLoggingFilter> loggingFilter() {
    FilterRegistrationBean<RequestResponseLoggingFilter> registrationBean =
        new FilterRegistrationBean<>();

    registrationBean.setFilter(new RequestResponseLoggingFilter());
    registrationBean.addUrlPatterns("/**");

    return registrationBean;
  }
}
