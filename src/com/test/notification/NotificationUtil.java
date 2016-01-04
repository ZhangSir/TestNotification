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
 * @��˾: �Ͼ�������Ϣ�������޹�˾
 * @CLASS:NotificationUtil
 * @����: ֪ͨ��������
 * @����:zhangshuo
 * @�汾:v1.0
 * @����:2014��9��11�� ����2:40:46
 */
public class NotificationUtil {

	/**
	 * @��������:	��ʾ�Զ����Notification
	 * @����:zhangshuo
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
	 * 			����Activity��broadcast��requestCode��һ����˵���ò�������Ҫ��д��Ĭ��Ϊ0���ɣ�
	 * 			������Ĭд���ͣ���ΪP6���ϣ���������øò���Ϊ����0��ֵ������תActivityʱ���ܲ��ɹ�
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
		
		// ����֪ͨ�������ͼ
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

		// ��ȡ֪ͨ����������
		NotificationManager mNotificationManager = (NotificationManager) context
				.getSystemService(Context.NOTIFICATION_SERVICE);
		mNotificationManager.notify(id, mNotifiaction);
	}

	/**
	 * @����:zhangshuo
	 * @param context
	 * @param id
	 *            ָ��notification��Id
	 * @param intent
	 * @param largeIconRes
	 *            ������0�����ô���ʱ������Ϊ��ͼ�꣬����ʹ��smallIconRes��Ϊ��ͼ�꣬Сͼ�겻��ʾ
	 * @param smallIconRes
	 *            ������0������Ϊ�������ʹ��Ĭ��ͼƬ����Ϊ��ΪNotification���ô��Notification�޷���ʾ
	 * @param title
	 * @param content
	 * @param subText
	 *            ������ֻ��API16�����ϲ���Ч
	 * @param ticker
	 * @param when
	 *            ʱ�� ������0��
	 * @param number
	 *            ���� ������0��
	 * @param autoCancel
	 *            �Ƿ���notification���Զ���ʧ
	 * @param requestCode
	 * 			����Activity��broadcast��requestCode��һ����˵���ò�������Ҫ��д��Ĭ��Ϊ0���ɣ�
	 * 			������Ĭд���ͣ���ΪP6���ϣ���������øò���Ϊ����0��ֵ������תActivityʱ���ܲ��ɹ�          
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
			mBuilder.setTicker(ticker);// ��һ����ʾ��Ϣ��ʱ����ʾ��֪ͨ����
		if (when > 0)
			mBuilder.setWhen(when);
		if (number > 0)
			mBuilder.setNumber(number);

		mBuilder.setDefaults(Notification.DEFAULT_ALL);// DEFAULT_ALL�����������⡢�𶯾�ϵͳĬ�ϡ�
														// DEFAULT_SOUND��ϵͳĬ��������
														// DEFAULT_VIBRATE��ϵͳĬ���𶯡�
														// DEFAULT_LIGHTS��ϵͳĬ�����⡣
		// mBuilder.setLights(argb, onMs, offMs);//�趨ǰ��LED�Ƶ���˸���ʣ�������������ͣ�ٺ�������
		// mBuilder.setSound(sound,
		// streamType);//�趨һ��������������֪ͨ��ʱ����Ӧ������һ��Uri�Ĳ�������ʽΪ��file:///mnt/sdcard/Xxx.mp3��
		// mBuilder.setVibrate(pattern);//�趨�𶯵�ģʽ����һ��long���鱣����뼶������𶯡�

		mBuilder.setAutoCancel(autoCancel);// �Լ�ά��֪ͨ����ʧ
		// ����֪ͨ�������ͼ
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

		// ��ȡ֪ͨ����������
		NotificationManager mNotificationManager = (NotificationManager) context
				.getSystemService(Context.NOTIFICATION_SERVICE);
		mNotificationManager.notify(id, mBuilder.build());
	}

	/**
	 * @��������: ��ʾ��ͨ״̬��֪ͨ
	 * @����:zhangshuo
	 * @param context
	 * @param id
	 *            ָ��notification��Id
	 * @param intent
	 * @param largeIconRes
	 *            ������0�����ô���ʱ������Ϊ��ͼ�꣬����ʹ��smallIconRes��Ϊ��ͼ�꣬Сͼ�겻��ʾ
	 * @param smallIconRes
	 *            ������0������Ϊ�������ʹ��Ĭ��ͼƬ����Ϊ��ΪNotification���ô��Notification�޷���ʾ
	 * @param title
	 * @param content
	 * @param subText
	 * @param ticker
	 * @param when
	 *            ʱ�� ������0��
	 * @param number
	 *            ���� ������0��
	 * @param autoCancel
	 *            �Ƿ���notification���Զ���ʧ
	 * @param requestCode
	 * 			����Activity��broadcast��requestCode��һ����˵���ò�������Ҫ��д��Ĭ��Ϊ0���ɣ�
	 * 			������Ĭд���ͣ���ΪP6���ϣ���������øò���Ϊ����0��ֵ������תActivityʱ���ܲ��ɹ�            
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
			mBuilder.setTicker(ticker);// ��һ����ʾ��Ϣ��ʱ����ʾ��֪ͨ����
		if (when > 0)
			mBuilder.setWhen(when);
		if (number > 0)
			mBuilder.setNumber(number);

		mBuilder.setDefaults(Notification.DEFAULT_ALL);// DEFAULT_ALL�����������⡢�𶯾�ϵͳĬ�ϡ�
														// DEFAULT_SOUND��ϵͳĬ��������
														// DEFAULT_VIBRATE��ϵͳĬ���𶯡�
														// DEFAULT_LIGHTS��ϵͳĬ�����⡣
		// mBuilder.setLights(argb, onMs, offMs);//�趨ǰ��LED�Ƶ���˸���ʣ�������������ͣ�ٺ�������
		// mBuilder.setSound(sound,
		// streamType);//�趨һ��������������֪ͨ��ʱ����Ӧ������һ��Uri�Ĳ�������ʽΪ��file:///mnt/sdcard/Xxx.mp3��
		// mBuilder.setVibrate(pattern);//�趨�𶯵�ģʽ����һ��long���鱣����뼶������𶯡�

		mBuilder.setAutoCancel(autoCancel);// �Լ�ά��֪ͨ����ʧ
		// ����֪ͨ�������ͼ
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

		// ��ȡ֪ͨ����������
		NotificationManager mNotificationManager = (NotificationManager) context
				.getSystemService(Context.NOTIFICATION_SERVICE);
		mNotificationManager.notify(id, mBuilder.build());
	}

	/**
	 * @��������: ��ʾ���״̬��֪ͨ ��Android4.1���Ժ�İ汾��֧�֣�
	 * @����:zhangshuo
	 * @param context
	 * @param id
	 *            ָ��notification��Id
	 * @param intent
	 * @param largeIconRes
	 * @param smallIconRes
	 * @param title
	 * @param contents
	 * @param summaryText
	 * @param ticker
	 * @param when
	 *            ʱ��
	 * @param number
	 *            ����
	 * @param autoCancel
	 *            �Ƿ���notification���Զ���ʧ
	 * @param requestCode
	 * 			����Activity��broadcast��requestCode��һ����˵���ò�������Ҫ��д��Ĭ��Ϊ0���ɣ�
	 * 			������Ĭд���ͣ���ΪP6���ϣ���������øò���Ϊ����0��ֵ������תActivityʱ���ܲ��ɹ�            
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
			mBuilder.setTicker(ticker);// ��һ����ʾ��Ϣ��ʱ����ʾ��֪ͨ����
		if (when > 0)
			mBuilder.setWhen(when);
		if (number > 0)
			mBuilder.setNumber(number);

		mBuilder.setDefaults(Notification.DEFAULT_ALL);// DEFAULT_ALL�����������⡢�𶯾�ϵͳĬ�ϡ�
														// DEFAULT_SOUND��ϵͳĬ��������
														// DEFAULT_VIBRATE��ϵͳĬ���𶯡�
														// DEFAULT_LIGHTS��ϵͳĬ�����⡣
		// mBuilder.setLights(argb, onMs, offMs);//�趨ǰ��LED�Ƶ���˸���ʣ�������������ͣ�ٺ�������
		// mBuilder.setSound(sound,
		// streamType);//�趨һ��������������֪ͨ��ʱ����Ӧ������һ��Uri�Ĳ�������ʽΪ��file:///mnt/sdcard/Xxx.mp3��
		// mBuilder.setVibrate(pattern);//�趨�𶯵�ģʽ����һ��long���鱣����뼶������𶯡�

		mBuilder.setAutoCancel(autoCancel);// �Լ�ά��֪ͨ����ʧ
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

	// ��ʾ��������Notification
	// public static void showProgressNotification(){
	// manager = (NotificationManager)
	// getSystemService(Context.NOTIFICATION_SERVICE);
	// builder = new NotificationCompat.Builder(MainActivity.this)
	// .setSmallIcon(R.drawable.ic_launcher)
	// .setContentTitle("Picture Download")
	// .setContentText("Download in progress");
	// builder.setAutoCancel(true);
	// //ͨ��һ�����̣߳���̬���ӽ������̶�
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
	// ��ʾѭ����������Notification
	// public static void showRepeatProgressNotification(){
	// manager = (NotificationManager)
	// getSystemService(Context.NOTIFICATION_SERVICE);
	// builder = new NotificationCompat.Builder(MainActivity.this)
	// .setSmallIcon(R.drawable.ic_launcher)
	// .setContentTitle("Picture Download")
	// .setContentText("Download in progress");
	// builder.setProgress(0, 0, true);//����Ϊtrue����ʾ����
	// manager.notify(0, builder.build());
	//
	// //5��֮��ֹͣ����
	// new Thread(new Runnable() {
	// @Override
	// public void run() {
	// try {
	// Thread.sleep(5000);
	// } catch (InterruptedException e) {
	// e.printStackTrace();
	// }
	// builder.setProgress(100, 100, false);//����Ϊtrue����ʾ�̶�
	// manager.notify(0, builder.build());
	// }
	// }).start();
	// }

	// �Զ���Notification�Ĳ���
	// public static void showCustomNotification(){
	// RemoteViews contentViews = new RemoteViews(getPackageName(),
	// R.layout.custom_notification);
	// //ͨ���ؼ���Id��������
	// contentViews
	// .setImageViewResource(R.id.imageNo, R.drawable.btm1);
	// contentViews.setTextViewText(R.id.titleNo, "�Զ���֪ͨ����");
	// contentViews.setTextViewText(R.id.textNo, "�Զ���֪ͨ����");
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
	 * @��������: �ر�����Notification
	 * @����:zhangshuo
	 * @param context
	 */
	public static void cancelAllNotification(Context context) {
		NotificationManager mNotificationManager = (NotificationManager) context
				.getSystemService(Context.NOTIFICATION_SERVICE);
		mNotificationManager.cancelAll();
	}

