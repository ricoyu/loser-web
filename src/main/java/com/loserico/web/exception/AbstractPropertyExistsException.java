package com.loserico.web.exception;

/**
 *  
 * <p>
 * Copyright: Copyright (c) 2019-10-14 17:03
 * <p>
 * Company: Sexy Uncle Inc.
 * <p>
 
 * @author Rico Yu  ricoyu520@gmail.com
 * @version 1.0
 */
public abstract class AbstractPropertyExistsException extends RuntimeException {

	private static final long serialVersionUID = 4831389749197721795L;

	protected String field;

	protected String templateParam;

	protected String messageTemplate;

	public AbstractPropertyExistsException() {
	}

	public AbstractPropertyExistsException(String message) {
		super(message);
	}

	public AbstractPropertyExistsException(Throwable cause) {
		super(cause);
	}

	public AbstractPropertyExistsException(String message, Throwable cause) {
		super(message, cause);
	}

	public AbstractPropertyExistsException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getTemplateParam() {
		return templateParam;
	}

	public void setTemplateParam(String templateParam) {
		this.templateParam = templateParam;
	}

	public String getMessageTemplate() {
		return messageTemplate;
	}

	public void setMessageTemplate(String messageTemplate) {
		this.messageTemplate = messageTemplate;
	}

}
