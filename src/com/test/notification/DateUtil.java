package com.test.notification;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;


public class DateUtil {

	private static String datePattern = "yyyy-MM-dd_HH-mm-ss";

	private static final String FORMAT = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 格式化日期到时分秒 yyyy-MM-dd_HH-mm-ss
	 */
	public static String formatDateTime(Date date) {
		SimpleDateFormat dateTimeFormat = new SimpleDateFormat(datePattern);
		String result = null;
		try {
			result = dateTimeFormat.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static Date str2Date(String str) {
		return str2Date(str, null);
	}

	public static Calendar str2Calendar(String str) {
		return str2Calendar(str, null);

	}

	public static Calendar str2Calendar(String str, String format) {

		Date date = str2Date(str, format);
		if (date == null) {
			return null;
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);

		return c;

	}

	public static String date2Str(Calendar c) {// yyyy-MM-dd HH:mm:ss
		return date2Str(c, null);
	}

	public static String date2Str(Calendar c, String format) {
		if (c == null) {
			return null;
		}
		return date2Str(c.getTime(), format);
	}

	public static String date2Str(Date d) {// yyyy-MM-dd HH:mm:ss
		return date2Str(d, null);
	}

	public static String date2Str(Date d, String format) {// yyyy-MM-dd HH:mm:ss
		if (d == null) {
			return null;
		}
		if (format == null || format.length() == 0) {
			format = FORMAT;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String s = sdf.format(d);
		return s;
	}

	public static String getCurDateStr() {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		return c.get(Calendar.YEAR) + "-" + (c.get(Calendar.MONTH) + 1) + "-" + c.get(Calendar.DAY_OF_MONTH) + "-"
				+ c.get(Calendar.HOUR_OF_DAY) + ":" + c.get(Calendar.MINUTE) + ":" + c.get(Calendar.SECOND);
	}

	/**
	 * 获得当前日期的字符串格式
	 * 
	 * @param format
	 * @return
	 */
	public static String getCurDateStr(String format) {
		Calendar c = Calendar.getInstance();
		return date2Str(c, format);
	}

	// 格式到秒
	public static String getMillon(long time) {

		return new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(time);

	}
	
	// 格式到秒
	public static String getTimeByTimeStamp(long time) {

		return new SimpleDateFormat("yyyy-M-d H:mm").format(time);

	}

	/**
	 * 格式到天
	 * 
	 * @param time
	 * @return
	 */
	public static String getDay(long time) {

		return new SimpleDateFormat("yyyy.MM.dd").format(time);

	}

	/**
	 * @方法描述: 格式化得到 小时：分钟
	 * @作者:zhangshuo
	 * @param time
	 * @return
	 */
	public static String getMinute(long time) {
		return new SimpleDateFormat("HH:mm").format(time);
	}

	/**
	 * 获取秒
	 * 
	 * @param time
	 * @return
	 */
	public static String getSecond(long time) {
		return new SimpleDateFormat("HH:mm:ss").format(time);
	}

	// 格式到毫秒
	public static String getSMillon(long time) {

		return new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-SSS").format(time);

	}

	/**
	 * 字符串时间转换为固定的格式
	 * 
	 * @param str
	 * @param format
	 * @return
	 */
	public static Date str2Date(String str, String format) {
		if (str == null || str.length() == 0) {
			return null;
		}
		if (format == null || format.length() == 0) {
			format = FORMAT;
		}
		Date date = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			date = sdf.parse(str);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;

	}


	/**
	 * 格式化当前时间
	 * 
	 * @return
	 */
	public static String getNowTime() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	}

	/**
	 * @方法描述: 日期选择器
	 * @作者:zhangshuo
	 * @param context
	 * @param editText
	 * @return
	 */
	public static Dialog createDataDialog(Context context, final EditText editText) {
		// 用来获取日期和时间的
		Calendar calendar = Calendar.getInstance();
		Dialog dialog = null;
		DatePickerDialog.OnDateSetListener dateListener = new DatePickerDialog.OnDateSetListener() {
			@Override
			public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
				// Calendar月份是从0开始,所以month要加1
				editText.setText(DateUtil
						.getDay(DateUtil.str2Date(year + "-" + (month + 1) + "-" + dayOfMonth + " 00:00:00").getTime())
						.replace(".", "-"));
			}
		};
		dialog = new DatePickerDialog(context, dateListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
				calendar.get(Calendar.DAY_OF_MONTH));
		return dialog;
	}

	/**
	 * 计算两个时间之间相隔的天数
	 * 
	 * @param milliTime1
	 * @param milliTime2
	 * @return
	 */
	public static long caculateDifferDays(long milliTime1, long milliTime2) {
		long differDays = 0;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date1 = new Date(milliTime1);
		Date date2 = new Date(milliTime2);
		try {
			date1 = format.parse(format.format(date1));
			date2 = format.parse(format.format(date2));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		differDays = (date1.getTime() - date2.getTime()) / (1000 * 60 * 60 * 24);
		return Math.abs(differDays);
	}

	/**
	 * @方法描述: 时间选择器
	 * @作者:zhangshuo
	 * @param context
	 * @param editText
	 * @return
	 */
	public static Dialog createTimerDialog(Context context, final EditText editText) {
		// 用来获取日期和时间的
		Calendar calendar = Calendar.getInstance();
		Dialog dialog = null;
		TimePickerDialog.OnTimeSetListener timeListener = new TimePickerDialog.OnTimeSetListener() {

			@Override
			public void onTimeSet(TimePicker timerPicker, int hourOfDay, int minute) {
				editText.setText(hourOfDay + ":" + minute);
			}
		};
		dialog = new TimePickerDialog(context, timeListener, calendar.get(Calendar.HOUR_OF_DAY),
				calendar.get(Calendar.MINUTE), false); // 是否为二十四制
		return dialog;
	}


	/**
	 * 比一天还要长
	 * 
	 * @return
	 */
	public static boolean isUpperThanDay(long milliseconds) {
		long timeGap = (milliseconds - new Date().getTime()) / 1000;// 与现在时间相差秒数
		if (timeGap > 24 * 60 * 60) {
			return true;
		}
		return false;
	}
	
	/**
	 * 比一天还要长
	 * 
	 * @return
	 */
	public static boolean isUpperThanDay(long milliseconds,int day) {
		long timeGap = (milliseconds - new Date().getTime()) / 1000;// 与现在时间相差秒数
		if (timeGap > 24 * 60 * 60*day) {
			return true;
		}
		return false;
	}

}
