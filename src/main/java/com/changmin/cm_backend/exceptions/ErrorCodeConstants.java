package com.changmin.cm_backend.exceptions;

/**
 * System 错误码枚举类
 *
 * <p>system 系统，使用 1-002-000-000 段
 */
public interface ErrorCodeConstants {
  static BusinessException newBusinessException(int code, String msg) {
    return new BusinessException(code, msg, msg);
  }
  // 通用错误码，沿用http的标准错误码
  BusinessException WRONG_PARAMETER_TYPE =
      newBusinessException(400, "EXCEPTION.WRONG_PARAMETER_TYPE");
  BusinessException API_NOT_FOUND = newBusinessException(400, "EXCEPTION.API_NOT_FOUND");
  BusinessException UNAUTHORIZED = newBusinessException(401, "EXCEPTION.UNAUTHORIZED");
  BusinessException TOKEN_NOT_EXIST = newBusinessException(401, "EXCEPTION.TOKEN_NOT_EXIST");
  BusinessException TOKEN_EXPIRED = newBusinessException(401, "EXCEPTION.TOKEN_EXPIRED");

  BusinessException FORBIDDEN = newBusinessException(403, "EXCEPTION.FORBIDDEN");
  BusinessException NOT_FOUND = newBusinessException(404, "EXCEPTION.NOT_FOUND");
  BusinessException MODEL_NOT_FOUND = newBusinessException(404, "EXCEPTION.MODEL_NOT_FOUND");

  // ========== AUTH 模块 1002000000 ==========
  BusinessException AUTH_LOGIN_BAD_CREDENTIALS =
      newBusinessException(1002000000, "EXCEPTION.AUTH_LOGIN_BAD_CREDENTIALS");
  BusinessException AUTH_LOGIN_USER_DISABLED =
      newBusinessException(1002000001, "EXCEPTION.AUTH_LOGIN_USER_DISABLED");
  BusinessException AUTH_LOGIN_CAPTCHA_CODE_ERROR =
      newBusinessException(1002000004, "EXCEPTION.AUTH_LOGIN_CAPTCHA_CODE_ERROR");
  BusinessException AUTH_THIRD_LOGIN_NOT_BIND =
      newBusinessException(1002000005, "EXCEPTION.AUTH_THIRD_LOGIN_NOT_BIND");
  BusinessException AUTH_TOKEN_EXPIRED =
      newBusinessException(1002000006, "EXCEPTION.AUTH_TOKEN_EXPIRED");
  BusinessException AUTH_MOBILE_NOT_EXISTS =
      newBusinessException(1002000007, "EXCEPTION.AUTH_MOBILE_NOT_EXISTS");
  BusinessException AUTH_NO_ADMIN = newBusinessException(1002000008, "EXCEPTION.AUTH_NO_ADMIN");
  BusinessException AUTH_NO_LOGIN = newBusinessException(1002000009, "EXCEPTION.AUTH_NO_LOGIN");
  BusinessException COMMON_FORBIDDEN =
      newBusinessException(1002000010, "EXCEPTION.COMMON_FORBIDDEN");

  BusinessException AUTH_MINIAPP_SESSION_PARSE_ERROR =
      newBusinessException(1002000011, "EXCEPTION.AUTH_MINIAPP_SESSION_PARSE_ERROR");
  BusinessException AUTH_MINIAPP_MOBILE_PARSE_ERROR =
      newBusinessException(1002000012, "EXCEPTION.AUTH_MINIAPP_MOBILE_PARSE_ERROR");
  BusinessException AUTH_MINIAPP_USER_INFO_CHECK_ERROR =
      newBusinessException(1002000013, "EXCEPTION.AUTH_MINIAPP_USER_INFO_CHECK_ERROR");

  // ========== 菜单模块 1002001000 ==========
  BusinessException MENU_NAME_DUPLICATE =
      newBusinessException(1002001000, "EXCEPTION.MENU_NAME_DUPLICATE");
  BusinessException MENU_PARENT_NOT_EXISTS =
      newBusinessException(1002001001, "EXCEPTION.MENU_PARENT_NOT_EXISTS");
  BusinessException MENU_PARENT_ERROR =
      newBusinessException(1002001002, "EXCEPTION.MENU_PARENT_ERROR");
  BusinessException MENU_NOT_EXISTS = newBusinessException(1002001003, "EXCEPTION.MENU_NOT_EXISTS");
  BusinessException MENU_EXISTS_CHILDREN =
      newBusinessException(1002001004, "EXCEPTION.MENU_EXISTS_CHILDREN");
  BusinessException MENU_PARENT_NOT_DIR_OR_MENU =
      newBusinessException(1002001005, "EXCEPTION.MENU_PARENT_NOT_DIR_OR_MENU");

  // ========== 角色模块 1002002000 ==========
  BusinessException ROLE_NOT_EXISTS = newBusinessException(1002002000, "EXCEPTION.ROLE_NOT_EXISTS");
  BusinessException ROLE_NAME_DUPLICATE =
      newBusinessException(1002002001, "EXCEPTION.ROLE_NAME_DUPLICATE");
  BusinessException ROLE_CODE_DUPLICATE =
      newBusinessException(1002002002, "EXCEPTION.ROLE_CODE_DUPLICATE");
  BusinessException ROLE_CAN_NOT_UPDATE_SYSTEM_TYPE_ROLE =
      newBusinessException(1002002003, "EXCEPTION.ROLE_CAN_NOT_UPDATE_SYSTEM_TYPE_ROLE");
  BusinessException ROLE_IS_DISABLE = newBusinessException(1002002004, "EXCEPTION.ROLE_IS_DISABLE");
  BusinessException ROLE_ADMIN_CODE_ERROR =
      newBusinessException(1002002005, "EXCEPTION.ROLE_ADMIN_CODE_ERROR");

