package com.changmin.cm_backend.model.dto.wuxing;

import io.swagger.annotations.*;
import java.util.Date;
import lombok.*;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class WuxingItemDto extends WuxingBaseDto{

    @ApiModelProperty(value = "")
    private String id;

    @ApiModelProperty(value = "")
    private Boolean deleted;

    @ApiModelProperty(value = "")
    private Date createTimeUtc;

}

