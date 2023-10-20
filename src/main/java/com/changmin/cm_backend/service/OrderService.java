package com.changmin.cm_backend.service;

import com.changmin.cm_backend.config.common.pojo.PageResult;
import com.changmin.cm_backend.model.dto.order.*;

public interface OrderService {
  OrderItemDto get(Long id);

  Long create(OrderCreateReqDto dto);

  void update(OrderUpdateStateReqDto dto);

  void updateByAdmin(OrderUpdateByAdminReqDto dto);

  void delete(Long id);

  PageResult<OrderItemDto> getPage(OrderPageReqDto dto);
}
