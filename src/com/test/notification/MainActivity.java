package com.test.notification;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends ActionBarActivity implements OnClickListener{

	private Button btn1, btn2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		btn1 = (Button) this.findViewById(R.id.btn1);
		btn2 = (Button) this.findViewById(R.id.btn2);
		
		btn1.setOnClickListener(this);
		btn2.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		int requestCode = 1001;
		String content = "";
		int notificationId = 0;
		int largeIconRes = R.drawable.ic_launcher;
		int smallIconRes  = R.drawable.ic_launcher;
		String subText = "点击查看详情";
		Intent it = null;
		switch (v.getId()) {
		case R.id.btn1:
			content = "这是一个启动Activity的Notification";
			notificationId = content.hashCode();
			
			it = new Intent(this, ResultActivity.class);
			it.putExtra(ResultActivity.INTENT_FLAG_PUSH_MSG, content);
			
			NotificationUtil.showNotificationV4( 
	        		this, 
	        		notificationId, 
	        		it, 
	        		largeIconRes, 
	        		smallIconRes, 
	        		content, 
	        		content, 
	        		subText, 
	        		content,
	        		-1,
	        		-1,
	        		true,
	        		requestCode);
			break;
		case R.id.btn2:
			content = "这是一个启动BroadCast的Notification";
			notificationId = content.hashCode();
			
			it = new Intent(NotificationReceiver.ACTION_NOTICE);
			it.putExtra(ResultActivity.INTENT_FLAG_PUSH_MSG, content);
			
			NotificationUtil.showNotificationToBroadCastV4( 
	        		this, 
	        		notificationId, 
	        		it, 
	        		largeIconRes, 
	        		smallIconRes, 
	        		content, 
	        		content, 
	        		subText, 
	        		content,
	        		-1,
	        		-1,
	        		true,
	        		requestCode);
			break;

		default:
			break;
		}
		
	}
	
	

}
