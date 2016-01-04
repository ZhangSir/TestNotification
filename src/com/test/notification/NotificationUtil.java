package com.test.notification;

import java.util.Date;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.widget.RemoteViews;

/**
 * @公司: 南京红松信息技术有限公司
 * @CLASS:NotificationUtil
 * @描述: 通知栏工具类
 * @作者:zhangshuo
 * @版本:v1.0
 * @日期:2014年9月11日 下午2:40:46
 */
public class NotificationUtil {

	/**
	 * @方法描述:	显示自定义的Notification
	 * @作者:zhangshuo
	 * @param context
	 * @param id
	 * @param intent
	 * @param largeIconRes
	 * @param smallIconRes
	 * @param title
	 * @param content
	 * @param subText
	 * @param ticker
	 * @param when
	 * @param number
	 * @param autoCancel
	 * @param requestCode
	 * 			启动Activity或broadcast的requestCode，一般来说，该参数不需要填写，默认为0即可；
	 * 			但是在默写机型（华为P6）上，如果不设置该参数为大于0的值，在跳转Activity时可能不成功
	 */
	@SuppressLint("NewApi")
	public static void showNotificationCustom(Context context, int id,
			Intent intent, int largeIconRes, int smallIconRes, String title,
			String content, String subText, String ticker, long when,
			int number, boolean autoCancel, int requestCode) {
		Notification.Builder mBuilder = new Notification.Builder(context)
				.setDefaults(Notification.DEFAULT_ALL)
				.setAutoCancel(autoCancel);
		
		if (smallIconRes < 0)
			smallIconRes = R.drawable.ic_launcher;
		mBuilder.setSmallIcon(smallIconRes);
		
		// 设置通知主题的意图
		if (null != intent) {
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
					| Intent.FLAG_ACTIVITY_NEW_TASK
					| Intent.FLAG_ACTIVITY_SINGLE_TOP);
			mBuilder.setContentIntent(PendingIntent.getActivity(context, requestCode,
					intent, PendingIntent.FLAG_UPDATE_CURRENT));
		} else {
			mBuilder.setContentIntent(PendingIntent.getActivity(context, requestCode,
					new Intent(), PendingIntent.FLAG_UPDATE_CURRENT));
		}

		Notification mNotifiaction = mBuilder.build();

		RemoteViews mRemoteView = new RemoteViews(context.getPackageName(),
				R.layout.layout_custom_notification);
		if (largeIconRes > 0)
			mRemoteView.setImageViewResource(R.id.iv_custom_notification_large,
					largeIconRes);
		if (smallIconRes > 0)
			mRemoteView.setImageViewResource(R.id.iv_custom_notification_small,
					smallIconRes);
		if (null != title) {
			mRemoteView.setTextViewText(R.id.tv_custom_notification_title,
					title);
		} else {
			mRemoteView.setViewVisibility(R.id.tv_custom_notification_title,
					View.GONE);
		}
		if (null != content) {
			mRemoteView.setTextViewText(R.id.tv_custom_notification_content,
					content);
		} else {
			mRemoteView.setViewVisibility(R.id.tv_custom_notification_content,
					View.GONE);
		}
		if (when > 0) {
			mRemoteView.setTextViewText(R.id.tv_custom_notification_time,
					DateUtil.getMinute(when));
		} else {
			mRemoteView.setTextViewText(R.id.tv_custom_notification_time,
					DateUtil.getMinute(new Date().getTime()));
		}
		if (number > 0) {
			mRemoteView.setTextViewText(R.id.tv_custom_notification_number,
					String.valueOf(number));
		} else {
			mRemoteView.setViewVisibility(R.id.tv_custom_notification_number,
					View.GONE);
		}
		mNotifiaction.contentView = mRemoteView;

