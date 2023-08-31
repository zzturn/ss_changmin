package com.changmin.cm_backend.config;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.changmin.cm_backend.config.common.pojo.Resp;
import com.changmin.cm_backend.exceptions.BusinessException;
import com.changmin.cm_backend.exceptions.ErrorCodeConstants;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.ClientAbortException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 统一处理异常
 *
 * @author ouzhenxiong
 * @since 2021/7/6
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(ConstraintViolationException.class)
  public Resp badRequest(ConstraintViolationException ex) {
    log.error("ConstraintViolationException error", ex);
    return Resp.error(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(BindException.class)
  public Resp badRequest(BindException ex) {
    String msg = Objects.requireNonNull(ex.getBindingResult().getFieldError()).getDefaultMessage();
    log.error("BindException error {}", msg, ex);
    return Resp.error(HttpStatus.BAD_REQUEST.value(), msg);
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public Resp badRequest(MethodArgumentNotValidException ex) {
    Map<String, String> errors = new HashMap<>();
    AtomicReference<String> errorMsg = new AtomicReference<>();
    ex.getBindingResult()
        .getFieldErrors()
        .forEach(
            error -> {
              errorMsg.set(error.getDefaultMessage());
              errors.put(error.getField(), error.getDefaultMessage());
            });
    String localeMessage = errorMsg.get();
    log.error("用户表单参数错误, 详情为：{} ", localeMessage, ex);
    // 这里换了一个错误码，前端会对400这个错误码进行统一处理，会丢失掉报错信息
    return Resp.error(HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS.value(), localeMessage);
  }

  /**
   * 统一处理用户请求传参错误
   *
   * @param ex
   * @return
   */
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(ServletRequestBindingException.class)
  public Resp badRequestArguments(ServletRequestBindingException ex) {
    log.error(ex.getMessage(), ex);
    return Resp.error(-1, ex.getMessage());
  }

  @ResponseStatus(HttpStatus.OK)
  @ExceptionHandler(BusinessException.class)
  public Resp<Void> error(BusinessException ex) {
    String message = ex.getMessage();
    if (Objects.nonNull(ex.getArgs()) && !ArrayUtil.isEmpty(ex.getArgs())) {
      message = StrUtil.format(message, ex.getArgs());
    } else {
      message = StrUtil.replace(message, "{}", "").trim();
    }
    log.error("BusinessException, 详情为：{} ", message, ex);
    return Resp.error(ex.getCode(), message);
  }

  @ExceptionHandler(RuntimeException.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public Resp<Void> serverError(RuntimeException ex) {
    log.error(ex.getMessage(), ex);
    return Resp.internalServerError("SYSTEM_ERROR");
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler({HttpMessageNotReadableException.class})
  public Resp<Void> jsonParseException(Exception ex) {
    // jackson的报错需要捕获
    log.error("HttpMessageNotReadableException", ex);
    return Resp.error(
        HttpStatus.BAD_REQUEST.value(),
        ErrorCodeConstants.WRONG_PARAMETER_TYPE.getMessage());
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
  public Resp<Void> methodNotSupportedException(Exception ex) {
    // jackson的报错需要捕获
    log.error("HttpRequestMethodNotSupportedException", ex);
    return Resp.error(
        HttpStatus.BAD_REQUEST.value(),
        ErrorCodeConstants.API_NOT_FOUND.getMessage());
  }

  /**
   * 处理 Spring Security 权限不足的异常
   *
   * <p>来源是，使用 @PreAuthorize 注解，AOP 进行权限拦截
   */
  // todo 权限校验兜底返回
  @ExceptionHandler(value = AccessDeniedException.class)
  public Resp<Void> accessDeniedExceptionHandler(HttpServletRequest req, AccessDeniedException ex) {
    String message = ErrorCodeConstants.FORBIDDEN.getMessage();
    return Resp.error(HttpStatus.FORBIDDEN.value(), message);
  }

  @ExceptionHandler({ClientAbortException.class})
  public void clientException(Exception ex) {
    // magic, do not touch
    // 忽略掉 EOF/BROKEN PIPE 错误, 客户端主动断开连接的情况下触发
  }
}
