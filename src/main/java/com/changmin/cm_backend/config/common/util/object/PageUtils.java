package com.changmin.cm_backend.config.common.util.object;

import com.changmin.cm_backend.config.common.pojo.PageParam;

/**
 * {@link com.changmin.cm_backend.config.common.pojo.PageParam} 工具类
 *
 * @author changmin源码
 */
public class PageUtils {

  public static int getStart(PageParam pageParam) {
    return (pageParam.getPageNo() - 1) * pageParam.getPageSize();
  }
}
