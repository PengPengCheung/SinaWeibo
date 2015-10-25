package pengpengweibo.dao;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import pengpengweibo.db.DBHelper;
import pengpengweibo.db.DBInfo;
import pengpengweibo.proj.User;

public class UserDao {
	private DBHelper dbHelper = null;
	private SQLiteDatabase db = null;
	private ContentValues values = null;
	// User表字段
	String[] columns = { DBInfo.Table._ID, DBInfo.Table.USER_ID,
			DBInfo.Table.USER_NAME, DBInfo.Table.TOKEN,
			DBInfo.Table.TOKEN_SECRET, DBInfo.Table.DESCRIPTION,
			DBInfo.Table.USER_HEAD };

	public UserDao(Context context) {
		dbHelper = new DBHelper(context);
	}

	// 新增用户user
	public long insertUser(User user) {
		// 获得SQLiteDatabase，进行数据库操作
		db = dbHelper.getWritableDatabase();
		// 参数绑定对象
		values = new ContentValues();

		values.put(DBInfo.Table.USER_ID, user.getUser_id());
		values.put(DBInfo.Table.USER_NAME, user.getUser_name());
		values.put(DBInfo.Table.TOKEN, user.getToken());
		values.put(DBInfo.Table.TOKEN_SECRET, user.getToken_secret());
		values.put(DBInfo.Table.DESCRIPTION, user.getDescription());

		// 将图片类型的数据进行存储的时候，需要进行压缩转换才能存储到Blog类型中
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		// 为了实现数据存储，需要将数据类型进行转换
		BitmapDrawable userHead = (BitmapDrawable) user.getUser_head();
		// 图片压缩，第一个参数表示压缩的格式，第二个表示压缩质量，第三个表示将压缩后的数据写入哪个数据流
		// 将数据进行png编码，存储质量为100%
		userHead.getBitmap().compress(CompressFormat.PNG, 100, os);
		// 存储图片类型数据
		values.put(DBInfo.Table.USER_HEAD, os.toByteArray());
		// 进行插入操作，返回行号
		long rowId = db.insert(DBInfo.Table.USER_TABLE, DBInfo.Table.USER_NAME,
				values);
		// 释放资源
		db.close();

		return rowId;
	}

	// 更新用户user
	public int updateUser(User user) {
		return 1;
	}

	// 根据用户id删除对应的用户数据
	public int deleteUser(String user_id) {
		return 1;
	}

	// 根据用户id获得用户信息
	public User findUserById(String user_id) {
		return null;
	}

	// String[] columns = {DBInfo.Table._ID, DBInfo.Table.USER_ID,
	// DBInfo.Table.USER_NAME,DBInfo.Table.TOKEN,DBInfo.Table.TOKEN_SECRET,DBInfo.Table.DESCRIPTION,DBInfo.Table.USER_HEAD};
	// 获得所有的用户数据
	public List<User> findAllUsers() {
		db = dbHelper.getReadableDatabase();
		List<User> userList = null;
		User user = null;
		Cursor cursor = db.query(DBInfo.Table.USER_TABLE, columns, null, null,
				null, null, null, null);
		if (cursor != null && cursor.getCount() > 0) {
			userList = new ArrayList<User>(cursor.getCount());
			while(cursor.moveToNext()){
				user = new User();
				
				user.setId(cursor.getLong(cursor.getColumnIndex(DBInfo.Table._ID)));
				user.setUser_id(cursor.getString(cursor.getColumnIndex(DBInfo.Table.USER_ID)));
				user.setUser_name(cursor.getString(cursor.getColumnIndex(DBInfo.Table.USER_NAME)));
				user.setToken(cursor.getString(cursor.getColumnIndex(DBInfo.Table.TOKEN)));
				user.setToken_secret(cursor.getString(cursor.getColumnIndex(DBInfo.Table.TOKEN_SECRET)));
				user.setDescription(cursor.getString(cursor.getColumnIndex(DBInfo.Table.DESCRIPTION)));
				byte[] byteHead = cursor.getBlob(cursor.getColumnIndex(DBInfo.Table.USER_HEAD));
				
				ByteArrayInputStream is = new ByteArrayInputStream(byteHead);
				Drawable userHead = Drawable.createFromStream(is, "image");
				user.setUser_head(userHead);
				
				userList.add(user);
			}
		}
		
		cursor.close();
		return userList;
	}
}
