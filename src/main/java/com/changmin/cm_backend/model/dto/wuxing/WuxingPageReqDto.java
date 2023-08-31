package com.changmin.cm_backend.model.dto.wuxing;

import com.changmin.cm_backend.config.common.pojo.PageParam;
import com.changmin.cm_backend.model.enums.*;
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
  private SortFieldEnum sortField;

  @NotNull
  private Boolean sortAsc = false;
}
