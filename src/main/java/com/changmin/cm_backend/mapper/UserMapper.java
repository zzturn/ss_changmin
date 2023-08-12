package com.changmin.cm_backend.mapper;

import com.changmin.cm_backend.config.mybatis.core.mapper.BaseMapperX;
import com.changmin.cm_backend.config.mybatis.core.query.LambdaQueryWrapperX;
import com.changmin.cm_backend.model.pojo.UserDO;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends BaseMapperX<UserDO> {

    default UserDO selectByOpenid(String openid) {
        return selectOne(
            new LambdaQueryWrapperX<UserDO>()
                .eq(UserDO::getOpenid, openid)
                .eq(UserDO::getDeleted, false));
    }
}
