package com.changmin.cm_backend;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@MapperScan(value = "com.changmin.cm_backend.mapper")
@SpringBootApplication
public class CmBackendApplication {

  public static void main(String[] args) {
    SpringApplication.run(CmBackendApplication.class, args);
    log.info("=================================== SUCCESS ===================================");
  }
}
