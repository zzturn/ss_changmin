package com.changmin.cm_backend.config.common.pojo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import com.changmin.cm_backend.exceptions.BusinessException;

@Getter
@Setter
public class Resp<T> {
  protected int errorCode;
  protected String errorMessage;
  private T data;
  private boolean success = false;

  private Resp() {}

  public static Resp<Void> ok() {
    return ok(HttpStatus.OK.value(), true, HttpStatus.OK.getReasonPhrase());
  }

  public static Resp<Void> ok(int errorCode, boolean success, String message) {

    Resp<Void> resp = new Resp<>();
    resp.setErrorCode(errorCode);
    resp.setErrorMessage(message);
    resp.setSuccess(success);
    return resp;
  }

  public static <T> Resp<T> data(T data) {
    Resp<T> resp = new Resp<>();
    resp.setErrorCode(HttpStatus.OK.value());
    resp.setErrorMessage(HttpStatus.OK.getReasonPhrase());
    resp.setData(data);
    resp.setSuccess(true);
    return resp;
  }

  public static Resp<Void> error(BusinessException ex) {
    Resp<Void> resp = new Resp<>();
    resp.setErrorCode(ex.getCode());
    resp.setErrorMessage(ex.getMessage());
    return resp;
  }

  public static Resp<Void> error(int errorCode, String errorMessage) {
    Resp<Void> resp = new Resp<>();
    resp.setErrorCode(errorCode);
    resp.setErrorMessage(errorMessage);
    return resp;
  }

  public static Resp<Void> internalServerError(String message) {
    Resp<Void> resp = new Resp<>();
    resp.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
    resp.setErrorMessage(message);
    return resp;
  }
}
