package com.changmin.cm_backend.config.mybatis.core.type;

import com.fasterxml.jackson.databind.node.ArrayNode;
import org.apache.ibatis.type.MappedTypes;

/**
 * @author ren
 * @since 2021/7/9
 */
@MappedTypes(ArrayNode.class)
public class JsonArrayTypeHandler extends BaseJsonTypeHandler<ArrayNode> {}
