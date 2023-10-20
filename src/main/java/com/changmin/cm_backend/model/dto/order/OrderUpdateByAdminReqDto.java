package com.changmin.cm_backend.model.dto.order;

import io.swagger.annotations.*;
import lombok.*;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class OrderUpdateByAdminReqDto extends OrderUpdateStateReqDto {
  @ApiModelProperty(value = "管理员备注")
  private String adminDesc;
}
