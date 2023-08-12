package com.changmin.cm_backend.config.swagger;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * content
 *
 * @author ouzhenxiong
 * @since 2023/4/7
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface SwaggerDisplayEnum {

  String valueName() default "value";

  String descName() default "desc";
}
