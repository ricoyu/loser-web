package com.loserico.web.utils;

import com.loserico.web.bean.ErrorMessage;
import com.loserico.web.exception.AbstractPropertyExistsException;
import com.loserico.web.i18n.LocaleContextHolder;
import org.springframework.context.MessageSource;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static com.loserico.common.lang.utils.Arrays.asArray;
import static java.util.stream.Collectors.toList;

/**
 *  
 * <p>
 * Copyright: Copyright (c) 2019-10-14 17:27
 * <p>
 * Company: Sexy Uncle Inc.
 * <p>
 
 * @author Rico Yu  ricoyu520@gmail.com
 * @version 1.0
 */
public class ValidationUtils {

	/**
	 * 将Spring的BindingResult转成ErrorMessage, 用于输出成JSON格式
	 * @param bindingResult
	 * @return ErrorMessage
	 */
	public static ErrorMessage getErrorMessage(BindingResult bindingResult) {
		List<FieldError> fieldErrors = bindingResult.getFieldErrors();
		List<ObjectError> globalErrors = bindingResult.getGlobalErrors();
		List<String[]> errors = new ArrayList<String[]>(fieldErrors.size() + globalErrors.size());
		String[] error;
		for (FieldError fieldError : fieldErrors) {
			error = new String[] { fieldError.getField(), fieldError.getDefaultMessage() };
			errors.add(error);
		}
		for (ObjectError objectError : globalErrors) {
			error = new String[] { objectError.getObjectName(), objectError.getDefaultMessage() };
			errors.add(error);
		}
		errors = errors.stream()
				.sorted((previous, next) -> {
					return previous[0].compareTo(next[0]);
				}).collect(toList());
		
		return new ErrorMessage(errors);
	}

	/**
	 * 
	 * @param field
	 * @param messageTemplate
	 * @param args
	 * @return
	 */
	public static ErrorMessage getErrorMessage(String field, String messageTemplate, Object... args) {
		List<String[]> errors = new ArrayList<String[]>();
		MessageSource messageSource = LocaleContextHolder.getMessageSource();
		Locale locale = LocaleContextHolder.getLocale();
		String message = messageSource.getMessage(messageTemplate, args, locale);
		errors.add(new String[] { field, message });
		return new ErrorMessage(errors);
	}

	/**
	 * 
	 * @param e
	 * @return
	 */
	public static ErrorMessage getErrorMessage(AbstractPropertyExistsException e) {
		List<String[]> errors = new ArrayList<String[]>();
		MessageSource messageSource = LocaleContextHolder.getMessageSource();
		Locale locale = LocaleContextHolder.getLocale();
		String message = messageSource.getMessage(e.getMessageTemplate(), asArray(e.getTemplateParam()), locale);
		errors.add(new String[] { e.getField(), message });
		return new ErrorMessage(errors);
	}
}
