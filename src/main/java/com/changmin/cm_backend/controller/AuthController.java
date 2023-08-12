package com.changmin.cm_backend.controller;

import com.changmin.cm_backend.config.common.pojo.Resp;
import com.changmin.cm_backend.model.vo.AuthLoginRespVO;
import com.changmin.cm_backend.model.vo.AuthMiniappLoginReqVO;
import com.changmin.cm_backend.service.UserService;
import io.swagger.annotations.Api;
import javax.annotation.security.PermitAll;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Api(tags = "登陆注册认证")
@RestController
@RequestMapping("/v1/auth")
@Validated
@Slf4j
public class AuthController {

  @Autowired UserService userService;

  @GetMapping("")
  public Resp<String> get() {
    return Resp.data("hello");
  }

  @GetMapping("")
  @PermitAll
  public Resp<String> permitAll() {
    return Resp.data("hello");
  }

  public Resp<AuthLoginRespVO> miniappLogin(AuthMiniappLoginReqVO reqVO) {
    return Resp.data(userService.miniappLogin(reqVO));
  }
}
