package com.changmin.cm_backend.controller;

import com.changmin.cm_backend.config.common.pojo.Resp;
import com.changmin.cm_backend.model.dto.user.UserBaseDto;
import com.changmin.cm_backend.model.dto.user.UserUpdateReqDto;
import com.changmin.cm_backend.service.UserService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Api(tags = "用户")
@RestController
@RequestMapping("/v1/user")
@Validated
@Slf4j
public class UserController {

  @Autowired
  UserService userService;

  @PostMapping("/update")
  public Resp<UserBaseDto> update(@RequestBody UserUpdateReqDto reqVO) {
    return Resp.data(userService.updateInfo(reqVO));
  }

  @GetMapping("/get_myself")
  public Resp<UserBaseDto> getMyself() {
    return Resp.data(userService.getMyself());
  }
}
