package com.changmin.cm_backend.component;

import com.changmin.cm_backend.component.datejson.NumberSerializer;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.afterburner.AfterburnerModule;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class ObjectMapperFactory {
  /** 正常情况应该是不需要两个策略，non_hashid是为了兼容集群间服务调用不需要加密的问题 */
  public enum Strategy {
    COMMON,
    LOWER_CAMEL_CASE;
  }

  private static final ObjectMapper COMMON_INSTANCE = create();
  private static final ObjectMapper LOWER_CAMEL_CASE_INSTANCE = create(Strategy.LOWER_CAMEL_CASE);

  public static ObjectMapper getInstance() {
    return COMMON_INSTANCE;
  }

  public static ObjectMapper getInstance(Strategy strategy) {
    switch (strategy) {
      case LOWER_CAMEL_CASE:
        return LOWER_CAMEL_CASE_INSTANCE;
      default:
        return COMMON_INSTANCE;
    }
  }

  public static ObjectMapper create() {
    return create(Strategy.COMMON);
  }

  public static ObjectMapper create(Strategy strategy) {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
    SimpleModule simpleModule = new SimpleModule();
    simpleModule
        .addSerializer(Long.class, NumberSerializer.INSTANCE)
        .addSerializer(Long.TYPE, NumberSerializer.INSTANCE);

    switch (strategy) {
      case LOWER_CAMEL_CASE:
        return new ObjectMapper()
            .registerModules(new JavaTimeModule())
            .registerModule(new AfterburnerModule())
            .registerModule(simpleModule)
            .setAnnotationIntrospector(new JacksonAnnotationIntrospector())
            .setDateFormat(sdf)
            .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .setPropertyNamingStrategy(PropertyNamingStrategies.LOWER_CAMEL_CASE);
      default:
        return new ObjectMapper()
            .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE)
            .registerModule(simpleModule)
            .registerModules(new JavaTimeModule())
            .registerModule(new AfterburnerModule())
            .setAnnotationIntrospector(new JacksonAnnotationIntrospector())
            .setDateFormat(sdf);
    }
  }
}
