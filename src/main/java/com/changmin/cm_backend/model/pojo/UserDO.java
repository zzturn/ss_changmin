package com.changmin.cm_backend.model.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;

import com.changmin.cm_backend.config.mybatis.core.dataobject.BaseDO;
import lombok.*;

/**
 * 用户信息表(User)表实体类
 *
 * @author changmin
 * @since 2023-04-21 10:07:48
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "public.user", autoResultMap = true)
@EqualsAndHashCode(callSuper = true)
public class UserDO extends BaseDO {

  // 用户ID
  @TableId(type = IdType.ASSIGN_UUID)
  private String id;

  // 用户账号
  private String username;

  // 密码
  private String password;

  // 用户昵称
  private String nickname;

  // 备注
  private String remark;

  // 用户邮箱
  private String email;

  // 手机号码
  private String mobile;

  // 用户性别
  private String gender;

  // 头像地址
  private String avatar;

  // 帐号状态（0正常 1停用）
  private Integer status;

  // 最后登录IP
  private String loginIp;

  // 最后登录时间
  private Date loginDate;

  // 用户类型 1前台用户 2后台用户
  private Integer userType;

  private String openid;
}
