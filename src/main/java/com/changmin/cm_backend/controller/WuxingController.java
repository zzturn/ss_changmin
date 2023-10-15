package com.changmin.cm_backend.controller;

import com.changmin.cm_backend.config.common.pojo.PageResult;
import com.changmin.cm_backend.config.common.pojo.Resp;
import com.changmin.cm_backend.model.dto.wuxing.*;
import com.changmin.cm_backend.service.*;
import io.swagger.annotations.*;
import java.util.List;
import javax.annotation.Resource;
import javax.annotation.security.PermitAll;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/wuxing")
@Api(value = "WuxingController", tags = "wuxing")
public class WuxingController {
  @Resource private WuxingService wuxingService;

  @ApiOperation(value = "根据 id 获取")
  @PermitAll
  @GetMapping("/{id}")
  public Resp<WuxingItemDto> get(@PathVariable(value = "id") String id) {
    return Resp.data(wuxingService.get(id));
  }

  @ApiOperation(value = "用户保存屋形")
  @PostMapping()
  public Resp<String> save(@Valid @RequestBody WuxingCreateReqDto dto) {
    return Resp.data(wuxingService.saveWuxing(dto));
  }

  @DeleteMapping()
  @ApiOperation(value = "删除")
  public Resp<Boolean> deleteSaved(@RequestBody List<String> ids) {
    wuxingService.deleteSavedWuxing(ids);
    return Resp.data(true);
  }

  @ApiOperation(value = "获取保存的屋形列表")
  @PostMapping("/list")
  public Resp<List<WuxingItemDto>> getSavedList(@RequestBody WuxingListReqDto dto) {
    return Resp.data(wuxingService.getSavedWuxingList(dto));
  }

  @ApiOperation(value = "批量上传")
  @PermitAll
  @PostMapping("/batch_upload")
  public Resp<List<String>> create(@Valid @RequestBody List<WuxingCreateReqDto> dto) {
    return Resp.data(wuxingService.createBatch(dto));
  }

  @PutMapping("/update")
  @PermitAll
  @ApiOperation(value = "修改")
  public Resp<Void> update(@Valid @RequestBody WuxingUpdateReqDto dto) {
    wuxingService.update(dto);
    return Resp.ok();
  }

  @DeleteMapping("/batch_delete")
  @PermitAll
  @ApiOperation(value = "删除")
  public Resp<Void> delete(@RequestBody List<String> ids) {
    wuxingService.deleteBatch(ids);
    return Resp.ok();
  }

  @PostMapping("/page")
  @PermitAll
  @ApiOperation(value = "获得分页列表")
  public Resp<PageResult<WuxingItemDto>> getPage(@Valid @RequestBody WuxingPageReqDto dto) {
    // 获得用户分页列表
    return Resp.data(wuxingService.getPage(dto));
  }
}
