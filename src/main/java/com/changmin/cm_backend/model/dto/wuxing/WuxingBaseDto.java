package com.changmin.cm_backend.model.dto.wuxing;

import com.changmin.cm_backend.model.enums.*;
import io.swagger.annotations.*;
import lombok.*;

@Data
public class WuxingBaseDto {
  @ApiModelProperty(value = "")
  private String data;

  @ApiModelProperty(value = "面积")
  private Double areaValue;

  @ApiModelProperty(value = "价格")
  private Long price;

  @ApiModelProperty(value = "用途")
  private YongTuEnum yongTu;

  @ApiModelProperty(value = "面积枚举")
  private AreaEnum area;

  @ApiModelProperty(value = "风格")
  private FengGeEnum fengGe;

  @ApiModelProperty(value = "屋顶")
  private RoofTypesEnum roof;

  @ApiModelProperty(value = "房间数类型")
  private RoomCountEnum roomCount;

  @ApiModelProperty(value = "能否定制")
  private Boolean canCustom;

  @ApiModelProperty(value = "类型")
  private WuxingTypeEnum type;
}
