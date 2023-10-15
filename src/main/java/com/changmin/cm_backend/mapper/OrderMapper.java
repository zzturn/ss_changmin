package com.changmin.cm_backend.mapper;

import com.changmin.cm_backend.config.mybatis.core.mapper.BaseMapperX;
import com.changmin.cm_backend.model.pojo.*;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderMapper extends BaseMapperX<OrderDO> {

    List<UserWithOrderCountDO> getUserWithOrderCount();
}

