package util;

import pengpengweibo.proj.User;

import com.example.pengpengweibo.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.TextView;

public class Tools {
	
	public User getUserInfo(User user){
		return null;
	}
	
	public static void checkNetwork(final Context context) {
		if (!isNetworkAvailable(context)) {
			TextView msg = new TextView(context);
			msg.setText("当前没有可以使用的网络，请设置网络！");
			new AlertDialog.Builder(context)
					.setIcon(R.drawable.ic_com_sina_weibo_sdk_logo)
					.setTitle("网络状态提示")
					.setView(msg)
					.setPositiveButton("确定",
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface arg0,
										int arg1) {
									// TODO Auto-generated method stub
									context.startActivity(new Intent(
											android.provider.Settings.ACTION_WIRELESS_SETTINGS));
									 ((Activity) context).finish();
								}
							}).create().show();
		}

	}

	public static boolean isNetworkAvailable(Context context) {
		ConnectivityManager connectivityManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);

		if (connectivityManager == null) {
			// Toast.makeText(LoadActivity.this, "没有网络",
			// Toast.LENGTH_SHORT).show();
			return false;
		} else {
			NetworkInfo[] info = connectivityManager.getAllNetworkInfo();
			if (info != null) {
				for (NetworkInfo network : info) {
					if (network.getState() == NetworkInfo.State.CONNECTED) {
						// Toast.makeText(LoadActivity.this, "有网络",
						// Toast.LENGTH_SHORT).show();
						return true;
					}
				}
			}
		}
		return false;
	}
}
