package com.changmin.cm_backend.controller;

import com.changmin.cm_backend.config.common.pojo.PageResult;
import com.changmin.cm_backend.config.common.pojo.Resp;
import com.changmin.cm_backend.exceptions.BusinessException;
import com.changmin.cm_backend.model.dto.order.*;
import com.changmin.cm_backend.service.*;
import io.swagger.annotations.*;
import java.util.Objects;
import javax.annotation.Resource;
import javax.annotation.security.PermitAll;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/order")
@Api(value = "OrderController", tags = "order")
public class OrderController {
  @Resource private OrderService orderService;

  @ApiOperation(value = "根据id获取，管理员和用户均可调用")
  @PermitAll
  @GetMapping("/{id}")
  public Resp<OrderItemDto> get(@PathVariable(value = "id") Long id) {
    return Resp.data(orderService.get(id));
  }

  @ApiOperation(value = "创建订单")
  @PostMapping("/create")
  public Resp<Long> create(@Validated @RequestBody @NotNull OrderCreateReqDto dto) {
    if (Objects.isNull(dto.getEmail()) && Objects.isNull(dto.getPhone())) {
      throw new BusinessException("邮箱或者电话不能为空");
    }
    return Resp.data(orderService.create(dto));
  }

  @PutMapping("/update")
  @ApiOperation(value = "用户修改订单")
  public Resp<Void> update(@Valid @RequestBody OrderUpdateStateReqDto dto) {
    orderService.update(dto);
    return Resp.ok();
  }
  @PutMapping("/admin/update")
  @PermitAll
  @ApiOperation(value = "管理员修改订单")
  public Resp<Void> updateByAdmin(@Valid @RequestBody OrderUpdateByAdminReqDto dto) {
    orderService.updateByAdmin(dto);
    return Resp.ok();
  }

  @DeleteMapping("/delete/{id}")
  @ApiOperation(value = "删除")
  public Resp<Void> delete(@PathVariable(value = "id") Long id) {
    orderService.delete(id);
    return Resp.ok();
  }

  @PostMapping("/page")
  @PermitAll
  @ApiOperation(value = "获得分页列表")
  public Resp<PageResult<OrderItemDto>> getPage(@Valid @RequestBody OrderPageReqDto dto) {
    // 获得用户分页列表
    return Resp.data(orderService.getPage(dto));
  }
}
