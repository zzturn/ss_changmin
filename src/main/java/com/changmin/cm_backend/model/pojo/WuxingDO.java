package com.changmin.cm_backend.model.pojo;

import com.baomidou.mybatisplus.annotation.*;
import com.changmin.cm_backend.config.mybatis.core.dataobject.*;
import java.util.*;
import lombok.*;

/**
 * (Wuxing)表实体类
 *
 * @author makejava
 * @since 2023-08-31 18:27:29
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "public.wuxing", autoResultMap = true)
@EqualsAndHashCode(callSuper = true)
public class WuxingDO extends BaseDO {

  @TableId(type = IdType.ASSIGN_UUID)
  private String id;

  private String data;

  // 面积
  private Double areaValue;

  // 价格
  private Long price;

  // 用途
  private String yongTu;

  // 面积枚举
  private String area;

  // 风格
  private String fengGe;

  // 屋顶
  private String roof;

  // 房间数类型
  private String roomCount;

  // 类型
  private String type;

  // 能否定制
  private Boolean canCustom;
}
