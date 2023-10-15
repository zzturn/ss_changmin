package com.changmin.cm_backend.model.dto.user;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserWithOrderCountDto extends UserBaseDto {

  private String id;

  private Integer reservedOrderCount;
}
