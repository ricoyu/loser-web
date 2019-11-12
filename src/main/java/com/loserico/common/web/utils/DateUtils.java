package com.loserico.common.web.utils;

import lombok.extern.slf4j.Slf4j;

import java.lang.ref.SoftReference;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.TimeZone;

import static java.util.Objects.requireNonNull;
import static org.apache.commons.lang3.StringUtils.isBlank;

/**
 * 日期格式化/转换相关帮助类
 * <p>
 * Copyright: Copyright (c) 2019/10/14 14:22
 * <p>
 * Company: Sexy Uncle Inc.
 * <p>
 *
 * @author Rico Yu  ricoyu520@gmail.com
 * @version 1.0
 */
@Slf4j
public class DateUtils {

	public static Map<TimeZone, Locale> timeZoneLocaleMap = new HashMap<>();

	public static TimeZone CHINA = TimeZone.getTimeZone("Asia/Shanghai");
	
	public static final String FMT_ISO_DATE = "yyyy-MM-dd";
	
	public static final String FMT_ISO_DATE_TIME = "yyyy-MM-dd HH:mm:ss";


	/**
	 * 根据指定的format解析日期字符串, 时区为"Asia/Shanghai", Locale为CHINA
	 *
	 * @param source
	 * @param format
	 * @return Date
	 */
	public static Date parse(String source, String format) {
		if (isBlank(source)) {
			return null;
		}
		Objects.requireNonNull(format);
		SimpleDateFormat simpleDateFormat = SimpleDateFormatHolder.formatFor(format);
		try {
			return simpleDateFormat.parse(source);
		} catch (ParseException e) {
			log.error(MessageFormat.format("Parse date string:[{0}]", source));
		}
		return null;
	}
	

	final static class SimpleDateFormatHolder {

		private static final ThreadLocal<SoftReference<Map<String, SimpleDateFormat>>> THREADLOCAL_FORMATS = new ThreadLocal<SoftReference<Map<String, SimpleDateFormat>>>();

		/**
		 * 获取SimpleDateFormat对象，timezone默认为Asia/Shanghai，locale为SIMPLIFIED_CHINESE
		 *
		 * @param pattern
		 * @return
		 */
		public static SimpleDateFormat formatFor(final String pattern) {
			requireNonNull(pattern);
			final SoftReference<Map<String, SimpleDateFormat>> ref = THREADLOCAL_FORMATS.get();
			Map<String, SimpleDateFormat> formats = ref == null ? null : ref.get();
			if (formats == null) {
				formats = new HashMap<String, SimpleDateFormat>();
				THREADLOCAL_FORMATS.set(new SoftReference<Map<String, SimpleDateFormat>>(formats));
			}

			SimpleDateFormat format = formats.get(pattern);
			if (format == null) {
				format = new SimpleDateFormat(pattern, Locale.CHINA);
				format.setTimeZone(CHINA);
				formats.put(pattern, format);
			}

			return format;
		}

		/**
		 * 根据format和timezone获取SimpleDateFormat对象，根据时区决定locale是什么
		 *
		 * @param pattern
		 * @param timezone
		 * @return
		 */
		public static SimpleDateFormat formatFor(final String pattern, TimeZone timezone) {
			requireNonNull(pattern);
			final SoftReference<Map<String, SimpleDateFormat>> ref = THREADLOCAL_FORMATS.get();
			Map<String, SimpleDateFormat> formats = ref == null ? null : ref.get();
			if (formats == null) {
				formats = new HashMap<String, SimpleDateFormat>();
				THREADLOCAL_FORMATS.set(new SoftReference<Map<String, SimpleDateFormat>>(formats));
			}

			SimpleDateFormat format = formats.get(pattern + timezone.getID());
			if (format == null) {
				Locale locale = timeZoneLocaleMap.get(timezone.getID());
				if (locale == null) {
					format = new SimpleDateFormat(pattern);
				} else {
					format = new SimpleDateFormat(pattern, locale);
				}
				format.setTimeZone(timezone);
				formats.put(pattern + timezone.getID(), format);
			}

			return format;
		}

		public static void clearThreadLocal() {
			THREADLOCAL_FORMATS.remove();
		}

	}
}
