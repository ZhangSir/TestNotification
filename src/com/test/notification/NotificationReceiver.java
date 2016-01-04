package com.test.notification;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * ����Notification������Ĺ㲥
 * @author zhangshuo
 */
public class NotificationReceiver extends BroadcastReceiver{

	private static final String TAG = NotificationReceiver.class.getSimpleName();
	
	public static final String ACTION_NOTICE = "com.test.notification.NotificationReceiver.action";
	
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		Log.e(TAG, "���յ�Notification�Ĺ㲥");
		
		intent.setClass(context, ResultActivity.class);
		//ͨ��Notification����Activity������Ҫ��־Intent.FLAG_ACTIVITY_NEW_TASK�������޷�������
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
				| Intent.FLAG_ACTIVITY_NEW_TASK
				| Intent.FLAG_ACTIVITY_SINGLE_TOP);
		context.startActivity(intent);
	}

}
