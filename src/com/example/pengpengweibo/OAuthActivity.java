package com.example.pengpengweibo;

import pengpengweibo.proj.User;
import util.OAuth;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

//用户授权操作
public class OAuthActivity extends Activity {
	private String callBackUrl = "pengpeng://OAuthActivity";
	private OAuth oauth = null;
	private static final String TAG="OAuthActivity";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.oauth);
		View dialogView = View.inflate(this, R.layout.oauth_dialog, null);
		Dialog dialog  =new Dialog(this, R.style.oauth_style);
		dialog.setContentView(dialogView);
		dialog.show();
		Button oauth_start = (Button)dialogView.findViewById(R.id.oauth_start);
		oauth_start.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				oauth = OAuth.getInstance();
				//请求AccessToken
				//callBackUrl 授权完成返回的页面
				oauth.requestAccessToken(OAuthActivity.this, callBackUrl);
			}
		});
	}
	
	//返回时执行
	@Override
	protected void onNewIntent(Intent intent) {
		// TODO Auto-generated method stub
		super.onNewIntent(intent);
		User user = oauth.getAccessToken(intent);
		Log.i(TAG, "-----user------"+user.toString());
	}
}
