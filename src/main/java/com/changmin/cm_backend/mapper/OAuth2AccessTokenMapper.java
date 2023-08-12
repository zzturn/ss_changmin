package com.changmin.cm_backend.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.changmin.cm_backend.config.mybatis.core.mapper.BaseMapperX;
import com.changmin.cm_backend.model.pojo.AccessTokenDO;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OAuth2AccessTokenMapper extends BaseMapperX<AccessTokenDO> {

  default AccessTokenDO selectByAccessToken(String accessToken) {
    return selectOne(AccessTokenDO::getAccessToken, accessToken);
  }

  default int updateExpiresTimeUtcByAccessToken(String token, Date date) {
    return update(
        AccessTokenDO.builder().expiresTimeUtc(date).build(),
        new LambdaUpdateWrapper<AccessTokenDO>().eq(AccessTokenDO::getAccessToken, token));
  }
}
