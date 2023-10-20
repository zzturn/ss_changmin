package com.changmin.cm_backend.model.pojo;

import com.baomidou.mybatisplus.annotation.*;
import com.changmin.cm_backend.config.mybatis.core.dataobject.*;
import java.util.*;
import lombok.*;

/**
 * (Order)表实体类
 *
 * @author makejava
 * @since 2023-10-12 20:12:13
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "public.order", autoResultMap = true)
@EqualsAndHashCode(callSuper = true)
public class OrderDO extends BaseDO {

  // id
  @TableId(type = IdType.ASSIGN_ID)
  private Long id;

  // 用户id
  private String userId;

  // 订单类型
  private String type;

  // 订单状态
  private String state;

  // 屋型id
  private String wuxingId;

  // 冗余数据
  private String data;

  // 用户名
  private String username;

  // 手机号
  private String phone;

  // 邮箱
  private String email;

  // 描述
  private String description;

  // 管理员备注
  private String adminDesc;
}
