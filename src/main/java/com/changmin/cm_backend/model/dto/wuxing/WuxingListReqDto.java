package com.changmin.cm_backend.model.dto.wuxing;

import com.changmin.cm_backend.model.enums.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.*;
import lombok.*;

@Data
public class WuxingListReqDto {
  // 默认为true,即默认不返回3d信息
  @JsonProperty(value = "ignore_3d_data")
  private Boolean ignore3DData = true;
}
