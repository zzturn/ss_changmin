package com.changmin.cm_backend.config.common.util.json;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.changmin.cm_backend.component.ObjectMapperFactory;
import com.changmin.cm_backend.exceptions.BusinessException;
import com.changmin.cm_backend.exceptions.ErrorCodeConstants;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * JSON 工具类
 *
 * @author changmin源码
 */
@UtilityClass
@Slf4j
public class JsonUtils {

  private static ObjectMapper objectMapper;

  static {
    objectMapper = ObjectMapperFactory.create();
  }

  /**
   * 初始化 objectMapper 属性
   *
   * <p>通过这样的方式，使用 Spring 创建的 ObjectMapper Bean
   *
   * @param objectMapper ObjectMapper 对象
   */
  public static void init(ObjectMapper objectMapper) {
    JsonUtils.objectMapper = objectMapper;
  }

  @SneakyThrows
  public static String toJsonString(Object object) {
    return objectMapper.writeValueAsString(object);
  }

  @SneakyThrows
  public static byte[] toJsonByte(Object object) {
    return objectMapper.writeValueAsBytes(object);
  }

  @SneakyThrows
  public static String toJsonPrettyString(Object object) {
    return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
  }

  public static <T> T parseObject(String text, Class<T> clazz) {
    if (StrUtil.isEmpty(text)) {
      return null;
    }
    try {
      return objectMapper.readValue(text, clazz);
    } catch (IOException e) {
      log.error("json parse err,json:{}", text, e);
      throw new BusinessException(ErrorCodeConstants.JSON_PARSE_ERROR);
    }
  }

  /**
   * 将字符串解析成指定类型的对象 使用 {@link #parseObject(String, Class)} 时，在@JsonTypeInfo(use =
   * JsonTypeInfo.Id.CLASS) 的场景下， 如果 text 没有 class 属性，则会报错。此时，使用这个方法，可以解决。
   *
   * @param text 字符串
   * @param clazz 类型
   * @return 对象
   */
  public static <T> T parseObject2(String text, Class<T> clazz) {
    if (StrUtil.isEmpty(text)) {
      return null;
    }
    return JSONUtil.toBean(text, clazz);
  }

  public static <T> T parseObject(byte[] bytes, Class<T> clazz) {
    if (ArrayUtil.isEmpty(bytes)) {
      return null;
    }
    try {
      return objectMapper.readValue(bytes, clazz);
    } catch (IOException e) {
      log.error("json parse err,json:{}", bytes, e);
      throw new BusinessException(ErrorCodeConstants.JSON_PARSE_ERROR);
    }
  }

  public static <T> T parseObject(String text, TypeReference<T> typeReference) {
    try {
      return objectMapper.readValue(text, typeReference);
    } catch (IOException e) {
      log.error("json parse err,json:{}", text, e);
      throw new BusinessException(ErrorCodeConstants.JSON_PARSE_ERROR);
    }
  }

  public static <T extends JsonNode> T parseObject(byte[] resultSBytes, TypeReference<T> clazz) {
    try {
      return objectMapper.readValue(resultSBytes, clazz);
    } catch (IOException e) {
      log.error("json parse err,json:{}", resultSBytes, e);
      throw new BusinessException(ErrorCodeConstants.JSON_PARSE_ERROR);
    }
  }

  public static <T> List<T> parseArray(String text, Class<T> clazz) {
    if (StrUtil.isEmpty(text)) {
      return new ArrayList<>();
    }
    try {
      return objectMapper.readValue(
          text, objectMapper.getTypeFactory().constructCollectionType(List.class, clazz));
    } catch (IOException e) {
      log.error("json parse err,json:{}", text, e);
      throw new BusinessException(ErrorCodeConstants.JSON_PARSE_ERROR);
    }
  }

  public static JsonNode parseTree(String text) {
    try {
      return objectMapper.readTree(text);
    } catch (IOException e) {
      log.error("json parse err,json:{}", text, e);
      throw new BusinessException(ErrorCodeConstants.JSON_PARSE_ERROR);
    }
  }

  public static JsonNode parseTree(byte[] text) {
    try {
      return objectMapper.readTree(text);
    } catch (IOException e) {
      log.error("json parse err,json:{}", text, e);
      throw new BusinessException(ErrorCodeConstants.JSON_PARSE_ERROR);
    }
  }

  public static <T> JsonNode valueToTree(T text) {
    return objectMapper.valueToTree(text);
  }

  public static <T> T treeToValue(JsonNode text, Class<T> clazz) {
    try {
      return objectMapper.treeToValue(text, clazz);
    } catch (JsonProcessingException e) {
      log.error("json parse err,json:{}", text, e);
      throw new BusinessException(ErrorCodeConstants.JSON_PARSE_ERROR);
    }
  }

  public static boolean isJson(String text) {
    return JSONUtil.isTypeJSON(text);
  }

  public static <T> T readValue(String content, TypeReference<T> valueTypeRef) {
    try {
      return objectMapper.readValue(content, valueTypeRef);
    } catch (JsonProcessingException e) {
      log.error("json parse err,json:{}", content, e);
      throw new BusinessException(ErrorCodeConstants.JSON_PARSE_ERROR);
    }
  }

  public static <T> T readValue(JsonNode text, TypeReference<T> valueTypeRef) {
    return readValue(text.toString(), valueTypeRef);
  }
}
