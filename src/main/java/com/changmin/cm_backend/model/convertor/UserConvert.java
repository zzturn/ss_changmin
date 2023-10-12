package com.changmin.cm_backend.model.convertor;


import com.changmin.cm_backend.model.dto.user.*;
import com.changmin.cm_backend.model.pojo.*;
import java.util.*;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserConvert {
  UserConvert INSTANCE = Mappers.getMapper(UserConvert.class);

  UserBaseDto convertBase(UserDO bean);


  UserDO convert(UserUpdateReqDto bean);
  
}

