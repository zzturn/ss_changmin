package com.changmin.cm_backend.config.common.validation;


import com.changmin.cm_backend.config.common.core.StringArrayValuable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EnumValidator implements ConstraintValidator<InEnum, Object> {

  private List<String> values;
  private boolean ignoreValid;
  private String param;

  @Override
  public void initialize(InEnum annotation) {
    StringArrayValuable[] values = annotation.value().getEnumConstants();
    if (values.length == 0) {
      this.values = Collections.emptyList();
    } else {
      this.values = Arrays.stream(values[0].array()).collect(Collectors.toList());
    }
    this.ignoreValid = annotation.ignoreValid();
    this.param = annotation.param();
  }

  @Override
  public boolean isValid(Object value, ConstraintValidatorContext context) {
    if (ignoreValid) {
      return true;
    }
    // 为空时，默认不校验，即认为通过
    if (value == null) {
      return true;
    }
    if (value instanceof Collection) {
      Collection<?> collection = (Collection<?>) value;
      // 遍历value 并执行doValid方法
      for (Object item : collection) {
        if (!doValid(item, context)) {
          return false;
        }
      }
      return true;
    } else {
      return doValid(value, context);
    }
  }

  private boolean doValid(Object value, ConstraintValidatorContext context) {

    // 校验通过
    if (values.contains(String.valueOf(value))) {
      return true;
    }
    // 校验不通过，自定义提示语句（因为，注解上的 value 是枚举类，无法获得枚举类的实际值）
    context.disableDefaultConstraintViolation(); // 禁用默认的 message 的值
    context
        .buildConstraintViolationWithTemplate(
            context
                .getDefaultConstraintMessageTemplate()
                .replaceAll("\\{param}", this.param)
                .replaceAll("\\{value}", values.toString())
                .replaceAll("\\{input}", value.toString()))
        .addConstraintViolation(); // 重新添加错误提示语句
    return false;
  }
}
