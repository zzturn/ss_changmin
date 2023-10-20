package com.changmin.cm_backend.model.dto.order;

import com.changmin.cm_backend.model.enums.OrderStateEnum;
import io.swagger.annotations.*;
import javax.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
public class OrderUpdateStateReqDto {

  @ApiModelProperty(value = "id")
  @NotNull
  private Long id;

  @ApiModelProperty(value = "订单状态")
  private OrderStateEnum state;
}
