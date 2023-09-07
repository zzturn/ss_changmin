package com.changmin.cm_backend.config.swagger;

import com.changmin.cm_backend.config.security.config.SecurityProperties;
import java.util.Arrays;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
  @Value("${swagger.enable}")
  private Boolean enable;

  @Resource private Environment environment;

  @Resource private SecurityProperties securityProperties;

  @Bean
  public Docket createRestApi() {
    Docket docket =
        new Docket(DocumentationType.SWAGGER_2)
            .apiInfo(apiInfo())
            // 是否开启 (true 开启  false隐藏。生产环境建议隐藏)
            .enable(enable)
            .select()
            // 扫描的路径包,设置basePackage会将包下的所有被@Api标记类的所有方法作为api
            .apis(RequestHandlerSelectors.basePackage("com.changmin.cm_backend.controller"))
            // 指定路径处理PathSelectors.any()代表所有的路径
            .paths(PathSelectors.any())
            .build()
            .securityContexts(Arrays.asList(securityContext()))
            .securitySchemes(
                Arrays.asList(new ApiKey("token", securityProperties.getTokenHeader(), "")));
    // 解决dev环境下无法直接使用swagger的问题
    if (Arrays.asList(environment.getActiveProfiles()).contains("prod")) {
      docket.pathMapping("/backend");
    }
    return docket;
  }

  private ApiInfo apiInfo() {
    return new ApiInfoBuilder()
        // 设置文档标题(API名称)
        .title("Swagger2接口规范")
        // 文档描述
        .description("接口说明")
        // 版本号
        .version("1.0.0")
        .build();
  }

  private SecurityContext securityContext() {
    return SecurityContext.builder()
        .securityReferences(defaultAuth())
        // .forPaths(PathSelectors.regex("/*.*"))
        .build();
  }

  private List<SecurityReference> defaultAuth() {
    AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
    AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
    authorizationScopes[0] = authorizationScope;
    return Arrays.asList(new SecurityReference("token", authorizationScopes));
  }
}
