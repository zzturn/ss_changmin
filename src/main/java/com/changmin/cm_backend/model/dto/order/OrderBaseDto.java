package com.changmin.cm_backend.model.dto.order;


import com.changmin.cm_backend.model.enums.OrderStateEnum;
import com.changmin.cm_backend.model.enums.OrderTypeEnum;
import io.swagger.annotations.*;
import javax.validation.constraints.NotNull;
import lombok.*;

@Data
public class OrderBaseDto{

  @ApiModelProperty(value = "id")
  private Long id;
  
  @ApiModelProperty(value = "")
  private Boolean deleted;
  
  @ApiModelProperty(value = "用户id")
  private String userId;
  
  @ApiModelProperty(value = "订单类型")
  @NotNull
  private OrderTypeEnum type;
  
  @ApiModelProperty(value = "订单状态")
  private OrderStateEnum state;
  
  @ApiModelProperty(value = "屋型id")
  private String wuxingId;
  
  @ApiModelProperty(value = "冗余数据")
  private String data;
  
  @ApiModelProperty(value = "用户名")
  private String username;
  
  @ApiModelProperty(value = "手机号")
  private String phone;
  
  @ApiModelProperty(value = "邮箱")
  private String email;

  @ApiModelProperty(value = "描述")
  private String description;
}

