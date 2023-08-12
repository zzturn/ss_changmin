package com.changmin.cm_backend.model.vo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Api(value = "用户系统 - 微信小程序绑定、登录")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthMiniappLoginReqVO {
  @ApiModelProperty(
      value = "授权码",
      required = true,
      example = "9b2ffbc1-7425-4155-9894-9d5c08541d62")
  @NotEmpty(message = "Validation_NotEmpty_AuthSocialLoginReqVO_Code")
  private String code;

  @ApiModelProperty(value = "用户头像、昵称等基本信息")
  private AuthMiniappDataVO miniappUserInfo;

  @ApiModelProperty(value = "获取手机号的动态令牌")
  private String mobileCode;
}
