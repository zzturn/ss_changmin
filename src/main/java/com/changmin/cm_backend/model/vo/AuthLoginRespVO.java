package com.changmin.cm_backend.model.vo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Api(value = "用户系统 - 登录 Response VO")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthLoginRespVO {

  @ApiModelProperty(value = "用户编号", required = true, example = "xxxuseridxxx")
  private String userId;

  @ApiModelProperty(value = "访问令牌", required = true, example = "happy")
  private String accessToken;

  @ApiModelProperty(value = "过期时间", required = true)
  private Date expiresTime;
}
