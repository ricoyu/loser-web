package com.loserico.common.web.vo;

import com.loserico.common.web.beans.Page;
import com.loserico.common.web.enums.StatusCode;
import com.loserico.common.web.utils.MessageHelper;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static java.util.Collections.EMPTY_LIST;
import static java.util.Optional.ofNullable;

/**
 * <blockquote><pre>
 * status    请求接口状态码
 * code      数据请求状态码
 * message   数据请求返回消息
 * data      单个数据类
 * results   数组类型
 *
 * 情况1  status==200&&code==0 的时候请求接口数据成功, 解析数据
 * 情况2  status!=200          直接捕获异常 exception
 * 情况3  status＝200 code!=0  弹框message给用户
 * </pre></blockquote>
 * <p>
 * Copyright: Copyright (c) 2019-10-14 15:54
 * <p>
 * Company: Sexy Uncle Inc.
 * <p>
 *
 * @author Rico Yu  ricoyu520@gmail.com
 * @version 1.0
 */
public class Results {

	private Results() {
	}

	public static class Builder {
		private final Result result = new Result();

		//请求接口状态码
		private int status = 200;

		//数据请求状态码 
		private int code = 0;

		//数据请求返回消息
		private Object message = "OK";

		private Object debugMessage;

		private Page page;

		//单个数据对象
		private Object data;

		private Collection<?> results;

		public Builder success() {
			this.code = 0;
			return this;
		}

		/**
		 * 标记此次API调用结果是fail，code设为1
		 *
		 * @return
		 */
		public Builder fail() {
			this.code = 1;
			return this;
		}

		/**
		 * 手工标记code
		 *
		 * @param statusCode
		 * @return
		 */
		public Builder code(StatusCode statusCode) {
			this.code = statusCode.getCode();
			this.message = MessageHelper.getMessage(statusCode.getMsgTemplate(), statusCode.getDefaultMsg());
			return this;
		}

		public Builder code(int code) {
			this.code = code;
			return this;
		}

		public Builder code(int code, String message) {
			this.code = code;
			this.message = message;
			return this;
		}

		/**
		 * 设置请求状态代码
		 *
		 * @param status
		 * @return
		 */
		public Builder status(int status) {
			this.status = status;
			return this;
		}

		/**
		 * 设置返回消息描述
		 *
		 * @param message
		 * @return
		 */
		public Builder message(Object message) {
			this.message = message;
			return this;
		}

		public Builder debugMessage(Object debugMessage) {
			this.debugMessage = debugMessage;
			return this;
		}

		/**
		 * 设置返回数据，为单个对象
		 *
		 * @param data
		 * @return
		 */
		public Builder result(Object data) {
			this.data = data;
			return this;
		}

		/**
		 * 设置返回数据，数据是数组类型
		 *
		 * @param results
		 * @return
		 */
		public Builder results(Collection<?> results) {
			this.results = results;
			return this;
		}

		/**
		 * 设置分页信息
		 *
		 * @param page
		 * @return
		 */
		public Builder page(Page page) {
			if (page != null && !page.isPagingIgnore()) {
				this.page = page;
			}
			return this;
		}

		public Result build() {
			result.setCode(code);
			result.setMessage(message);
			result.setDebugMessage(debugMessage);
			result.setStatus(status);

			/*
			 * 传了page对象就认为是分页数据，
			 */
			if (page != null) {
				Map<String, Object> resultMap = new HashMap<>();
				resultMap.put("totalCount", page.getTotalCount());
				resultMap.put("totalPages", page.getTotalPages());
				resultMap.put("currentPage", page.getCurrentPage());
				resultMap.put("pageSize", page.getPageSize());
				resultMap.put("hasNextPage", page.isHasNextPage());
				resultMap.put("hasPreviousPage", page.isHasPreviousPage());
				resultMap.put("items", ofNullable(results).orElse(EMPTY_LIST));
				result.setData(resultMap);
			} else { //返回结果不带分页
				if (results != null) {
					Map<String, Object> resultMap = new HashMap<>();
					resultMap.put("items", results);
					result.setData(resultMap);
				} else {
					result.setData(data);
				}
			}
			return result;
		}
	}

	/**
	 * 入口方法之一
	 */
	public static Builder success() {
		Builder builder = new Builder();
		return builder.success();
	}

	/**
	 * 入口方法之一
	 */
	public static Builder fail() {
		Builder builder = new Builder();
		return builder.fail();
	}

	@Deprecated
	public static Builder builder() {
		return new Builder();
	}

}