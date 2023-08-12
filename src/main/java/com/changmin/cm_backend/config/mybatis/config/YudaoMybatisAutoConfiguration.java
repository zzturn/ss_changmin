package com.changmin.cm_backend.config.mybatis.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.core.incrementer.IKeyGenerator;
import com.baomidou.mybatisplus.extension.incrementer.PostgreKeyGenerator;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.changmin.cm_backend.config.mybatis.core.handler.DefaultDBFieldHandler;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * MyBaits 配置类
 *
 * @author changmin源码
 */
@Configuration
@MapperScan(
    value = "com.changmin.cm_backend.mapper",
    annotationClass = Mapper.class,
    lazyInitialization = "false") // Mapper 懒加载，目前仅用于单元测试
public class YudaoMybatisAutoConfiguration {

  @Bean
  public MybatisPlusInterceptor mybatisPlusInterceptor() {
    MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
    mybatisPlusInterceptor.addInnerInterceptor(new PaginationInnerInterceptor()); // 分页插件
    return mybatisPlusInterceptor;
  }

  @Bean
  public MetaObjectHandler defaultMetaObjectHandler() {
    return new DefaultDBFieldHandler(); // 自动填充参数类
  }

  @Bean
  @ConditionalOnProperty(
      prefix = "mybatis-plus.global-config.db-config",
      name = "id-type",
      havingValue = "INPUT")
  public IKeyGenerator keyGenerator(ConfigurableEnvironment environment) {
    return new PostgreKeyGenerator();
  }
}
