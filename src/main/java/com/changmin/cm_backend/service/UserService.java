package com.changmin.cm_backend.service;

import com.changmin.cm_backend.model.dto.user.UserBaseDto;
import com.changmin.cm_backend.model.dto.user.UserUpdateReqDto;

public interface UserService {

  UserBaseDto updateInfo(UserUpdateReqDto dto);

  UserBaseDto getMyself();
}
