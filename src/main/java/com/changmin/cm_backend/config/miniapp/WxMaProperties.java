package com.changmin.cm_backend.config.miniapp;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@Data
@ConfigurationProperties(prefix = "social.miniapp")
@Configuration
public class WxMaProperties {

  /** 设置微信小程序的appid */
  private String appid;

  /** 设置微信小程序的Secret */
  private String secret;

  /** 设置微信小程序灵感图片分享路径 */
  private String artworkPage;

  /** 设置微信小程序码是否检查路径 */
  private Boolean checkPath = true;

  /** 设置微信小程序码的版本 */
  private String envVersion = "release";
}