  // ========== 用户模块 1002003000 ==========
  BusinessException USER_USERNAME_EXISTS =
      newBusinessException(1002003000, "EXCEPTION.USER_USERNAME_EXISTS");
  BusinessException USER_MOBILE_EXISTS =
      newBusinessException(1002003001, "EXCEPTION.USER_MOBILE_EXISTS");
  BusinessException USER_EMAIL_EXISTS =
      newBusinessException(1002003002, "EXCEPTION.USER_EMAIL_EXISTS");
  BusinessException USER_NOT_EXISTS = newBusinessException(1002003003, "EXCEPTION.USER_NOT_EXISTS");
  BusinessException USER_IMPORT_LIST_IS_EMPTY =
      newBusinessException(1002003004, "EXCEPTION.USER_IMPORT_LIST_IS_EMPTY");
  BusinessException USER_PASSWORD_FAILED =
      newBusinessException(1002003005, "EXCEPTION.USER_PASSWORD_FAILED");
  BusinessException USER_IS_DISABLE = newBusinessException(1002003006, "EXCEPTION.USER_IS_DISABLE");
  BusinessException USER_COUNT_MAX = newBusinessException(1002003008, "EXCEPTION.USER_COUNT_MAX");
  BusinessException USER_TENANT_DATA_PERMISSION_FAIL =
      newBusinessException(1002003009, "EXCEPTION.USER_TENANT_DATA_PERMISSION_FAIL");
  BusinessException USER_NO_TENANT_PERMISSION =
      newBusinessException(1002003010, "EXCEPTION.USER_NO_TENANT_PERMISSION");
  BusinessException USER_MOBILE_NOT_VALID =
      newBusinessException(1002003011, "EXCEPTION.USER_MOBILE_NOT_VALID");

  BusinessException USER_EMAIL_NOT_VALID =
      newBusinessException(1002003011, "EXCEPTION.USER_EMAIL_NOT_VALID");
  BusinessException USER_ALTER_MOBILE_REPEAT =
      newBusinessException(1002003012, "EXCEPTION.USER_ALTER_MOBILE_REPEAT");
  BusinessException USER_STATUS_BANNED =
      newBusinessException(1002003013, "EXCEPTION.USER_STATUS_BANNED");
  BusinessException USER_SOCIAL_TYPE_ALREADY_BIND =
      newBusinessException(1002003014, "EXCEPTION.USER_SOCIAL_TYPE_ALREADY_BIND");
  BusinessException USER_THIRD_ACCOUNT_ALREADY_BIND =
      newBusinessException(1002003014, "EXCEPTION.USER_THIRD_ACCOUNT_ALREADY_BIND");

  // ========== 部门模块 1002004000 ==========
  BusinessException DEPT_NAME_DUPLICATE =
      newBusinessException(1002004000, "EXCEPTION.DEPT_NAME_DUPLICATE");
  BusinessException DEPT_PARENT_NOT_EXITS =
      newBusinessException(1002004001, "EXCEPTION.DEPT_PARENT_NOT_EXITS");
  BusinessException DEPT_NOT_FOUND = newBusinessException(1002004002, "EXCEPTION.DEPT_NOT_FOUND");
  BusinessException DEPT_EXITS_CHILDREN =
      newBusinessException(1002004003, "EXCEPTION.DEPT_EXITS_CHILDREN");
  BusinessException DEPT_PARENT_ERROR =
      newBusinessException(1002004004, "EXCEPTION.DEPT_PARENT_ERROR");
  BusinessException DEPT_EXISTS_USER =
      newBusinessException(1002004005, "EXCEPTION.DEPT_EXISTS_USER");
  BusinessException DEPT_NOT_ENABLE = newBusinessException(1002004006, "EXCEPTION.DEPT_NOT_ENABLE");
  BusinessException DEPT_PARENT_IS_CHILD =
      newBusinessException(1002004007, "EXCEPTION.DEPT_PARENT_IS_CHILD");
  BusinessException DEPT_NO_UPDATE_ROOT =
      newBusinessException(1002004008, "EXCEPTION.DEPT_NO_UPDATE_ROOT");
  BusinessException DEPT_NO_TENANT_PERMISSION =
      newBusinessException(1002004009, "EXCEPTION.DEPT_NO_TENANT_PERMISSION");

  // ========== 岗位模块 1002005000 ==========
  BusinessException POST_NOT_FOUND = newBusinessException(1002005000, "EXCEPTION.POST_NOT_FOUND");
  BusinessException POST_NOT_ENABLE = newBusinessException(1002005001, "EXCEPTION.POST_NOT_ENABLE");
  BusinessException POST_NAME_DUPLICATE =
      newBusinessException(1002005002, "EXCEPTION.POST_NAME_DUPLICATE");
  BusinessException POST_CODE_DUPLICATE =
      newBusinessException(1002005003, "EXCEPTION.POST_CODE_DUPLICATE");

  // ========== 字典类型 1002006000 ==========
  BusinessException DICT_TYPE_NOT_EXISTS =
      newBusinessException(1002006001, "EXCEPTION.DICT_TYPE_NOT_EXISTS");
  BusinessException DICT_TYPE_NOT_ENABLE =
      newBusinessException(1002006002, "EXCEPTION.DICT_TYPE_NOT_ENABLE");
  BusinessException DICT_TYPE_NAME_DUPLICATE =
      newBusinessException(1002006003, "EXCEPTION.DICT_TYPE_NAME_DUPLICATE");
  BusinessException DICT_TYPE_TYPE_DUPLICATE =
      newBusinessException(1002006004, "EXCEPTION.DICT_TYPE_TYPE_DUPLICATE");
  BusinessException DICT_TYPE_HAS_CHILDREN =
      newBusinessException(1002006005, "EXCEPTION.DICT_TYPE_HAS_CHILDREN");

