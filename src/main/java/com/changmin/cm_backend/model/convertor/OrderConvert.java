package com.changmin.cm_backend.model.convertor;


import com.changmin.cm_backend.model.dto.order.*;
import com.changmin.cm_backend.model.pojo.*;
import java.util.*;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderConvert {
  OrderConvert INSTANCE = Mappers.getMapper(OrderConvert.class);

  OrderBaseDto convertBase(OrderDO bean);

  OrderItemDto convertItem(OrderDO bean);

  OrderDO convert(OrderCreateReqDto bean);

  OrderDO convert(OrderUpdateStateReqDto bean);

  OrderDO convert(OrderUpdateByAdminReqDto bean);

  List<OrderItemDto> convertList(List<OrderDO> list);
}

