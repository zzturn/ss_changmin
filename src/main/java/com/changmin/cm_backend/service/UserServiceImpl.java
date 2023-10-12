package com.changmin.cm_backend.service;

import com.changmin.cm_backend.mapper.UserMapper;
import com.changmin.cm_backend.model.convertor.UserConvert;
import com.changmin.cm_backend.model.dto.user.UserBaseDto;
import com.changmin.cm_backend.model.dto.user.UserUpdateReqDto;
import com.changmin.cm_backend.model.pojo.UserDO;
import com.changmin.cm_backend.util.LoginUserUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

  @Autowired UserMapper userMapper;

  @Override
  public UserBaseDto updateInfo(UserUpdateReqDto dto) {
    String userId = LoginUserUtil.getLoginUserIdOrElseThrow();
    UserDO convert = UserConvert.INSTANCE.convert(dto);
    convert.setId(userId);

    userMapper.updateById(convert);
    return UserConvert.INSTANCE.convertBase(userMapper.selectById(userId));
  }

  @Override
  public UserBaseDto getMyself() {
    String userId = LoginUserUtil.getLoginUserIdOrElseThrow();
    return UserConvert.INSTANCE.convertBase(userMapper.selectById(userId));
  }
}