  // ========== 字典数据 1002007000 ==========
  BusinessException DICT_DATA_NOT_EXISTS =
      newBusinessException(1002007001, "EXCEPTION.DICT_DATA_NOT_EXISTS");
  BusinessException DICT_DATA_NOT_ENABLE =
      newBusinessException(1002007002, "EXCEPTION.DICT_DATA_NOT_ENABLE");
  BusinessException DICT_DATA_VALUE_DUPLICATE =
      newBusinessException(1002007003, "EXCEPTION.DICT_DATA_VALUE_DUPLICATE");

  // ========== 通知公告 1002008000 ==========
  BusinessException NOTICE_NOT_FOUND =
      newBusinessException(1002008001, "EXCEPTION.NOTICE_NOT_FOUND");

  // ========== 短信渠道 1002011000 ==========
  BusinessException SMS_CHANNEL_NOT_EXISTS =
      newBusinessException(1002011000, "EXCEPTION.SMS_CHANNEL_NOT_EXISTS");
  BusinessException SMS_CHANNEL_DISABLE =
      newBusinessException(1002011001, "EXCEPTION.SMS_CHANNEL_DISABLE");
  BusinessException SMS_CHANNEL_HAS_CHILDREN =
      newBusinessException(1002011002, "EXCEPTION.SMS_CHANNEL_HAS_CHILDREN");

  // ========== 短信模板 1002012000 ==========
  BusinessException SMS_TEMPLATE_NOT_EXISTS =
      newBusinessException(1002012000, "EXCEPTION.SMS_TEMPLATE_NOT_EXISTS");
  BusinessException SMS_TEMPLATE_CODE_DUPLICATE =
      newBusinessException(1002012001, "EXCEPTION.SMS_TEMPLATE_CODE_DUPLICATE");

  // ========== 短信发送 1002013000 ==========
  BusinessException SMS_SEND_MOBILE_NOT_EXISTS =
      newBusinessException(1002013000, "EXCEPTION.SMS_SEND_MOBILE_NOT_EXISTS");
  BusinessException SMS_SEND_MOBILE_TEMPLATE_PARAM_MISS =
      newBusinessException(1002013001, "EXCEPTION.SMS_SEND_MOBILE_TEMPLATE_PARAM_MISS");
  BusinessException SMS_SEND_TEMPLATE_NOT_EXISTS =
      newBusinessException(1002013002, "EXCEPTION.SMS_SEND_TEMPLATE_NOT_EXISTS");

  // ========== 短信验证码 1002014000 ==========
  BusinessException SMS_CODE_NOT_FOUND =
      newBusinessException(1002014000, "EXCEPTION.SMS_CODE_NOT_FOUND");
  BusinessException SMS_CODE_EXPIRED =
      newBusinessException(1002014001, "EXCEPTION.SMS_CODE_EXPIRED");
  BusinessException SMS_CODE_USED = newBusinessException(1002014002, "EXCEPTION.SMS_CODE_USED");
  BusinessException SMS_CODE_NOT_CORRECT =
      newBusinessException(1002014003, "EXCEPTION.SMS_CODE_NOT_CORRECT");
  BusinessException SMS_CODE_EXCEED_SEND_MAXIMUM_QUANTITY_PER_DAY =
      newBusinessException(1002014004, "EXCEPTION.SMS_CODE_EXCEED_SEND_MAXIMUM_QUANTITY_PER_DAY");
  BusinessException SMS_CODE_SEND_TOO_FAST =
      newBusinessException(1002014005, "EXCEPTION.SMS_CODE_SEND_TOO_FAST");
  BusinessException SMS_CODE_IS_EXISTS =
      newBusinessException(1002014006, "EXCEPTION.SMS_CODE_IS_EXISTS");
  BusinessException SMS_CODE_IS_UNUSED =
      newBusinessException(1002014007, "EXCEPTION.SMS_CODE_IS_UNUSED");

  // ========== 服务商信息 1002015000 ==========
  BusinessException TENANT_NOT_EXISTS =
      newBusinessException(1002015000, "EXCEPTION.TENANT_NOT_EXISTS");
  BusinessException TENANT_DISABLE = newBusinessException(1002015001, "EXCEPTION.TENANT_DISABLE");
  BusinessException TENANT_EXPIRE = newBusinessException(1002015002, "EXCEPTION.TENANT_EXPIRE");
  BusinessException TENANT_CAN_NOT_UPDATE_SYSTEM =
      newBusinessException(1002015003, "EXCEPTION.TENANT_CAN_NOT_UPDATE_SYSTEM");

  // ========== 服务商套餐 1002016000 ==========
  BusinessException TENANT_PACKAGE_NOT_EXISTS =
      newBusinessException(1002016000, "EXCEPTION.TENANT_PACKAGE_NOT_EXISTS");
  BusinessException TENANT_PACKAGE_USED =
      newBusinessException(1002016001, "EXCEPTION.TENANT_PACKAGE_USED");
  BusinessException TENANT_PACKAGE_DISABLE =
      newBusinessException(1002016002, "EXCEPTION.TENANT_PACKAGE_DISABLE");

  // ========== 错误码模块 1002017000 ==========
  BusinessException ERROR_CODE_NOT_EXISTS =
      newBusinessException(1002017000, "EXCEPTION.ERROR_CODE_NOT_EXISTS");
  BusinessException ERROR_CODE_DUPLICATE =
      newBusinessException(1002017001, "EXCEPTION.ERROR_CODE_DUPLICATE");

  // ========== 社交用户 1002018000 ==========
  BusinessException SOCIAL_USER_AUTH_FAILURE =
      newBusinessException(1002018000, "EXCEPTION.SOCIAL_USER_AUTH_FAILURE");
  BusinessException SOCIAL_USER_UNBIND_NOT_SELF =
      newBusinessException(1002018001, "EXCEPTION.SOCIAL_USER_UNBIND_NOT_SELF");
  BusinessException SOCIAL_USER_NOT_FOUND =
      newBusinessException(1002018002, "EXCEPTION.SOCIAL_USER_NOT_FOUND");

