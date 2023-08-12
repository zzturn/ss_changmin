package com.changmin.cm_backend.config.common.pojo;

import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

@Data
public class PageParam implements Serializable {

  private static final Integer PAGE_NO = 1;
  private static final Integer PAGE_SIZE = 10;

  @ApiModelProperty(value = "页码，从 1 开始", required = true, example = "1")
  @NotNull(message = "Validation_NotNull_PageParam_PageNo")
  @Min(value = 1, message = "Validation_Min_PageParam_PageNo")
  private Integer pageNo = PAGE_NO;

  @ApiModelProperty(value = "每页条数，最大值为 100", required = true, example = "10")
  @NotNull(message = "Validation_NotNull_PageParam_PageSize")
  @Min(value = 1, message = "Validation_Min_PageParam_PageSize")
  @Max(value = 100, message = "Validation_Max_PageParam_PageSize")
  private Integer pageSize = PAGE_SIZE;
}
