package com.changmin.cm_backend.exceptions;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * <p>现在如果要格式化message，请手动调用formatMessage方法
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BusinessException extends RuntimeException {

  private int code = 400;
  private String message;
  private String internalMessage;
  private Object[] args;

  public BusinessException(int code, String message, String internalMessage) {
    super();
    this.code = code;
    this.message = message;
    this.internalMessage = internalMessage;
  }

  public BusinessException(String internalMessage) {
    super();
    this.message = internalMessage;
    this.internalMessage = internalMessage;
  }

  public BusinessException(String internalMessage, Throwable e) {
    super(internalMessage, e);
    this.internalMessage = internalMessage;
  }

  public BusinessException(BusinessException e) {
    super();
    this.code = e.getCode();
    this.message = e.getMessage();
    this.internalMessage = e.getInternalMessage();
    this.args = e.getArgs();
  }

  public BusinessException(BusinessException e, Object... args) {
    super();
    this.code = e.getCode();
    this.message = e.getMessage();
    this.internalMessage = e.getInternalMessage();
    this.args = args;
  }

  public BusinessException formatMessage() {
    BusinessException ex = new BusinessException(this);
    if (ArrayUtil.isEmpty(this.args)) {
      return ex;
    }
    ex.setMessage(StrUtil.format(ex.message, ex.args));
    ex.setInternalMessage(StrUtil.format(ex.message, ex.args));
    return ex;
  }
}
