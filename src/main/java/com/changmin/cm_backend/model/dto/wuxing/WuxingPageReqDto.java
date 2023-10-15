package com.changmin.cm_backend.model.dto.wuxing;

import com.changmin.cm_backend.config.common.pojo.PageParam;
import com.changmin.cm_backend.model.enums.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import javax.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class WuxingPageReqDto extends PageParam {

  /** 用途 */
  private List<YongTuEnum> yongTu;

  private List<AreaEnum> area;

  private List<FengGeEnum> fengGe;

  private List<RoofTypesEnum> roof;

  private List<RoomCountEnum> roomCount;

  private Boolean canCustom;

  @NotNull
  private SortFieldEnum sortField = SortFieldEnum.CreateTimeUtc;

  private Boolean sortAsc = false;

  // 默认为 false，即默认过滤
  private Boolean disableCompleteFilter = false;

  // 默认为true,即默认不返回3d信息
  @JsonProperty(value = "ignore_3d_data")
  private Boolean ignore3DData = true;
}
