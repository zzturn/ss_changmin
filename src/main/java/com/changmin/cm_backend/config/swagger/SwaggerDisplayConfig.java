package com.changmin.cm_backend.config.swagger;

import com.changmin.cm_backend.config.common.validation.InEnum;
import com.fasterxml.classmate.ResolvedType;
import com.google.common.base.Optional;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import springfox.documentation.builders.ModelPropertyBuilder;
import springfox.documentation.schema.Annotations;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.schema.ModelPropertyBuilderPlugin;
import springfox.documentation.spi.schema.contexts.ModelPropertyContext;

/**
 * content
 *
 * @author ouzhenxiong
 * @since 2023/4/7
 */
@Component
@Primary
@Slf4j
public class SwaggerDisplayConfig implements ModelPropertyBuilderPlugin {
  /** 是否允许swagger */
  @Value("${swagger.enable:true}")
  private Boolean enableSwagger;

  @Override
  public void apply(ModelPropertyContext context) {
    // 如果不支持swagger的话，直接返回
    if (!enableSwagger) {
      return;
    }

    // 获取当前字段的类型
    final Class fieldType = context.getBeanPropertyDefinition().get().getField().getRawType();

    // 为枚举字段设置注释
    descForEnumFields(context, fieldType);
  }

  /** 为枚举字段设置注释 */
  private void descForEnumFields(ModelPropertyContext context, Class fieldType) {

    Optional<InEnum> annotation =
        Annotations.findPropertyAnnotation(context.getBeanPropertyDefinition().get(), InEnum.class);

    if (!annotation.isPresent()) {
      return;
    }

    // @ApiModelProperties中的notes指定的class类型
    Class rawPrimaryType;
    try {
      rawPrimaryType = annotation.get().value();
    } catch (Exception e) {
      // 如果指定的类型无法转化，直接忽略
      return;
    }

    // 如果对应的class是一个@SwaggerDisplayEnum修饰的枚举类，获取其中的枚举值
    Object[] subItemRecords = null;
    SwaggerDisplayEnum swaggerDisplayEnum =
        AnnotationUtils.findAnnotation(rawPrimaryType, SwaggerDisplayEnum.class);
    if (null != swaggerDisplayEnum && Enum.class.isAssignableFrom(rawPrimaryType)) {
      subItemRecords = rawPrimaryType.getEnumConstants();
    }
    if (null == subItemRecords) {
      return;
    }

    final List<String> displayValues =
        Arrays.stream(subItemRecords)
            .filter(Objects::nonNull)
            .map(
                item -> {
                  return item.toString();
                })
            .filter(Objects::nonNull)
            .collect(Collectors.toList());

    String joinText = " (" + String.join("; ", displayValues) + ")";
    try {
      Field mField = ModelPropertyBuilder.class.getDeclaredField("description");
      mField.setAccessible(true);
      joinText = mField.get(context.getBuilder()) + joinText;
    } catch (Exception e) {
      log.error(e.getMessage());
    }

    final ResolvedType resolvedType = context.getResolver().resolve(fieldType);
    context.getBuilder().description(joinText).type(resolvedType);
  }

  @Override
  public boolean supports(DocumentationType documentationType) {
    return true;
  }
}
