package com.en.sphzb.dto;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateUtils {
	private static final Logger LOGGER = LoggerFactory.getLogger(DateUtils.class);
	/**
	 * 获取格式化对象
	 * @param strFormat 格式化的格式 如"yyyy-MM-dd"
	 * @return 格式化对象
	 */
	public static SimpleDateFormat getSimpleDateFormat(String strFormat) {
		if (strFormat != null && !"".equals(strFormat.trim())) {
			return new SimpleDateFormat(strFormat);
		}
		else {
			return new SimpleDateFormat();
		}
	}
	/**
	 * 将java.util.date型按照指定格式转为字符串
	 * @param date  源对象
	 * @param format 想得到的格式字符串
	 * @return 如：2010-05-28
	 */
	public static String toString(Date date, String format) {
		return getSimpleDateFormat(format).format(date);
	}

	public static Date getCurDate(String format) throws  Exception {
		return getSimpleDateFormat(format).parse(toString(new Date(),format));
	}
	/**
	 * getCurDate 取当前日期
	 * @return java.util.Date型日期
	 **/
	public static Date getCurDate() {
		return (new Date());
	}
}