  // ========== 系统敏感词 1002019000 =========
  BusinessException SENSITIVE_WORD_NOT_EXISTS =
      newBusinessException(1002019000, "EXCEPTION.SENSITIVE_WORD_NOT_EXISTS");
  BusinessException SENSITIVE_WORD_EXISTS =
      newBusinessException(1002019001, "EXCEPTION.SENSITIVE_WORD_EXISTS");
  BusinessException SENSITIVE_WORD_DENIED =
      newBusinessException(1002019002, "EXCEPTION.SENSITIVE_WORD_DENIED");

  // ========== OAuth2 客户端 1002020000 =========
  BusinessException OAUTH2_CLIENT_NOT_EXISTS =
      newBusinessException(1002020000, "EXCEPTION.OAUTH2_CLIENT_NOT_EXISTS");
  BusinessException OAUTH2_CLIENT_EXISTS =
      newBusinessException(1002020001, "EXCEPTION.OAUTH2_CLIENT_EXISTS");
  BusinessException OAUTH2_CLIENT_DISABLE =
      newBusinessException(1002020002, "EXCEPTION.OAUTH2_CLIENT_DISABLE");
  BusinessException OAUTH2_CLIENT_AUTHORIZED_GRANT_TYPE_NOT_EXISTS =
      newBusinessException(1002020003, "EXCEPTION.OAUTH2_CLIENT_AUTHORIZED_GRANT_TYPE_NOT_EXISTS");
  BusinessException OAUTH2_CLIENT_SCOPE_OVER =
      newBusinessException(1002020004, "EXCEPTION.OAUTH2_CLIENT_SCOPE_OVER");
  BusinessException OAUTH2_CLIENT_REDIRECT_URI_NOT_MATCH =
      newBusinessException(1002020005, "EXCEPTION.OAUTH2_CLIENT_REDIRECT_URI_NOT_MATCH");
  BusinessException OAUTH2_CLIENT_CLIENT_SECRET_ERROR =
      newBusinessException(1002020006, "EXCEPTION.OAUTH2_CLIENT_CLIENT_SECRET_ERROR");

  // ========== OAuth2 授权 1002021000 =========
  BusinessException OAUTH2_GRANT_CLIENT_ID_MISMATCH =
      newBusinessException(1002021000, "EXCEPTION.OAUTH2_GRANT_CLIENT_ID_MISMATCH");
  BusinessException OAUTH2_GRANT_REDIRECT_URI_MISMATCH =
      newBusinessException(1002021001, "EXCEPTION.OAUTH2_GRANT_REDIRECT_URI_MISMATCH");
  BusinessException OAUTH2_GRANT_STATE_MISMATCH =
      newBusinessException(1002021002, "EXCEPTION.OAUTH2_GRANT_STATE_MISMATCH");
  BusinessException OAUTH2_GRANT_CODE_NOT_EXISTS =
      newBusinessException(1002021003, "EXCEPTION.OAUTH2_GRANT_CODE_NOT_EXISTS");

  // ========== OAuth2 授权 1002022000 =========
  BusinessException OAUTH2_CODE_NOT_EXISTS =
      newBusinessException(1002022000, "EXCEPTION.OAUTH2_CODE_NOT_EXISTS");
  BusinessException OAUTH2_CODE_EXPIRE =
      newBusinessException(1002022001, "EXCEPTION.OAUTH2_CODE_EXPIRE");

  // ========== 邮箱账号 1002023000 ==========
  BusinessException MAIL_ACCOUNT_NOT_EXISTS =
      newBusinessException(1002023000, "EXCEPTION.MAIL_ACCOUNT_NOT_EXISTS");
  BusinessException MAIL_ACCOUNT_RELATE_TEMPLATE_EXISTS =
      newBusinessException(1002023001, "EXCEPTION.MAIL_ACCOUNT_RELATE_TEMPLATE_EXISTS");

  // ========== 邮件模版 1002024000 ==========
  BusinessException MAIL_TEMPLATE_NOT_EXISTS =
      newBusinessException(1002024000, "EXCEPTION.MAIL_TEMPLATE_NOT_EXISTS");
  BusinessException MAIL_TEMPLATE_CODE_EXISTS =
      newBusinessException(1002024001, "EXCEPTION.MAIL_TEMPLATE_CODE_EXISTS");

  // ========== 邮件发送 1002025000 ==========
  BusinessException MAIL_SEND_TEMPLATE_PARAM_MISS =
      newBusinessException(1002025000, "EXCEPTION.MAIL_SEND_TEMPLATE_PARAM_MISS");
  BusinessException MAIL_SEND_MAIL_NOT_EXISTS =
      newBusinessException(1002025000, "EXCEPTION.MAIL_SEND_MAIL_NOT_EXISTS");

  // ========== 站内信模版 1002026000 ==========
  BusinessException NOTIFY_TEMPLATE_NOT_EXISTS =
      newBusinessException(1002026000, "EXCEPTION.NOTIFY_TEMPLATE_NOT_EXISTS");
  BusinessException NOTIFY_TEMPLATE_CODE_DUPLICATE =
      newBusinessException(1002026001, "EXCEPTION.NOTIFY_TEMPLATE_CODE_DUPLICATE");

  // ========== 站内信模版 1002027000 ==========

  // ========== 站内信发送 1002028000 ==========
  BusinessException NOTIFY_SEND_TEMPLATE_PARAM_MISS =
      newBusinessException(1002025000, "EXCEPTION.NOTIFY_SEND_TEMPLATE_PARAM_MISS");

