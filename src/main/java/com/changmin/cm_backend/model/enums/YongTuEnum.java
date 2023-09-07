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
public enum YongTuEnum implements StringArrayValuable {
  MingShu("MingShu", "民宿"),
  ZhuZhai("ZhuZhai", "住宅"),
  GongZuoShi("GongZuoShi", "工作室"),
  GongJianJiHe("GongJianJiHe", "公建集合"),
  WeiShengSheShi("WeiShengSheShi", "卫生设施"),
  NongYuChuPeng("NongYuChuPeng", "农渔畜棚"),
  Other("Other", "其他");

  @JsonValue private final String value;
  private final String name;

  YongTuEnum(String value, String name) {
    this.value = value;
    this.name = name;
  }

  @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
  public static YongTuEnum from(String value) {
    for (YongTuEnum item : YongTuEnum.values()) {
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
