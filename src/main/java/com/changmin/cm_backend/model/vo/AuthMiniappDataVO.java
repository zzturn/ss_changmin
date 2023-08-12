package com.changmin.cm_backend.model.vo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Api(value = "用户系统 - 微信小程序用户信息待解密数据")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthMiniappDataVO {
  @ApiModelProperty(
      value = "signature",
      required = true,
      example = "9b2ffbc1-7425-4155-9894-9d5c08541d62")
  @NotEmpty
  private String signature;

  @ApiModelProperty(value = "接口原生返回的数据", required = true)
  @NotEmpty
  private String rawData;

  @ApiModelProperty(value = "加密数据", required = true)
  @NotEmpty
  private String encryptedData;

  @ApiModelProperty(value = "对称解密算法初始向量", required = true)
  @NotEmpty
  private String iv;
}
