package com.changmin.cm_backend.model.dto.order;

import com.changmin.cm_backend.model.enums.OrderTypeEnum;
import io.swagger.annotations.*;
import javax.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
public class OrderCreateReqDto {
    @ApiModelProperty(value = "订单类型")
    @NotNull private OrderTypeEnum type;

    @ApiModelProperty(value = "屋型id")
    private String wuxingId;

    @ApiModelProperty(value = "冗余数据")
    private String data;

    @ApiModelProperty(value = "用户名")
    @NotNull
    private String username;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "描述")
    private String description;
}

