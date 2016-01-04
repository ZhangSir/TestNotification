package com.test.notification;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * 接收Notification被点击的广播
 * @author zhangshuo
 */
public class NotificationReceiver extends BroadcastReceiver{

	private static final String TAG = NotificationReceiver.class.getSimpleName();
	
	public static final String ACTION_NOTICE = "com.test.notification.NotificationReceiver.action";
	
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		Log.e(TAG, "接收到Notification的广播");
		
		intent.setClass(context, ResultActivity.class);
		//通过Notification启动Activity必须需要标志Intent.FLAG_ACTIVITY_NEW_TASK，否则无法启动；
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
				| Intent.FLAG_ACTIVITY_NEW_TASK
				| Intent.FLAG_ACTIVITY_SINGLE_TOP);
		context.startActivity(intent);
	}

}
