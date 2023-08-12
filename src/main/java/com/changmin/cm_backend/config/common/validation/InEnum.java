package com.changmin.cm_backend.config.common.validation;

import com.changmin.cm_backend.config.common.core.StringArrayValuable;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({
  ElementType.METHOD,
  ElementType.FIELD,
  ElementType.ANNOTATION_TYPE,
  ElementType.CONSTRUCTOR,
  ElementType.PARAMETER,
  ElementType.TYPE_USE
})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = EnumValidator.class)
public @interface InEnum {

  /**
   * @return 实现 EnumValuable 接口的
   */
  Class<? extends StringArrayValuable> value();

  String param() default "";

  String message() default "{param}必须是 {value}，输入值为{input}";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

  /**
   * @return 忽略校验
   */
  boolean ignoreValid() default false;
}
