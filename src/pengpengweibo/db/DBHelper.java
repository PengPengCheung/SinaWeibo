package pengpengweibo.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
	
	/*
	 * context 上下文
	 * name 数据库名称
	 * factory 游标工厂
	 * version 数据库版本
	 * */

	public DBHelper(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}
	
	//创建DBHelper上下文
	public DBHelper(Context context){
		this(context, DBInfo.DB.DB_NAME, null, DBInfo.DB.VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(DBInfo.Table.CREATE_USER_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL(DBInfo.Table.DROP_USER_TABLE);
		onCreate(db);
	}

}
