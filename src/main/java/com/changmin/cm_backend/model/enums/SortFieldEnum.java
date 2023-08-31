package com.changmin.cm_backend.model.enums;

import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.changmin.cm_backend.config.common.core.StringArrayValuable;
import com.changmin.cm_backend.config.swagger.SwaggerDisplayEnum;
import com.changmin.cm_backend.model.pojo.WuxingDO;
import com.fasterxml.jackson.annotation.JsonCreator;
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
public enum SortFieldEnum implements StringArrayValuable {
  Default("Default", "无", WuxingDO::getCreateTimeUtc),
  Price("Price", "价格", WuxingDO::getPrice),
  Area("Area", "面积", WuxingDO::getArea),
  ;

  private final String value;
  private final String name;
  private final SFunction<WuxingDO, ?> field;

  SortFieldEnum(String value, String name, SFunction<WuxingDO, ?> field) {
    this.value = value;
    this.name = name;
    this.field = field;
  }

  @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
  public static SortFieldEnum from(String value) {
    for (SortFieldEnum item : SortFieldEnum.values()) {
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
