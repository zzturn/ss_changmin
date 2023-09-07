package com.changmin.cm_backend.model.enums;

import com.changmin.cm_backend.config.common.core.StringArrayValuable;
import com.changmin.cm_backend.config.swagger.SwaggerDisplayEnum;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.Arrays;
import lombok.Getter;
import lombok.ToString;

/**
 * content
 *
 * @author luoxin
 * @since 2023/5/16
 */
@Getter
@SwaggerDisplayEnum
@ToString
public enum RoofTypesEnum implements StringArrayValuable {
  P("P", "单坡"),
  H("H", "双坡同坡（缓）"),
  J("J", "双坡同坡（尖）"),
  X("X", "双坡异坡"),
  ;

  @JsonValue private final String value;
  private final String name;

  RoofTypesEnum(String value, String name) {
    this.value = value;
    this.name = name;
  }

  @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
  public static RoofTypesEnum from(String value) {
    for (RoofTypesEnum item : RoofTypesEnum.values()) {
      if (item.getValue().equals(value)) {
        return item;
      }
    }
    return null;
  }

  public static final String[] ARRAYS =
      Arrays.stream(values()).map(item -> String.valueOf(item.getValue())).toArray(String[]::new);

  @Override
  public String[] array() {
    return ARRAYS;
  }
}
