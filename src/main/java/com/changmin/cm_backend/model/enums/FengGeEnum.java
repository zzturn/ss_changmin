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
public enum FengGeEnum implements StringArrayValuable {
  None( "None", "无"),
  TianYuan("TianYuan", "田園風光"),
  EuropeStyle("EuropeStyle", "歐式風情"),
  CountryHuaiJiu("CountryHuaiJiu", "鄉村懷舊"),
  ZiRanHuanBao("ZiRanHuanBao", "自然環保"),
  ModernDangDai("ModernDangDai", "摩登當代"),
  JianYuePuSu("JianYuePuSu", "簡約樸素"),
  ;

  @JsonValue private final String value;
  private final String name;

  FengGeEnum(String value, String name) {
    this.value = value;
    this.name = name;
  }

  @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
  public static FengGeEnum from(String value) {
    for (FengGeEnum item : FengGeEnum.values()) {
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