		// 获取通知管理器对象
		NotificationManager mNotificationManager = (NotificationManager) context
				.getSystemService(Context.NOTIFICATION_SERVICE);
		mNotificationManager.notify(id, mNotifiaction);
	}

	/**
	 * @作者:zhangshuo
	 * @param context
	 * @param id
	 *            指定notification的Id
	 * @param intent
	 * @param largeIconRes
	 *            （大于0）设置此项时，此项为大图标，否则使用smallIconRes作为大图标，小图标不显示
	 * @param smallIconRes
	 *            （大于0）此项为必填，否则使用默认图片，因为不为Notification设置此项，Notification无法显示
	 * @param title
	 * @param content
	 * @param subText
	 *            此属性只在API16及以上才有效
	 * @param ticker
	 * @param when
	 *            时间 （大于0）
	 * @param number
	 *            数量 （大于0）
	 * @param autoCancel
	 *            是否点击notification后自动消失
	 * @param requestCode
	 * 			启动Activity或broadcast的requestCode，一般来说，该参数不需要填写，默认为0即可；
	 * 			但是在默写机型（华为P6）上，如果不设置该参数为大于0的值，在跳转Activity时可能不成功          
	 */
	@SuppressLint("NewApi")
	public static void showNotification(Context context, int id, Intent intent,
			int largeIconRes, int smallIconRes, String title, String content,
			String subText, String ticker, long when, int number,
			boolean autoCancel, int requestCode) {

		Notification.Builder mBuilder = new Notification.Builder(context);

		if (largeIconRes > 0) {
			mBuilder.setLargeIcon(BitmapFactory.decodeResource(
					context.getResources(), largeIconRes));
		}
		if (smallIconRes < 0)
			smallIconRes = R.drawable.ic_launcher;
		mBuilder.setSmallIcon(smallIconRes);

		if (null != title)
			mBuilder.setContentTitle(title);
		if (null != content)
			mBuilder.setContentText(content);
		if (null != subText)
			mBuilder.setSubText(subText);
		if (null != ticker)
			mBuilder.setTicker(ticker);// 第一次提示消息的时候显示在通知栏上
		if (when > 0)
			mBuilder.setWhen(when);
		if (number > 0)
			mBuilder.setNumber(number);

		mBuilder.setDefaults(Notification.DEFAULT_ALL);// DEFAULT_ALL：铃声、闪光、震动均系统默认。
														// DEFAULT_SOUND：系统默认铃声。
														// DEFAULT_VIBRATE：系统默认震动。
														// DEFAULT_LIGHTS：系统默认闪光。
		// mBuilder.setLights(argb, onMs, offMs);//设定前置LED灯的闪烁速率，持续毫秒数，停顿毫秒数。
		// mBuilder.setSound(sound,
		// streamType);//设定一个铃声，用于在通知的时候响应。传递一个Uri的参数，格式为“file:///mnt/sdcard/Xxx.mp3”
		// mBuilder.setVibrate(pattern);//设定震动的模式，以一个long数组保存毫秒级间隔的震动。

		mBuilder.setAutoCancel(autoCancel);// 自己维护通知的消失
		// 设置通知主题的意图
		if (null != intent) {
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
					| Intent.FLAG_ACTIVITY_NEW_TASK
					| Intent.FLAG_ACTIVITY_SINGLE_TOP);
			mBuilder.setContentIntent(PendingIntent.getActivity(context, requestCode,
					intent, PendingIntent.FLAG_UPDATE_CURRENT));
		} else {
			mBuilder.setContentIntent(PendingIntent.getActivity(context, requestCode,
					new Intent(), PendingIntent.FLAG_UPDATE_CURRENT));
		}

		// 获取通知管理器对象
		NotificationManager mNotificationManager = (NotificationManager) context
				.getSystemService(Context.NOTIFICATION_SERVICE);
		mNotificationManager.notify(id, mBuilder.build());
	}

	/**
	 * @方法描述: 显示普通状态栏通知
	 * @作者:zhangshuo
	 * @param context
	 * @param id
	 *            指定notification的Id
	 * @param intent
	 * @param largeIconRes
	 *            （大于0）设置此项时，此项为大图标，否则使用smallIconRes作为大图标，小图标不显示
	 * @param smallIconRes
	 *            （大于0）此项为必填，否则使用默认图片，因为不为Notification设置此项，Notification无法显示
	 * @param title
	 * @param content
	 * @param subText
	 * @param ticker
	 * @param when
	 *            时间 （大于0）
	 * @param number
	 *            数量 （大于0）
	 * @param autoCancel
	 *            是否点击notification后自动消失
	 * @param requestCode
	 * 			启动Activity或broadcast的requestCode，一般来说，该参数不需要填写，默认为0即可；
	 * 			但是在默写机型（华为P6）上，如果不设置该参数为大于0的值，在跳转Activity时可能不成功            
	 */
	public static void showNotificationV4(Context context, int id,
			Intent intent, int largeIconRes, int smallIconRes, String title,
			String content, String subText, String ticker, long when,
			int number, boolean autoCancel, int requestCode) {

		NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
				context);
		if (largeIconRes > 0) {
			mBuilder.setLargeIcon(BitmapFactory.decodeResource(
					context.getResources(), largeIconRes));
		}
		if (smallIconRes < 0)
			smallIconRes = R.drawable.ic_launcher;
		mBuilder.setSmallIcon(smallIconRes);

		if (null != title)
			mBuilder.setContentTitle(title);
		if (null != content)
			mBuilder.setContentText(content);
		if (null != subText)
			mBuilder.setSubText(subText);
		if (null != ticker)
			mBuilder.setTicker(ticker);// 第一次提示消息的时候显示在通知栏上
		if (when > 0)
			mBuilder.setWhen(when);
		if (number > 0)
			mBuilder.setNumber(number);

		mBuilder.setDefaults(Notification.DEFAULT_ALL);// DEFAULT_ALL：铃声、闪光、震动均系统默认。
														// DEFAULT_SOUND：系统默认铃声。
														// DEFAULT_VIBRATE：系统默认震动。
														// DEFAULT_LIGHTS：系统默认闪光。
		// mBuilder.setLights(argb, onMs, offMs);//设定前置LED灯的闪烁速率，持续毫秒数，停顿毫秒数。
		// mBuilder.setSound(sound,
		// streamType);//设定一个铃声，用于在通知的时候响应。传递一个Uri的参数，格式为“file:///mnt/sdcard/Xxx.mp3”
		// mBuilder.setVibrate(pattern);//设定震动的模式，以一个long数组保存毫秒级间隔的震动。

		mBuilder.setAutoCancel(autoCancel);// 自己维护通知的消失
		// 设置通知主题的意图
		if (null != intent) {
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
					| Intent.FLAG_ACTIVITY_NEW_TASK
					| Intent.FLAG_ACTIVITY_SINGLE_TOP);
			mBuilder.setContentIntent(PendingIntent.getActivity(context, requestCode,
					intent, PendingIntent.FLAG_UPDATE_CURRENT));
		} else {
			mBuilder.setContentIntent(PendingIntent.getActivity(context, requestCode,
					new Intent(), PendingIntent.FLAG_UPDATE_CURRENT));
		}

		// 获取通知管理器对象
		NotificationManager mNotificationManager = (NotificationManager) context
				.getSystemService(Context.NOTIFICATION_SERVICE);
		mNotificationManager.notify(id, mBuilder.build());
	}

	/**
	 * @方法描述: 显示大的状态栏通知 （Android4.1及以后的版本才支持）
	 * @作者:zhangshuo
	 * @param context
	 * @param id
	 *            指定notification的Id
	 * @param intent
	 * @param largeIconRes
	 * @param smallIconRes
	 * @param title
	 * @param contents
	 * @param summaryText
	 * @param ticker
	 * @param when
	 *            时间
	 * @param number
	 *            数量
	 * @param autoCancel
	 *            是否点击notification后自动消失
	 * @param requestCode
	 * 			启动Activity或broadcast的requestCode，一般来说，该参数不需要填写，默认为0即可；
	 * 			但是在默写机型（华为P6）上，如果不设置该参数为大于0的值，在跳转Activity时可能不成功            
	 */
	public static void showBigNotification(Context context, int id,
			Intent intent, int largeIconRes, int smallIconRes, String title,
			List<String> contents, String summaryText, String ticker,
			long when, int number, boolean autoCancel, int requestCode) {

		NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
				context);
		if (largeIconRes > 0) {
			mBuilder.setLargeIcon(BitmapFactory.decodeResource(
					context.getResources(), largeIconRes));
		}
		if (smallIconRes < 0)
			smallIconRes = R.drawable.ic_launcher;
		mBuilder.setSmallIcon(smallIconRes);

		if (null != ticker)
			mBuilder.setTicker(ticker);// 第一次提示消息的时候显示在通知栏上
		if (when > 0)
			mBuilder.setWhen(when);
		if (number > 0)
			mBuilder.setNumber(number);

		mBuilder.setDefaults(Notification.DEFAULT_ALL);// DEFAULT_ALL：铃声、闪光、震动均系统默认。
														// DEFAULT_SOUND：系统默认铃声。
														// DEFAULT_VIBRATE：系统默认震动。
														// DEFAULT_LIGHTS：系统默认闪光。
		// mBuilder.setLights(argb, onMs, offMs);//设定前置LED灯的闪烁速率，持续毫秒数，停顿毫秒数。
		// mBuilder.setSound(sound,
		// streamType);//设定一个铃声，用于在通知的时候响应。传递一个Uri的参数，格式为“file:///mnt/sdcard/Xxx.mp3”
		// mBuilder.setVibrate(pattern);//设定震动的模式，以一个long数组保存毫秒级间隔的震动。

		mBuilder.setAutoCancel(autoCancel);// 自己维护通知的消失
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
				| Intent.FLAG_ACTIVITY_NEW_TASK
				| Intent.FLAG_ACTIVITY_SINGLE_TOP);
		mBuilder.setContentIntent(PendingIntent.getActivity(context, requestCode, intent,
				PendingIntent.FLAG_CANCEL_CURRENT));

		NotificationCompat.InboxStyle style = new NotificationCompat.InboxStyle();
		if (null != title)
			style.setBigContentTitle(title);
		if (null != summaryText)
			style.setSummaryText(summaryText);
		if (null != contents && contents.size() > 0) {
			for (int i = 0; i < contents.size(); i++) {
				style.addLine(contents.get(i));
			}
		}

		mBuilder.setStyle(style);

		NotificationManager mNotificationManager = (NotificationManager) context
				.getSystemService(Context.NOTIFICATION_SERVICE);
		mNotificationManager.notify(id, mBuilder.build());
	}

	// 显示进度条的Notification
	// public static void showProgressNotification(){
	// manager = (NotificationManager)
	// getSystemService(Context.NOTIFICATION_SERVICE);
	// builder = new NotificationCompat.Builder(MainActivity.this)
	// .setSmallIcon(R.drawable.ic_launcher)
	// .setContentTitle("Picture Download")
	// .setContentText("Download in progress");
	// builder.setAutoCancel(true);
	// //通过一个子线程，动态增加进度条刻度
	// new Thread(new Runnable() {
	// @Override
	// public void run() {
	// int incr;
	// for (incr = 0; incr <= 100; incr += 5) {
	// builder.setProgress(100, incr, false);
	// manager.notify(0, builder.build());
	// try {
	// Thread.sleep(300);
	// } catch (InterruptedException e) {
	// Log.i(TAG, "sleep failure");
	// }
	// }
	// builder.setContentText("Download complete")
	// .setProgress(0, 0, false);
	// manager.notify(0, builder.build());
	// }
	// }).start();
	// }
	// 显示循环进度条的Notification
	// public static void showRepeatProgressNotification(){
	// manager = (NotificationManager)
	// getSystemService(Context.NOTIFICATION_SERVICE);
	// builder = new NotificationCompat.Builder(MainActivity.this)
	// .setSmallIcon(R.drawable.ic_launcher)
	// .setContentTitle("Picture Download")
	// .setContentText("Download in progress");
	// builder.setProgress(0, 0, true);//设置为true，表示流动
	// manager.notify(0, builder.build());
	//
	// //5秒之后还停止流动
	// new Thread(new Runnable() {
	// @Override
	// public void run() {
	// try {
	// Thread.sleep(5000);
	// } catch (InterruptedException e) {
	// e.printStackTrace();
	// }
	// builder.setProgress(100, 100, false);//设置为true，表示刻度
	// manager.notify(0, builder.build());
	// }
	// }).start();
	// }

	// 自定义Notification的布局
	// public static void showCustomNotification(){
	// RemoteViews contentViews = new RemoteViews(getPackageName(),
	// R.layout.custom_notification);
	// //通过控件的Id设置属性
	// contentViews
	// .setImageViewResource(R.id.imageNo, R.drawable.btm1);
	// contentViews.setTextViewText(R.id.titleNo, "自定义通知标题");
	// contentViews.setTextViewText(R.id.textNo, "自定义通知内容");
	//
	// Intent intent = new Intent(MainActivity.this,
	// ResultActivity.class);
	//
	// PendingIntent pendingIntent = PendingIntent.getActivity(
	// MainActivity.this, 0, intent,
	// PendingIntent.FLAG_CANCEL_CURRENT);
	// NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
	// MainActivity.this).setSmallIcon(R.drawable.ic_launcher)
	// .setContentTitle("My notification")
	// .setTicker("new message");
	// mBuilder.setAutoCancel(true);
	//
	// mBuilder.setContentIntent(pendingIntent);
	// mBuilder.setContent(contentViews);
	// mBuilder.setAutoCancel(true);
	// NotificationManager mNotificationManager = (NotificationManager)
	// getSystemService(Context.NOTIFICATION_SERVICE);
	// mNotificationManager.notify(10, mBuilder.build());
	// }
	/**
	 * @方法描述: 关闭所有Notification
	 * @作者:zhangshuo
	 * @param context
	 */
	public static void cancelAllNotification(Context context) {
		NotificationManager mNotificationManager = (NotificationManager) context
				.getSystemService(Context.NOTIFICATION_SERVICE);
		mNotificationManager.cancelAll();
	}

	/**
	 * @方法描述: 关闭指定Notification
	 * @作者:zhangshuo
	 * @param context
	 * @param id
	 *            要关闭的Notification的ID
	 */
	public static void cancelNotification(Context context, int id) {
		NotificationManager mNotificationManager = (NotificationManager) context
				.getSystemService(Context.NOTIFICATION_SERVICE);
		mNotificationManager.cancel(id);
	}

	
	/**
	 * @方法描述: 显示普通状态栏通知
	 * @作者:zhangshuo
	 * @param context
	 * @param id
	 *            指定notification的Id
	 * @param intent
	 * @param largeIconRes
	 *            （大于0）设置此项时，此项为大图标，否则使用smallIconRes作为大图标，小图标不显示
	 * @param smallIconRes
	 *            （大于0）此项为必填，否则使用默认图片，因为不为Notification设置此项，Notification无法显示
	 * @param title
	 * @param content
	 * @param subText
	 * @param ticker
	 * @param when
	 *            时间 （大于0）
	 * @param number
	 *            数量 （大于0）
	 * @param autoCancel
	 *            是否点击notification后自动消失
	 * @param requestCode
	 * 			启动Activity或broadcast的requestCode，一般来说，该参数不需要填写，默认为0即可；
	 * 			但是在默写机型（华为P6）上，如果不设置该参数为大于0的值，在跳转Activity时可能不成功
	 */
	public static void showNotificationToBroadCastV4(Context context, int id,
			Intent intent, int largeIconRes, int smallIconRes, String title,
			String content, String subText, String ticker, long when,
			int number, boolean autoCancel, int requestCode) {

		NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
				context);
		if (largeIconRes > 0) {
			mBuilder.setLargeIcon(BitmapFactory.decodeResource(
					context.getResources(), largeIconRes));
		}
		if (smallIconRes < 0)
			smallIconRes = R.drawable.ic_launcher;
		mBuilder.setSmallIcon(smallIconRes);

		if (null != title)
			mBuilder.setContentTitle(title);
		if (null != content)
			mBuilder.setContentText(content);
		if (null != subText)
			mBuilder.setSubText(subText);
		if (null != ticker)
			mBuilder.setTicker(ticker);// 第一次提示消息的时候显示在通知栏上
		if (when > 0)
			mBuilder.setWhen(when);
		if (number > 0)
			mBuilder.setNumber(number);

		mBuilder.setDefaults(Notification.DEFAULT_ALL);// DEFAULT_ALL：铃声、闪光、震动均系统默认。
														// DEFAULT_SOUND：系统默认铃声。
														// DEFAULT_VIBRATE：系统默认震动。
														// DEFAULT_LIGHTS：系统默认闪光。
		// mBuilder.setLights(argb, onMs, offMs);//设定前置LED灯的闪烁速率，持续毫秒数，停顿毫秒数。
		// mBuilder.setSound(sound,
		// streamType);//设定一个铃声，用于在通知的时候响应。传递一个Uri的参数，格式为“file:///mnt/sdcard/Xxx.mp3”
		// mBuilder.setVibrate(pattern);//设定震动的模式，以一个long数组保存毫秒级间隔的震动。

		mBuilder.setAutoCancel(autoCancel);// 自己维护通知的消失
		// 设置通知主题的意图
		if (null != intent) {
			mBuilder.setContentIntent(PendingIntent.getBroadcast(context, requestCode, intent, PendingIntent.FLAG_UPDATE_CURRENT));
		} else {
			mBuilder.setContentIntent(PendingIntent.getBroadcast(context, requestCode, new Intent(), PendingIntent.FLAG_UPDATE_CURRENT));
		}

		// 获取通知管理器对象
		NotificationManager mNotificationManager = (NotificationManager) context
				.getSystemService(Context.NOTIFICATION_SERVICE);
		mNotificationManager.notify(id, mBuilder.build());
	}
}
