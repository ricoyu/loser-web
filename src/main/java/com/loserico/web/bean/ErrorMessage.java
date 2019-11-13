package com.loserico.web.bean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 错误消息封装 
 * <p>
 * Copyright: Copyright (c) 2019-10-14 17:01
 * <p>
 * Company: Sexy Uncle Inc.
 * <p>
 
 * @author Rico Yu  ricoyu520@gmail.com
 * @version 1.0
 */
public class ErrorMessage {

	private List<String[]> errors;

	public ErrorMessage() {
		this.errors = new ArrayList<String[]>();
	}

	public ErrorMessage(List<String[]> errors) {
		this.errors = errors;
	}

	public ErrorMessage(String[] error) {
		this(Collections.singletonList(error));
	}

	public ErrorMessage(String[]... errors) {
		this(Arrays.asList(errors));
	}

	public List<String[]> getErrors() {
		return errors;
	}

	public void setErrors(List<String[]> errors) {
		this.errors = errors;
	}
}