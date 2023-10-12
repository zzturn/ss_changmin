package com.changmin.cm_backend.model.dto.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserBaseDto {
  @ApiModelProperty(value = "用户名")
  private String username;

  @ApiModelProperty(value = "手机号")
  private String phone;

  @ApiModelProperty(value = "邮箱")
  private String email;
}
