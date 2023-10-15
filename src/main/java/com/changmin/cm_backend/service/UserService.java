package com.changmin.cm_backend.service;

import com.changmin.cm_backend.config.common.pojo.PageResult;
import com.changmin.cm_backend.model.dto.user.UserBaseDto;
import com.changmin.cm_backend.model.dto.user.UserUpdateReqDto;
import com.changmin.cm_backend.model.dto.user.UserWithOrderCountDto;
import com.changmin.cm_backend.model.dto.user.UserWithOrderCountPageReqDto;

public interface UserService {

  UserBaseDto updateInfo(UserUpdateReqDto dto);

  UserBaseDto getMyself();

  PageResult<UserWithOrderCountDto> getListWithOrdersCount(UserWithOrderCountPageReqDto dto);
}
