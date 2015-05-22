package com.tarena.dao;

import java.sql.Connection;
import java.sql.SQLException;

import com.tarena.entity.User;
import com.tarena.entity.UserhandPaper;

public interface UserDAO {
	
	/**
	 * 用户登录：
	 * @param uid
	 * @param password
	 * @param conn
	 * @return
	 * @throws SQLException
	 */
	public User loginUser(String uid,String password,Connection conn)throws SQLException;
	
	/**
	 * 注册用户。
	 * @param uId
	 * @param name
	 * @param password
	 * @param phone
	 * @param email
	 * @param conn
	 * @return
	 * @throws SQLException
	 */
	public User registUser(
			String uId, 
			String name, 
			String password,
			String phone, 
			String email,
			Connection conn)throws SQLException;
	
	/**
	 * 查找用户
	 * @param uId
	 * @param conn
	 * @return
	 * @throws SQLException
	 */
	public User findUser(String uId,Connection conn) throws SQLException;
	
	/**
	 * 查看分数。
	 * @param uId
	 * @param conn
	 * @return
	 * @throws SQLException
	 */
	public UserhandPaper fintScore(String uId, Connection conn) throws SQLException;
}
