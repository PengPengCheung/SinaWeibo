package com.example.pengpengweibo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.message.BasicNameValuePair;

import pengpengweibo.proj.User;
import util.OAuth;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

//用户授权操作
public class OAuthActivity extends Activity {
	private String callBackUrl = "pengpeng://OAuthActivity";
	private OAuth oauth = null;
	private static final String TAG = "OAuthActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.oauth);

		// 下面这段代码一定加上，不然不可以跳转到第三方授权页面
		// 作用貌似是另外新建一个线程做网络访问操作之类的。。
		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
					.permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}

		View dialogView = View.inflate(this, R.layout.oauth_dialog, null);
		Dialog dialog = new Dialog(this, R.style.oauth_style);
		dialog.setContentView(dialogView);
		dialog.show();
		Button oauth_start = (Button) dialogView.findViewById(R.id.oauth_start);
		oauth_start.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				oauth = OAuth.getInstance();
				// 请求AccessToken
				// callBackUrl 授权完成返回的页面
				oauth.requestAccessToken(OAuthActivity.this, callBackUrl);
			}
		});
	}

	// 返回时执行
	@Override
	protected void onNewIntent(Intent intent) {
		// TODO Auto-generated method stub
		super.onNewIntent(intent);
		Log.i(TAG, "--intent--begin");
		User user = oauth.getAccessToken(intent);
		Log.i(TAG, "--intent--after");
		this.getUserInfo(user);
		Log.i(TAG, "-----user------" + user.toString());
	}
	
	public User getUserInfo(User user) {
		String url = "https://api.weibo.com/2/users/show.json";// 从微博开发平台得到
		List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
		params.add(new BasicNameValuePair("source", oauth.APP_KEY));
		params.add(new BasicNameValuePair("uid", user.getUser_id()));
		HttpResponse response = oauth.signRequest(user.getToken(),
				user.getToken_secret(), url, params);
		Log.i(TAG, String.valueOf(response.getStatusLine().getStatusCode()));
		Log.i(TAG, "getUserInfo");
		//只允许get方法，不允许post方法，但是这个请求获取数据的方法为post方法，待修改
		//405的意思为该方法不允许
		if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
			Log.i(TAG, "ok success");
			Reader reader=null;
			try {
				InputStream is = response.getEntity().getContent();
				reader = new BufferedReader(new InputStreamReader(is),
						4000);
				StringBuilder buffer = new StringBuilder((int) response
						.getEntity().getContentLength());
				char[] buf = new char[1024];
				int length = 0;
				while ((length = reader.read(buf)) != -1) {
					buffer.append(buf, 0, length);
				}
				
				Log.i(TAG, "--buffer--"+buffer.toString());
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				try {
					reader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return null;
	}
}
