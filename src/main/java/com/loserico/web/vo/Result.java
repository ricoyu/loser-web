package com.loserico.web.vo;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

@Data
public class Result {
	
	private static final Logger log = LoggerFactory.getLogger(Result.class);

	/**
	 * 请求接口状态码
	 */
	private int status = 200;

	/**
	 * 数据请求状态码 
	 * 0	代表成功
	 * 其他代表不同的错误码
	 */
	private int code = 0;

	/**
	 * message表示在API调用失败的情况下详细的错误信息，这个信息可以由客户端直接呈现给用户，否则为OK；
	 */
	private Object message = "OK";
	
	private Object debugMessage;

	private Object data;
	
	public <K, V> Result put(K key, V value) {
		if (data instanceof Map) {
			Map<K, V> map = (Map<K, V>)data;
			map.put(key, value);
		} else {
			log.warn("data is not a Map, cannot call put(k, v)");
		}
		return this;
	}

}
