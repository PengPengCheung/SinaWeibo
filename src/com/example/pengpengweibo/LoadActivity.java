package com.example.pengpengweibo;

import util.Tools;
import android.media.audiofx.BassBoost.Settings;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
/*
 * 加载页面
 * 
 * */
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import android.view.Menu;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class LoadActivity extends Activity {

	ImageView loadImage;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.loadimage);
		initView();

		// 创建并实例化透明度渐变动画，取值范围为0.0 - 1.0，0.0表示完全不显示，1.0表示完全显示
		AlphaAnimation animation = new AlphaAnimation(0.1f, 1.0f);
		// 设置动画持续时间
		animation.setDuration(6000);
		// 设置组件动画
		loadImage.setAnimation(animation);
		animation.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation arg0) {
				// TODO Auto-generated method stub
				Toast.makeText(LoadActivity.this, "动画开始", Toast.LENGTH_SHORT)
						.show();
				init();
			}

			

			@Override
			public void onAnimationRepeat(Animation arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationEnd(Animation arg0) {
				// TODO Auto-generated method stub
				Toast.makeText(LoadActivity.this, "动画结束", Toast.LENGTH_SHORT)
						.show();
			}
		});
	}

	private void initView() {
		// TODO Auto-generated method stub
		loadImage = (ImageView) this.findViewById(R.id.loadimage);
	}
	
	public void init(){
		Tools.checkNetwork(LoadActivity.this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
