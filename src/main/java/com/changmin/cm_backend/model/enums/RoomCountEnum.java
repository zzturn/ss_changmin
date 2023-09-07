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
public enum RoomCountEnum implements StringArrayValuable {
  R_1("R_1","R_1"),
  R_2("R_2","R_2"),
  R_3("R_3","R_3"),
  R_4("R_4","R_4"),
  R_Gearter_4("R_Gearter_4","R_Gearter_4"),
  ;

  @JsonValue private final String value;
  private final String name;

  RoomCountEnum(String value, String name) {
    this.value = value;
    this.name = name;
  }

  @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
  public static RoomCountEnum from(String value) {
    for (RoomCountEnum item : RoomCountEnum.values()) {
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
