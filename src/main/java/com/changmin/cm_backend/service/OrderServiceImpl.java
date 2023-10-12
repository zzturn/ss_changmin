package com.changmin.cm_backend.service;

import static com.changmin.cm_backend.service.WuxingServiceImpl.ADMIN_TOKEN;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.changmin.cm_backend.config.common.pojo.PageResult;
import com.changmin.cm_backend.config.mybatis.core.query.LambdaQueryWrapperX;
import com.changmin.cm_backend.config.security.config.SecurityProperties;
import com.changmin.cm_backend.config.security.util.SecurityFrameworkUtils;
import com.changmin.cm_backend.exceptions.ErrorCodeConstants;
import com.changmin.cm_backend.mapper.*;
import com.changmin.cm_backend.model.convertor.*;
import com.changmin.cm_backend.model.dto.order.*;
import com.changmin.cm_backend.model.enums.OrderStateEnum;
import com.changmin.cm_backend.model.pojo.*;
import com.changmin.cm_backend.util.LoginUserUtil;
import com.changmin.cm_backend.util.WebFrameworkUtils;
import java.util.Objects;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
  @Resource private OrderMapper orderMapper;

  @Resource private SecurityProperties securityProperties;

  @Override
  public OrderItemDto get(Long id) {
    String userId = LoginUserUtil.getLoginUserId();
    String token =
        SecurityFrameworkUtils.obtainAuthorization(
            Objects.requireNonNull(WebFrameworkUtils.getRequest(), "Request cannot be null"),
            securityProperties.getTokenHeader());

    // 不是用户也不是管理员，直接抛异常
    if (StrUtil.isEmpty(userId) && !ADMIN_TOKEN.equals(token)) {
      throw ErrorCodeConstants.UNAUTHORIZED;
    }

    OrderItemDto orderItemDto = OrderConvert.INSTANCE.convertItem(orderMapper.selectById(id));
    if (!ADMIN_TOKEN.equals(token) && !Objects.equals(orderItemDto.getUserId(), userId)) {
      throw ErrorCodeConstants.UNAUTHORIZED;
    }
    return orderItemDto;
  }

  @Override
  public Long create(OrderCreateReqDto dto) {
    OrderDO dataDo = OrderConvert.INSTANCE.convert(dto);
    dataDo.setState(OrderStateEnum.RESERVED.getValue());
    String userId = LoginUserUtil.getLoginUserIdOrElseThrow();
    dataDo.setUserId(userId);
    orderMapper.insert(dataDo);
    return dataDo.getId();
  }

  @Override
  public void update(OrderUpdateStateReqDto dto) {
    String userId = LoginUserUtil.getLoginUserIdOrElseThrow();
    OrderDO orderDO = orderMapper.selectById(dto.getId());
    if (!Objects.equals(orderDO.getUserId(), userId)) {
      throw ErrorCodeConstants.UNAUTHORIZED;
    }
    if (OrderStateEnum.COMPLETED.equals(dto.getState())) {
      throw ErrorCodeConstants.FORBIDDEN;
    }
    orderMapper.updateById(OrderConvert.INSTANCE.convert(dto));
  }

  @Override
  public void updateByAdmin(OrderUpdateStateReqDto dto) {
    String token =
        SecurityFrameworkUtils.obtainAuthorization(
            Objects.requireNonNull(WebFrameworkUtils.getRequest(), "Request cannot be null"),
            securityProperties.getTokenHeader());
    if (!ADMIN_TOKEN.equals(token)) {
      throw ErrorCodeConstants.FORBIDDEN;
    }
    orderMapper.updateById(OrderConvert.INSTANCE.convert(dto));
  }

  @Override
  public void delete(Long id) {
    orderMapper.deleteById(id);
  }

  @Override
  public PageResult<OrderItemDto> getPage(OrderPageReqDto dto) {
    LambdaQueryWrapper<OrderDO> queryWrapper =
        new LambdaQueryWrapperX<OrderDO>()
            .like(Objects.nonNull(dto.getUsername()), OrderDO::getUsername, dto.getUsername())
            .like(Objects.nonNull(dto.getPhone()), OrderDO::getPhone, dto.getPhone())
            .like(Objects.nonNull(dto.getEmail()), OrderDO::getEmail, dto.getEmail())
            .eq(Objects.nonNull(dto.getState()), OrderDO::getState, dto.getState())
            .orderByDesc(OrderDO::getCreateTimeUtc);

    String userId = LoginUserUtil.getLoginUserId();
    String token =
        SecurityFrameworkUtils.obtainAuthorization(
            Objects.requireNonNull(WebFrameworkUtils.getRequest(), "Request cannot be null"),
            securityProperties.getTokenHeader());

    // 不是用户也不是管理员，直接抛异常
    if (StrUtil.isEmpty(userId) && !ADMIN_TOKEN.equals(token)) {
      throw ErrorCodeConstants.UNAUTHORIZED;
    }
    // 是普通用户，则只查询自己的
    if (!ADMIN_TOKEN.equals(token)) {
      queryWrapper = queryWrapper.eq(OrderDO::getUserId, userId);
    }
    PageResult<OrderDO> pageResult = orderMapper.selectPage(dto, queryWrapper);

    return new PageResult<>(
        OrderConvert.INSTANCE.convertList(pageResult.getList()), pageResult.getTotal());
  }
}
