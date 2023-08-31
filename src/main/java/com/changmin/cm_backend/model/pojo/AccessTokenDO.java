package com.changmin.cm_backend.model.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.changmin.cm_backend.config.common.enums.UserTypeEnum;
import com.changmin.cm_backend.config.mybatis.core.dataobject.BaseDO;
import java.util.Date;
import java.util.List;
import java.util.Map;
import lombok.*;

/**
 * OAuth2 访问令牌 DO
 *
 * <p>如下字段，暂时未使用，暂时不支持： user_name、authentication（用户信息）
 *
 * @author changmin
 */
@TableName(value = "public.access_token", autoResultMap = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AccessTokenDO extends BaseDO {

  /** 编号，数据库递增 */
  @TableId(type = IdType.ASSIGN_ID)
  private Long id;
  /** 访问令牌 */
  private String accessToken;
  /** 刷新令牌 */
  private String refreshToken;
  /** 用户编号 */
  private String userId;
  /**
   * 用户类型
   *
   * <p>枚举 {@link UserTypeEnum}
   */
  private Integer userType;
  /** 客户端编号 */
  private String clientId;
  /** 授权范围 */
  @TableField(typeHandler = JacksonTypeHandler.class)
  private List<String> scopes;
  /** 过期时间 */
  private Date expiresTimeUtc;

  @TableField(typeHandler = JacksonTypeHandler.class)
  private Map<String, Object> userContext;

}