  // ========== 业务异常 1002029000 ==========
  BusinessException COMMON_UNKNOWN = newBusinessException(1002029001, "EXCEPTION.COMMON_UNKNOWN");

  // ========== 数据库相关 1002030000 ==========
  BusinessException DB_DATA_NOT_FOUND =
      newBusinessException(1002030001, "EXCEPTION.DB_DATA_NOT_FOUND");
  BusinessException DB_INSERT_ERROR = newBusinessException(1002030002, "EXCEPTION.DB_INSERT_ERROR");
  BusinessException DB_SELECT_ERROR = newBusinessException(1002030003, "EXCEPTION.DB_SELECT_ERROR");
  BusinessException DB_UPDATE_ERROR = newBusinessException(1002030004, "EXCEPTION.DB_UPDATE_ERROR");
  BusinessException DB_DELETE_ERROR = newBusinessException(1002030005, "EXCEPTION.DB_DELETE_ERROR");

  // ========== 内部服务调用相关 1002031000 ==========
  BusinessException INTERNAL_UNKNOWN =
      newBusinessException(1002031001, "EXCEPTION.INTERNAL_UNKNOWN");
  BusinessException INTERNAL_COMMON_SERVER_ERROR =
      newBusinessException(1002031002, "EXCEPTION.INTERNAL_COMMON_SERVER_ERROR");
  BusinessException INTERNAL_CHATBOT_REPLY =
      newBusinessException(1002031003, "EXCEPTION.INTERNAL_CHATBOT_REPLY");

  BusinessException INTERNAL_CALL_SECRET_ERROR =
      newBusinessException(1002031004, "EXCEPTION.INTERNAL_CALL_SECRET_ERROR");

  BusinessException CHATBOT_CONVERSATION_NOT_EXIST =
      newBusinessException(1002031005, "EXCEPTION.CHATBOT_CONVERSATION_NOT_EXIST");

  BusinessException INTERNAL_SD_PROMPT_GENERATE =
      newBusinessException(1002031006, "EXCEPTION.INTERNAL_SD_PROMPT_GENERATE");

  BusinessException SD_PROMPT_TOO_LONG =
      newBusinessException(1002031007, "EXCEPTION.SD_PROMPT_TOO_LONG");

  // ========== json序列化相关 1002032000 ==========
  BusinessException JSON_PARSE_ERROR =
      newBusinessException(1002032001, "EXCEPTION.JSON_PARSE_ERROR");

  // ========== 支付相关 1002033000 ==========
  BusinessException WX_GET_SIGNATURE_FAILED =
      newBusinessException(1002033001, "EXCEPTION.WX_GET_SIGNATURE_FAILED");
  BusinessException WX_SIGNATURE_FAILED =
      newBusinessException(1002033002, "EXCEPTION.WX_SIGNATURE_FAILED");
  BusinessException WX_NATIVE_PAY_FAILED =
      newBusinessException(1002033003, "EXCEPTION.WX_NATIVE_PAY_FAILED");
  BusinessException WX_GET_JSAPI_TICKET_FAILED =
      newBusinessException(1002033004, "EXCEPTION.WX_GET_JSAPI_TICKET_FAILED");
  BusinessException WX_GET_ACCESS_TOKEN_FAILED =
      newBusinessException(1002033005, "EXCEPTION.WX_GET_ACCESS_TOKEN_FAILED");
  BusinessException WX_GET_JSAPI_TICKET_RETRY_LIMIT =
      newBusinessException(1002033006, "EXCEPTION.WX_GET_JSAPI_TICKET_RETRY_LIMIT");
  BusinessException WX_JSAPI_CALL_FAILED =
      newBusinessException(1002033007, "EXCEPTION.WX_JSAPI_CALL_FAILED");
  BusinessException WX_REFUND_FAILED =
      newBusinessException(1002033008, "EXCEPTION.WX_REFUND_FAILED");
  BusinessException WX_CALLBACK_FAILED =
      newBusinessException(1002033009, "EXCEPTION.WX_CALLBACK_FAILED");
  BusinessException WX_PAY_ORDER_QUERY_FAILED =
      newBusinessException(1002033010, "EXCEPTION.WX_PAY_ORDER_QUERY_FAILED");
  BusinessException WX_PAY_ORDER_CLOSE_FAILED =
      newBusinessException(1002033011, "EXCEPTION.WX_PAY_ORDER_CLOSE_FAILED");
  BusinessException PAY_ORDER_CREATE_FAILED =
      newBusinessException(1002033012, "EXCEPTION.PAY_ORDER_CREATE_FAILED");

  BusinessException PAY_ORDER_QUERY_FAILED =
      newBusinessException(1002033013, "EXCEPTION.PAY_ORDER_QUERY_FAILED");

  BusinessException PAY_ORDER_REFUND_FAILED =
      newBusinessException(1002033014, "EXCEPTION.PAY_ORDER_REFUND_FAILED");

  BusinessException PAY_ORDER_REFUND_QUERY_FAILED =
      newBusinessException(1002033015, "EXCEPTION.PAY_ORDER_REFUND_QUERY_FAILED");

  BusinessException PAY_ORDER_CAPTURE_FAILED =
      newBusinessException(1002033016, "EXCEPTION.PAY_ORDER_CAPTURE_FAILED");

  BusinessException PAY_ORDER_NOT_EXIST =
      newBusinessException(1002033017, "EXCEPTION.PAY_ORDER_NOT_EXIST");

  BusinessException ORDER_NOT_EXIST = newBusinessException(1002033018, "EXCEPTION.ORDER_NOT_EXIST");

  BusinessException PAY_ORDER_CALLBACK_FAILED =
      newBusinessException(1002033019, "EXCEPTION.PAY_ORDER_CALLBACK_FAILED");

