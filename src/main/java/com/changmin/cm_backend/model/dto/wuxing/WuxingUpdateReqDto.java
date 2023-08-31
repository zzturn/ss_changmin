package com.changmin.cm_backend.model.dto.wuxing;

import io.swagger.annotations.*;
import lombok.*;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class WuxingUpdateReqDto extends WuxingBaseDto{

    @ApiModelProperty(value = "")
    private String id;
}

