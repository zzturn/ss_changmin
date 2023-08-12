package com.changmin.cm_backend.config.common.enums;

import cn.hutool.core.util.ArrayUtil;
import com.changmin.cm_backend.config.common.core.StringArrayValuable;
import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/** 全局用户类型枚举 */
@AllArgsConstructor
@Getter
public enum UserTypeEnum implements StringArrayValuable {
  MEMBER(1, "会员"), // 面向 c 端，普通用户
  ADMIN(2, "管理员"); // 面向 b 端，管理后台

  public static final String[] ARRAYS =
      Arrays.stream(values()).map(item -> String.valueOf(item.getValue())).toArray(String[]::new);

  /** 类型 */
  private final Integer value;
  /** 类型名 */
  private final String name;

  public static UserTypeEnum valueOf(Integer value) {
    return ArrayUtil.firstMatch(
        userType -> userType.getValue().equals(value), UserTypeEnum.values());
  }

  @Override
  public String[] array() {
    return ARRAYS;
  }

  @JsonCreator
  public static UserTypeEnum from(Integer value) {
    for (UserTypeEnum item : UserTypeEnum.values()) {
      if (item.getValue().equals(value)) {
        return item;
      }
    }
    return null;
  }
}
