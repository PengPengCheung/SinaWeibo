package pengpengweibo.db;

public class DBInfo {
	public static class DB {
		public static final String DB_NAME = "pengpengweibo_db";// 数据库名称
		public static final int VERSION = 1;// 数据库版本
	}

	public static class Table {
		public static final String USER_TABLE = "userinfo";// 用户表名称

		public static final String _ID = "_id";// 主键
		public static final String USER_ID = "user_id";// 用户编号
		public static final String USER_NAME = "user_name";// 用户名称
		public static final String TOKEN = "token";
		public static final String TOKEN_SECRET = "token_secret";
		public static final String DESCRIPTION = "description";// 用户描述
		public static final String USER_HEAD = "user_head";// 用户头像
		
		//创建用户表语句
		public static final String CREATE_USER_TABLE = "create table if not exists "
				+ USER_TABLE
				+ "("
				+ _ID
				+ " integer primary key autoincrement, "
				+ USER_ID
				+ " text, "
				+ TOKEN
				+ " text, "
				+ TOKEN_SECRET
				+ " text, "
				+ DESCRIPTION
				+ " text, " + USER_HEAD + " BLOG);";
		
		public static final String DROP_USER_TABLE = "drop table "+USER_TABLE;//删除用户表语句
	}
}
