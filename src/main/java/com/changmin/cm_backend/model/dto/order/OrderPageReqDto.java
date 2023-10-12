package com.changmin.cm_backend.model.dto.order;

import com.changmin.cm_backend.config.common.pojo.PageParam;
import com.changmin.cm_backend.model.enums.OrderStateEnum;
import io.swagger.annotations.*;
import lombok.*;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class OrderPageReqDto extends PageParam{
    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "订单状态")
    private OrderStateEnum state;

}

