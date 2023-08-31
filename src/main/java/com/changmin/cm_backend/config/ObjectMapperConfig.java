package com.changmin.cm_backend.config;

import com.changmin.cm_backend.component.ObjectMapperFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class ObjectMapperConfig {
  @Bean
  public ObjectMapper getCommonObjectMapper() {
    return ObjectMapperFactory.create();
  }
}