  // ========== Task相关 1002034000 ==========
  BusinessException REPEAT_TASK = newBusinessException(1002034001, "EXCEPTION.REPEAT_TASK");
  BusinessException TASK_TYPE_NOT_SUPPORTED =
      newBusinessException(1002034002, "EXCEPTION.TASK_TYPE_NOT_SUPPORTED");
  BusinessException TASK_AUTHORIZATION_FAILED =
      newBusinessException(1002034003, "EXCEPTION.TASK_AUTHORIZATION_FAILED");
  BusinessException TASK_TERMINATE_FAILED =
      newBusinessException(1002034004, "EXCEPTION.TASK_TERMINATE_FAILED");
  BusinessException PREVIEWS_TASK_NOT_FOUND =
      newBusinessException(1002034005, "EXCEPTION.PREVIEWS_TASK_NOT_FOUND");
  BusinessException TASK_NOT_FOUND = newBusinessException(1002034006, "EXCEPTION.TASK_NOT_FOUND");
  BusinessException TASK_NOT_FINISHED =
      newBusinessException(1002034007, "EXCEPTION.TASK_NOT_FINISHED");
  BusinessException TASK_NOT_BELONG_TO_CURRENT_NODE =
      newBusinessException(1002034008, "EXCEPTION.TASK_NOT_BELONG_TO_CURRENT_NODE");
  BusinessException TASK_STATUS_INCORRECT =
      newBusinessException(1002034009, "EXCEPTION.TASK_STATUS_INCORRECT");

  // ========== COS相关 1002035000 ==========
  BusinessException COS_GENERATE_SECRET_ERROR =
      newBusinessException(1002035001, "EXCEPTION.COS_GENERATE_SECRET_ERROR");

  // ========== NAS相关 1002036000 ==========
  BusinessException NAS_SD_PATH_NOT_EXIST =
      newBusinessException(1002036001, "EXCEPTION.NAS_SD_PATH_NOT_EXIST");
  BusinessException NAS_SD_PATH_NOT_WRITABLE =
      newBusinessException(1002036002, "EXCEPTION.NAS_SD_PATH_NOT_WRITABLE");
  BusinessException NAS_LORA_PATH_NOT_EXIST =
      newBusinessException(1002036003, "EXCEPTION.NAS_LORA_PATH_NOT_EXIST");
  BusinessException NAS_LORA_PATH_NOT_WRITABLE =
      newBusinessException(1002036004, "EXCEPTION.NAS_LORA_PATH_NOT_WRITABLE");
  BusinessException NAS_MODEL_DOWNLOAD_FAILED =
      newBusinessException(1002036005, "EXCEPTION.NAS_MODEL_DOWNLOAD_FAILED");

  // ========== 订单相关 1002037000 ==========
  BusinessException PAYMENT_ORDER_STATUS_ERROR =
      newBusinessException(1002037001, "EXCEPTION.PAYMENT_ORDER_STATUS_ERROR");
  BusinessException REPEAT_ORDER = newBusinessException(1002037002, "EXCEPTION.REPEAT_ORDER");
  BusinessException ORDER_ALREADY_COMPLETED_OR_CLOSED =
      newBusinessException(1002037003, "EXCEPTION.ORDER_ALREADY_COMPLETED_OR_CLOSED");
  BusinessException ORDER_INVALID = newBusinessException(1002037004, "EXCEPTION.ORDER_INVALID");
  BusinessException ORDER_CANCEL_FAILED =
      newBusinessException(1002037005, "EXCEPTION.ORDER_CANCEL_FAILED");
  BusinessException ORDER_CLOSE_FAILED =
      newBusinessException(1002037006, "EXCEPTION.ORDER_CLOSE_FAILED");
  BusinessException NOT_SUPPORT_PAY_CHANNEL =
      newBusinessException(1002037007, "EXCEPTION.NOT_SUPPORT_PAY_CHANNEL");
  BusinessException REPEAT_PAY_ORDER =
      newBusinessException(1002037008, "EXCEPTION.REPEAT_PAY_ORDER");
  BusinessException SKU_NOT_EXIST = newBusinessException(1002037009, "EXCEPTION.SKU_NOT_EXIST");

