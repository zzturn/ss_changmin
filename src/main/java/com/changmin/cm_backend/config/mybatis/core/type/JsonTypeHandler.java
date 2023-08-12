package com.changmin.cm_backend.config.mybatis.core.type;

import com.fasterxml.jackson.databind.JsonNode;
import org.apache.ibatis.type.MappedTypes;

/**
 * @author ren
 * @since 2021/7/9
 */
@MappedTypes(JsonNode.class)
public class JsonTypeHandler extends BaseJsonTypeHandler<JsonNode> {}
