package com.changmin.cm_backend.service;

import static com.changmin.cm_backend.service.WuxingServiceImpl.ADMIN_TOKEN;

import cn.hutool.core.collection.CollectionUtil;
import com.changmin.cm_backend.config.common.pojo.PageResult;
import com.changmin.cm_backend.config.security.config.SecurityProperties;
import com.changmin.cm_backend.config.security.util.SecurityFrameworkUtils;
import com.changmin.cm_backend.exceptions.ErrorCodeConstants;
import com.changmin.cm_backend.mapper.OrderMapper;
import com.changmin.cm_backend.mapper.UserMapper;
import com.changmin.cm_backend.model.convertor.UserConvert;
import com.changmin.cm_backend.model.dto.user.UserBaseDto;
import com.changmin.cm_backend.model.dto.user.UserUpdateReqDto;
import com.changmin.cm_backend.model.dto.user.UserWithOrderCountDto;
import com.changmin.cm_backend.model.dto.user.UserWithOrderCountPageReqDto;
import com.changmin.cm_backend.model.pojo.UserDO;
import com.changmin.cm_backend.model.pojo.UserWithOrderCountDO;
import com.changmin.cm_backend.util.LoginUserUtil;
import com.changmin.cm_backend.util.WebFrameworkUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

  @Autowired UserMapper userMapper;

  @Autowired
  OrderMapper orderMapper;

  @Autowired SecurityProperties securityProperties;

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

  @Override
  public PageResult<UserWithOrderCountDto> getListWithOrdersCount(
      UserWithOrderCountPageReqDto dto) {
    /**
     * select user.username, user.phone, user.email, count(*) from order as o left join user as u on
     * o.user_id = u.id group by u.id order by o.create_time_utc desc where o.order_state =
     * 'RESERVED' ;
     */
    String token =
        SecurityFrameworkUtils.obtainAuthorization(
            WebFrameworkUtils.getRequest(), securityProperties.getTokenHeader());
    if (!ADMIN_TOKEN.equals(token)) {
      throw ErrorCodeConstants.FORBIDDEN;
    }
    Page<UserWithOrderCountDO> iPage = PageHelper.startPage(dto.getPageNo(), dto.getPageSize());
    List<UserWithOrderCountDO> userWithOrderCount = orderMapper.getUserWithOrderCount();
    if (CollectionUtil.isEmpty(userWithOrderCount)) {
      return new PageResult<>(new ArrayList<>(), 0L);
    }

    return new PageResult<>(UserConvert.INSTANCE.convertList(userWithOrderCount), iPage.getTotal());
  }
}
