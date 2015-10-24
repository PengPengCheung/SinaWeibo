package pengpengweibo.dao;

import java.util.List;

import pengpengweibo.proj.User;

public class UserDao {
	
	//新增用户user
	public long insertUser(User user){
		return 1;
	}
	
	//更新用户user
	public int updateUser(User user){
		return 1;
	}
	
	//根据用户id删除对应的用户数据
	public int deleteUser(String user_id){
		return 1;
	}
	
	//根据用户id获得用户信息
	public User findUserById(String user_id){
		return null;
	}
	
	//获得所有的用户数据
	public List<User> findAllUsers(){
		return null;
	}
}
