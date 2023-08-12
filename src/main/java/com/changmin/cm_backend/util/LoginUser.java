package com.changmin.cm_backend.util;

import cn.hutool.core.map.MapUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 登录用户信息
 *
 * @author changmin源码
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginUser {

  /** 用户编号 */
  private String id;
  /**
   * 用户类型
   *
   * <p>关联 {@link UserTypeEnum}
   */
  private Integer userType;

  /** 授权范围 */
  private List<String> scopes;

  // ========== 上下文 ==========
  /** LoginUser 的 Context 缓存 Key */
  @JsonIgnore public static final String DATA_PERMISSION_RULE = "data_permission_rule";

  /**
   * 上下文字段，不进行持久化
   *
   * <p>1. 用于基于 LoginUser 维度的临时缓存
   */
  @JsonIgnore private Map<String, Object> context;

  public void setContext(String key, Object value) {
    if (context == null) {
      context = new HashMap<>();
    }
    context.put(key, value);
  }

  public <T> T getContext(String key, Class<T> type) {
    return MapUtil.get(context, key, type);
  }
}
