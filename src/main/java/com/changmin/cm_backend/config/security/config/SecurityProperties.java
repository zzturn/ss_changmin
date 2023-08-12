package com.changmin.cm_backend.config.security.config;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import java.util.Collections;
import java.util.List;

@ConfigurationProperties(prefix = "aibackend.security")
@Validated
@Data
public class SecurityProperties {

  /** HTTP 请求时，访问令牌的请求 Header */
  @NotEmpty(message = "Validation_NotEmpty_SecurityProperties_TokenHeader")
  private String tokenHeader = "xauthorization";

  /** mock 模式的开关 */
  @NotNull(message = "Validation_NotNull_SecurityProperties_MockEnable")
  private Boolean mockEnable = false;
  /** mock 模式的密钥 一定要配置密钥，保证安全性 */
  @NotEmpty(message = "Validation_NotEmpty_SecurityProperties_MockSecret") // 这里设置了一个默认值，因为实际上只有 mockEnable 为 true 时才需要配置。
  private String mockSecret = "test";

  /** 手机验证码使用666666 */
  private Boolean msgMock = false;

  /** 免登录的 URL 列表 */
  private List<String> permitAllUrls = Collections.emptyList();
}
