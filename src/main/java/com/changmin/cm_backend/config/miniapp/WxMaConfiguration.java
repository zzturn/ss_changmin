package com.changmin.cm_backend.config.miniapp;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.impl.WxMaServiceImpl;
import cn.binarywang.wx.miniapp.config.impl.WxMaDefaultConfigImpl;
import javax.annotation.Resource;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@Slf4j
@Configuration
public class WxMaConfiguration {

  @Resource
  private WxMaProperties wxMaProperties;

  @Bean
  public WxMaService wxMaService() {
    WxMaService maService = new WxMaServiceImpl();
    WxMaDefaultConfigImpl config = new WxMaDefaultConfigImpl();
    config.setAppid(wxMaProperties.getAppid());
    config.setSecret(wxMaProperties.getSecret());
    maService.setWxMaConfig(config);
    return maService;
  }
}
