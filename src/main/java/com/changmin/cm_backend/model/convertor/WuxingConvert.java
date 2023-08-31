package com.changmin.cm_backend.model.convertor;


import com.changmin.cm_backend.model.dto.wuxing.*;
import com.changmin.cm_backend.model.pojo.*;
import io.swagger.annotations.*;
import java.util.*;
import lombok.*;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface WuxingConvert {
  WuxingConvert INSTANCE = Mappers.getMapper(WuxingConvert.class);

  WuxingBaseDto convertBase(WuxingDO bean);

  WuxingItemDto convertItem(WuxingDO bean);

  WuxingDO convert(WuxingCreateReqDto bean);

  List<WuxingDO> convertCreateList(List<WuxingCreateReqDto> list);

  WuxingDO convert(WuxingUpdateReqDto bean);
  
  List<WuxingItemDto> convertList(List<WuxingDO> list);
}

