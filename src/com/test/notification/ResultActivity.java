package com.test.notification;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;

public class ResultActivity extends ActionBarActivity {

	public static final String INTENT_FLAG_PUSH_MSG = "msg";
	
	private TextView tvContent;
	
	private String msg = "";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result);
		
		if(getIntent().hasExtra(INTENT_FLAG_PUSH_MSG)){
			msg = getIntent().getStringExtra(INTENT_FLAG_PUSH_MSG);
		}
		
		this.tvContent = (TextView) this.findViewById(R.id.tvContent);
		this.tvContent.setText(msg);
	}

}
