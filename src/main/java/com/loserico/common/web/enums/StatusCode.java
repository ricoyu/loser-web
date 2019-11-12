package com.loserico.common.web.enums;

/**
 * 异常代码，code是相关代码、msgTemplate是国际化消息模版、defaultMsg是模版对应的国际化消息不存在情况下的默认消息 
 * <p>
 * Copyright: Copyright (c) 2019-10-14 16:23
 * <p>
 * Company: Sexy Uncle Inc.
 * <p>
 
 * @author Rico Yu  ricoyu520@gmail.com
 * @version 1.0
 */
public enum StatusCode {

	/* 40xx 数据验证相关code */
	BAD_REQUEST(4000, "code.badrequest", "Bad Request"),
	DUPLICATE_SUBMISSION(4022, "duplicate.submit", "请勿重复提交"),

	/* 41xx 数据交互相关code */
	ENTITY_NOT_FOUND(4101, "code.entity.not.found", "请求的数据不存在"),
	UNIQUE_VIOLATION(4103, "code.unique.violation", "违反唯一性约束"),
	USER_NOT_EXISTS(4208, "user.not.exists", "该用户不存在"),
	ACCOUNT_NOT_EXIST(4211, "account.not.exist", "您的账号不存在"),
	NO_MOBILE(4214, "not.empty.mobile", "请提供mobile"),
	FIRST_LOGIN(4215, "user.first.login", "首次登录请修改密码"),
	ACCOUNT_EXISTS(4216, "account.exists", "账号已存在"),
	API_FILEUPLOAD_FAIL(4301, "file.upload.failed", "上传图片失败"),

	/* 50xx 未知异常 */
	INTERNAL_SERVER_ERROR(5000, "code.internal.server.error", "Internal Server Error");

	private int code;
	private String defaultMsg;
	private String msgTemplate;

	private StatusCode(int code, String msgTemplate, String defaultMsg) {
		this.code = code;
		this.msgTemplate = msgTemplate;
		this.defaultMsg = defaultMsg;
	}
	
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}


	public String getMsgTemplate() {
		return msgTemplate;
	}

	public void setMsgTemplate(String msgTemplate) {
		this.msgTemplate = msgTemplate;
	}


	public String getDefaultMsg() {
		return defaultMsg;
	}

	public void setDefaultMsg(String defaultMsg) {
		this.defaultMsg = defaultMsg;
	}

}
