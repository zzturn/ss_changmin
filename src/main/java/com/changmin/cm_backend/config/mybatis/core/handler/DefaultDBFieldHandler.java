package com.changmin.cm_backend.config.mybatis.core.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.changmin.cm_backend.config.mybatis.core.dataobject.BaseDO;
import com.changmin.cm_backend.util.LoginUserUtil;
import org.apache.ibatis.reflection.MetaObject;

import java.util.Date;
import java.util.Objects;

/**
 * 通用参数填充实现类
 *
 * <p>如果没有显式的对通用参数进行赋值，这里会对通用参数进行填充、赋值
 *
 * @author hexiaowu
 */
public class DefaultDBFieldHandler implements MetaObjectHandler {

  @Override
  public void insertFill(MetaObject metaObject) {
    if (Objects.nonNull(metaObject) && metaObject.getOriginalObject() instanceof BaseDO) {
      BaseDO baseDO = (BaseDO) metaObject.getOriginalObject();

      Date current = new Date();
      // 创建时间为空，则以当前时间为插入时间
      if (Objects.isNull(baseDO.getCreateTimeUtc())) {
        baseDO.setCreateTimeUtc(current);
      }
      // 更新时间为空，则以当前时间为更新时间
      if (Objects.isNull(baseDO.getUpdateTimeUtc())) {
        baseDO.setUpdateTimeUtc(current);
      }

      String userId = LoginUserUtil.getLoginUserId();
      // 当前登录用户不为空，创建人为空，则当前登录用户为创建人
      if (Objects.nonNull(userId) && Objects.isNull(baseDO.getCreator())) {
        baseDO.setCreator(userId);
      }
      // 当前登录用户不为空，更新人为空，则当前登录用户为更新人
      if (Objects.nonNull(userId) && Objects.isNull(baseDO.getUpdater())) {
        baseDO.setUpdater(userId);
      }
    }
  }

  @Override
  public void updateFill(MetaObject metaObject) {
    // 更新时间为空，则以当前时间为更新时间
    Object modifyTime = getFieldValByName("updateTimeUtc", metaObject);
    if (Objects.isNull(modifyTime)) {
      setFieldValByName("updateTimeUtc", new Date(), metaObject);
    }

    // 当前登录用户不为空，更新人为空，则当前登录用户为更新人
    Object modifier = getFieldValByName("updater", metaObject);
    String userId = LoginUserUtil.getLoginUserId();
    if (Objects.nonNull(userId) && Objects.isNull(modifier)) {
      setFieldValByName("updater", userId, metaObject);
    }
  }
}
