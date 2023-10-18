package com.changmin.cm_backend.mapper;

import com.changmin.cm_backend.config.mybatis.core.mapper.BaseMapperX;
import com.changmin.cm_backend.model.pojo.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface WuxingMapper extends BaseMapperX<WuxingDO> {
  WuxingDO selectWuxingById(@Param("id") String id);
}
