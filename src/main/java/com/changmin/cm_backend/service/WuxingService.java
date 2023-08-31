package com.changmin.cm_backend.service;

import com.changmin.cm_backend.config.common.pojo.PageResult;
import com.changmin.cm_backend.model.dto.wuxing.*;
import java.util.List;

public interface WuxingService {
  WuxingItemDto get(String id);

  void createBatch(List<WuxingCreateReqDto> dto);

  String saveWuxing(WuxingCreateReqDto dto);

  List<WuxingItemDto> getSavedWuxingList();

  void update(WuxingUpdateReqDto dto);

  void deleteBatch(List<String> id);

  PageResult<WuxingItemDto> getPage(WuxingPageReqDto dto);
}
