package com.changmin.cm_backend.config.mybatis.core;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Key Value 的键值对
 *
 * @author changmin源码
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class KeyValue<K, V> {

  private K key;
  private V value;
}
