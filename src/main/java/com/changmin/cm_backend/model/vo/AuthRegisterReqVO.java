package com.changmin.cm_backend.model.vo;

import com.changmin.cm_backend.config.common.validation.Mobile;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Api(value = "用户系统 - 短信验证码的登录 Request VO")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthRegisterReqVO {

  @ApiModelProperty(value = "手机号", required = true, example = "15800000000")
  @NotEmpty
  @Mobile
  private String phone;

  @ApiModelProperty(value = "密码", required = true, example = "666666")
  @NotEmpty
  private String password;
}