  // ========== 模型相关 1002038000 ==========
  BusinessException LORA_BASE_MODEL_ERROR =
      newBusinessException(1002038001, "EXCEPTION.LORA_BASE_MODEL_ERROR");
  BusinessException CHECK_POINT_BASE_MODEL_ERROR =
      newBusinessException(1002038002, "EXCEPTION.CHECK_POINT_BASE_MODEL_ERROR");
  BusinessException MODEL_AUTHORIZE_FAILED =
      newBusinessException(1002038003, "EXCEPTION.MODEL_AUTHORIZE_FAILED");
  BusinessException MODEL_FILE_NOT_EXIST =
      newBusinessException(1002038004, "EXCEPTION.MODEL_FILE_NOT_EXIST");
  BusinessException MODEL_TYPE_ERROR =
      newBusinessException(1002038005, "EXCEPTION.MODEL_TYPE_ERROR");
  BusinessException MODEL_DOWNLOAD_ERROR =
      newBusinessException(1002038006, "EXCEPTION.MODEL_DOWNLOAD_ERROR");
  BusinessException BASE_MODEL_NOT_AVAILABLE =
      newBusinessException(1002038007, "EXCEPTION.BASE_MODEL_NOT_AVAILABLE");
  BusinessException BASE_MODEL_TYPE_ERROR =
      newBusinessException(1002038008, "EXCEPTION.BASE_MODEL_TYPE_ERROR");
  BusinessException LORA_MODEL_NOT_AVAILABLE =
      newBusinessException(1002038009, "EXCEPTION.LORA_MODEL_NOT_AVAILABLE");
  BusinessException LORA_MODEL_TYPE_ERROR =
      newBusinessException(1002038010, "EXCEPTION.LORA_MODEL_TYPE_ERROR");
  BusinessException MODEL_ALREADY_TESTED =
      newBusinessException(1002038011, "EXCEPTION.MODEL_ALREADY_TESTED");
  BusinessException MODEL_ALREADY_DELETED =
      newBusinessException(1002038012, "EXCEPTION.MODEL_ALREADY_DELETED");
  BusinessException MODEL_VALIDATING =
      newBusinessException(1002038013, "EXCEPTION.MODEL_VALIDATING");
  BusinessException MODEL_NOT_PUBLIC =
      newBusinessException(1002038014, "EXCEPTION.MODEL_NOT_PUBLIC");
  BusinessException MODEL_NOT_PUBLISH =
      newBusinessException(1002038015, "EXCEPTION.MODEL_NOT_PUBLISH");
  BusinessException MODEL_TRAIN_PERMISSION_DENIED =
      newBusinessException(1002038016, "EXCEPTION.MODEL_TRAIN_PERMISSION_DENIED");
  BusinessException MODEL_ANY2IMG_PERMISSION_DENIED =
      newBusinessException(1002038017, "EXCEPTION.MODEL_ANY2IMG_PERMISSION_DENIED");
  BusinessException MODEL_DOWNLOAD_PERMISSION_DENIED =
      newBusinessException(1002038018, "EXCEPTION.MODEL_DOWNLOAD_PERMISSION_DENIED");
  BusinessException EMPTY_MODEL_FILE =
      newBusinessException(1002038019, "EXCEPTION.EMPTY_MODEL_FILE");
  BusinessException EMPTY_COVER_FILE =
      newBusinessException(1002038020, "EXCEPTION.EMPTY_COVER_FILE");
  BusinessException EMPTY_CAROUSEL_FILE =
      newBusinessException(1002038021, "EXCEPTION.EMPTY_CAROUSEL_FILE");
  BusinessException MODEL_REVIEW_REPEATED =
      newBusinessException(1002038022, "EXCEPTION.MODEL_REVIEW_REPEATED");
  BusinessException CHECK_POINT_FILE_ERROR =
      newBusinessException(1002038023, "EXCEPTION.CHECK_POINT_FILE_ERROR");
  BusinessException LORA_FILE_ERROR = newBusinessException(1002038024, "EXCEPTION.LORA_FILE_ERROR");
  // ========== 兑换码相关 1002039000 ==========
  BusinessException EXCHANGE_CODE_NOT_EXIST =
      newBusinessException(1002039001, "EXCEPTION.EXCHANGE_CODE_NOT_EXIST");
  BusinessException EXCHANGE_CODE_USED =
      newBusinessException(1002039002, "EXCEPTION.EXCHANGE_CODE_USED");
  BusinessException EXCHANGE_CODE_EXPIRED =
      newBusinessException(1002039003, "EXCEPTION.EXCHANGE_CODE_EXPIRED");

  BusinessException EXCHANGE_CODE_STATUS_ERROR =
      newBusinessException(1002039004, "EXCEPTION.EXCHANGE_CODE_STATUS_ERROR");

  BusinessException EXCHANGE_CODE_TYPE_ERROR =
      newBusinessException(1002039005, "EXCEPTION.EXCHANGE_CODE_TYPE_ERROR");

  // ========== 订阅套餐相关 1002040000 ==========
  BusinessException USER_PLAN_NOT_EXIST =
      newBusinessException(1002040001, "EXCEPTION.USER_PLAN_NOT_EXIST");
  BusinessException USER_PLAN_SUBSTITUTE_ERROR =
      newBusinessException(1002040002, "EXCEPTION.USER_PLAN_SUBSTITUTE_ERROR");
  BusinessException USER_PLAN_PRODUCT_LIMITED =
      newBusinessException(1002040003, "EXCEPTION.USER_PLAN_PRODUCT_LIMITED");
  BusinessException USER_PLAN_NOT_SUPPORT =
      newBusinessException(1002040004, "EXCEPTION.USER_PLAN_NOT_SUPPORT");
  BusinessException USER_PLAN_OLD_NOT_EXIST =
      newBusinessException(1002040005, "EXCEPTION.USER_PLAN_OLD_NOT_EXIST");
  BusinessException USER_PLAN_NEW_NOT_EXIST =
      newBusinessException(1002040006, "EXCEPTION.USER_PLAN_NEW_NOT_EXIST");
  BusinessException USER_PLAN_UPGRADE_OR_RENEWAL_FAILED =
      newBusinessException(1002040007, "EXCEPTION.USER_PLAN_UPGRADE_OR_RENEWAL_FAILED");
  BusinessException USER_PLAN_UPDATE__FAILED =
      newBusinessException(1002040008, "EXCEPTION.USER_PLAN_UPDATE__FAILED");
  BusinessException USER_PLAN_ALREADY_ACTIVATED =
      newBusinessException(1002040009, "EXCEPTION.USER_PLAN_ALREADY_ACTIVATED");
  BusinessException COMMON_FEATURE_EXPIRED =
      newBusinessException(1002040010, "EXCEPTION.COMMON_FEATURE_EXPIRED");
  BusinessException USER_PLAN_CANCELLATION_NOT_ALLOWED =
      newBusinessException(1002040011, "EXCEPTION.USER_PLAN_CANCELLATION_NOT_ALLOWED");

  BusinessException USER_PLAN_CANCELLATION_TICKET_EXISTS =
      newBusinessException(1002040012, "EXCEPTION.USER_PLAN_CANCELLATION_TICKET_EXISTS");

  BusinessException USER_PLAN_CANCELLATION_TICKET_NOT_EXISTS =
      newBusinessException(1002040013, "EXCEPTION.USER_PLAN_CANCELLATION_TICKET_NOT_EXISTS");

  BusinessException SUBSCRIPTION_CANCEL_FAILED =
      newBusinessException(1002040013, "EXCEPTION.SUBSCRIPTION_CANCEL_FAILED");