	/**
	 * @��������: �ر�ָ��Notification
	 * @����:zhangshuo
	 * @param context
	 * @param id
	 *            Ҫ�رյ�Notification��ID
	 */
	public static void cancelNotification(Context context, int id) {
		NotificationManager mNotificationManager = (NotificationManager) context
				.getSystemService(Context.NOTIFICATION_SERVICE);
		mNotificationManager.cancel(id);
	}

	
	/**
	 * @��������: ��ʾ��ͨ״̬��֪ͨ
	 * @����:zhangshuo
	 * @param context
	 * @param id
	 *            ָ��notification��Id
	 * @param intent
	 * @param largeIconRes
	 *            ������0�����ô���ʱ������Ϊ��ͼ�꣬����ʹ��smallIconRes��Ϊ��ͼ�꣬Сͼ�겻��ʾ
	 * @param smallIconRes
	 *            ������0������Ϊ�������ʹ��Ĭ��ͼƬ����Ϊ��ΪNotification���ô��Notification�޷���ʾ
	 * @param title
	 * @param content
	 * @param subText
	 * @param ticker
	 * @param when
	 *            ʱ�� ������0��
	 * @param number
	 *            ���� ������0��
	 * @param autoCancel
	 *            �Ƿ���notification���Զ���ʧ
	 * @param requestCode
	 * 			����Activity��broadcast��requestCode��һ����˵���ò�������Ҫ��д��Ĭ��Ϊ0���ɣ�
	 * 			������Ĭд���ͣ���ΪP6���ϣ���������øò���Ϊ����0��ֵ������תActivityʱ���ܲ��ɹ�
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
			mBuilder.setTicker(ticker);// ��һ����ʾ��Ϣ��ʱ����ʾ��֪ͨ����
		if (when > 0)
			mBuilder.setWhen(when);
		if (number > 0)
			mBuilder.setNumber(number);

		mBuilder.setDefaults(Notification.DEFAULT_ALL);// DEFAULT_ALL�����������⡢�𶯾�ϵͳĬ�ϡ�
														// DEFAULT_SOUND��ϵͳĬ��������
														// DEFAULT_VIBRATE��ϵͳĬ���𶯡�
														// DEFAULT_LIGHTS��ϵͳĬ�����⡣
		// mBuilder.setLights(argb, onMs, offMs);//�趨ǰ��LED�Ƶ���˸���ʣ�������������ͣ�ٺ�������
		// mBuilder.setSound(sound,
		// streamType);//�趨һ��������������֪ͨ��ʱ����Ӧ������һ��Uri�Ĳ�������ʽΪ��file:///mnt/sdcard/Xxx.mp3��
		// mBuilder.setVibrate(pattern);//�趨�𶯵�ģʽ����һ��long���鱣����뼶������𶯡�

		mBuilder.setAutoCancel(autoCancel);// �Լ�ά��֪ͨ����ʧ
		// ����֪ͨ�������ͼ
		if (null != intent) {
			mBuilder.setContentIntent(PendingIntent.getBroadcast(context, requestCode, intent, PendingIntent.FLAG_UPDATE_CURRENT));
		} else {
			mBuilder.setContentIntent(PendingIntent.getBroadcast(context, requestCode, new Intent(), PendingIntent.FLAG_UPDATE_CURRENT));
		}

		// ��ȡ֪ͨ����������
		NotificationManager mNotificationManager = (NotificationManager) context
				.getSystemService(Context.NOTIFICATION_SERVICE);
		mNotificationManager.notify(id, mBuilder.build());
	}
}