  BusinessException SUBSCRIPTION_CANCEL_NOT_SUPPORTED =
      newBusinessException(1002040014, "EXCEPTION.SUBSCRIPTION_CANCEL_NOT_SUPPORTED");

  BusinessException USER_PLAN_CANCELLED =
      newBusinessException(1002040015, "EXCEPTION.USER_PLAN_CANCELLED");

  BusinessException USER_PLAN_CHANGE_FAILED =
      newBusinessException(1002040016, "EXCEPTION.USER_PLAN_CHANGE_FAILED");

  BusinessException USER_PLAN_PROCESSING =
      newBusinessException(1002040017, "EXCEPTION.USER_PLAN_PROCESSING");

  BusinessException USER_PLAN_RESUME_FAILED =
      newBusinessException(1002040018, "EXCEPTION.USER_PLAN_RESUME_FAILED");

  // ========== 用户credit账户相关 1002041000 ==========
  BusinessException CREDIT_INSUFFICIENT_BALANCE =
      newBusinessException(1002041001, "EXCEPTION.CREDIT_INSUFFICIENT_BALANCE");
  BusinessException CREDIT_UPDATE_FAILED =
      newBusinessException(1002041002, "EXCEPTION.CREDIT_UPDATE_FAILED");
  BusinessException COST_ORDER_UPDATE_FAILED =
      newBusinessException(1002041003, "EXCEPTION.COST_ORDER_UPDATE_FAILED");

  // ========== 内容生成相关 1002042000 ==========
  BusinessException ANY2IMG_MAX_PARALLEL_TASK =
      newBusinessException(1002042001, "EXCEPTION.ANY2IMG_MAX_PARALLEL_TASK");

  BusinessException ANY2IMG_EMPTY_IMG_FILE =
      newBusinessException(1002042002, "EXCEPTION.ANY2IMG_EMPTY_IMG_FILE");

  // ========== 文件上传相关 1002043000 ==========
  BusinessException UPLOAD_EMPTY_FILE =
      newBusinessException(1002043001, "EXCEPTION.UPLOAD_EMPTY_FILE");
  BusinessException UPLOAD_EMPTY_FILE_NAME =
      newBusinessException(1002043002, "EXCEPTION.UPLOAD_EMPTY_FILE_NAME");
  BusinessException UPLOAD_INVALID_TARGET_FILE_PATH =
      newBusinessException(1002043003, "EXCEPTION.UPLOAD_INVALID_TARGET_FILE_PATH");
  BusinessException UPLOAD_FILE_ERROR =
      newBusinessException(1002043004, "EXCEPTION.UPLOAD_FILE_ERROR");
  BusinessException UPLOAD_EMPTY_TARGET_FILE_CONTENT =
      newBusinessException(1002043005, "EXCEPTION.UPLOAD_EMPTY_TARGET_FILE_CONTENT");
  BusinessException UPLOAD_FILE_EMPTY_KEY =
      newBusinessException(1002043006, "EXCEPTION.UPLOAD_FILE_EMPTY_KEY");
  BusinessException UPLOAD_FILE_VALIDATE_FAIL =
      newBusinessException(1002043007, "EXCEPTION.UPLOAD_FILE_VALIDATE_FAIL");
  BusinessException UPLOAD_IMAGE_EMPTY =
      newBusinessException(1002043008, "EXCEPTION.UPLOAD_IMAGE_EMPTY");
  BusinessException UPLOAD_IMAGE_TOO_LARGE =
      newBusinessException(1002043009, "EXCEPTION.UPLOAD_IMAGE_TOO_LARGE");

  // ========== 邀请码相关 1002044000 ==========
  BusinessException INVITE_CODE_INVALID =
      newBusinessException(1002043001, "EXCEPTION.INVITE_CODE_INVALID");
  BusinessException INVITE_CODE_GENERATE_ERROR =
      newBusinessException(1002043002, "EXCEPTION.INVITE_CODE_GENERATE_ERROR");
  BusinessException INVITE_CODE_USED =
      newBusinessException(1002043003, "EXCEPTION.INVITE_CODE_USED");

  // ========== 灵感广场相关 1002045000 ==========
  BusinessException ART_WORK_CREATE_FAILED =
      newBusinessException(1002045001, "EXCEPTION.ART_WORK_CREATE_FAILED");
  BusinessException ART_WORK_TAG_EMPTY =
      newBusinessException(1002045002, "EXCEPTION.ART_WORK_TAG_EMPTY");

  // ========== 小程序相关 1002046000 ==========
  BusinessException MINIAPP_QRCODE_ERROR =
      newBusinessException(1002046001, "EXCEPTION.MINIAPP_QRCODE_ERROR");

  // ========== 评论相关 1002047000 ==========
  BusinessException COMMENT_ADD_FAILED =
      newBusinessException(1002047001, "EXCEPTION.COMMENT_ADD_FAILED");
  BusinessException COMMENT_DELETE_FAILED =
      newBusinessException(1002047002, "EXCEPTION.COMMENT_DELETE_FAILED");
  BusinessException COMMENT_GET_FAILED =
      newBusinessException(1002047003, "EXCEPTION.COMMENT_GET_FAILED");
  BusinessException COMMENT_AUTH_FAILED =
      newBusinessException(1002047004, "EXCEPTION.COMMENT_AUTH_FAILED");
  BusinessException COMMENT_ALREADY_DELETED =
      newBusinessException(1002047005, "EXCEPTION.COMMENT_ALREADY_DELETED");
  BusinessException COMMENT_CONTENT_ERROR =
      newBusinessException(1002047006, "EXCEPTION.COMMENT_CONTENT_ERROR");
  BusinessException COMMENT_TOO_FREQUENCY =
      newBusinessException(1002047007, "EXCEPTION.COMMENT_TOO_FREQUENCY");
}
