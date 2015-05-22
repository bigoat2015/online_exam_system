package com.tarena.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.tarena.dao.UserDAO;
import com.tarena.entity.User;
import com.tarena.entity.UserhandPaper;

public class UserDAOImpl implements UserDAO{

	public User loginUser(String uid, String password,Connection conn) throws SQLException {
		User user = null; 
		String sql = "select * from t_user where uid=? and password=?";
		PreparedStatement ps = 
			conn.prepareStatement(sql);
		ps.setString(1, uid);
		ps.setString(2, password);
		ResultSet rs = 
			ps.executeQuery();
		if(rs.next()){
			user = new User();
			user.setId(rs.getInt("id"));
			user.setUid(rs.getString("uid"));
			user.setName(rs.getString("name"));
			user.setPassword(rs.getString("password"));
			user.setPhone(rs.getString("phone"));
			user.setEmail(rs.getString("email"));
		}
		return user;
	}

	@Override
	public User registUser(String uId, String name, String password,
			String phone, String email, Connection conn) throws SQLException {
		
		String sql = "insert into t_user(uid,name,password,phone,email) values(?,?,?,?,?)";
		
		PreparedStatement ps = 
			conn.prepareStatement(sql);
		ps.setString(1, uId);
		ps.setString(2, name);
		ps.setString(3, password);
		ps.setString(4, phone);
		ps.setString(5, email);
		ps.executeUpdate();
		
		User user = new User();
		user.setUid(uId);
		user.setName(name);
		user.setPassword(password);
		user.setPhone(phone);
		user.setEmail(email);
		
		return user;
	}

	@Override
	public User findUser(String uId, Connection conn) throws SQLException {
		User user = null; 
		String sql = "select * from t_user where uid=?";
		PreparedStatement ps = 
			conn.prepareStatement(sql);
		ps.setString(1, uId);
		ResultSet rs = 
			ps.executeQuery();
		if(rs.next()){
			user = new User();
			user.setId(rs.getInt("id"));
			user.setUid(rs.getString("uid"));
			user.setName(rs.getString("name"));
			user.setPassword(rs.getString("password"));
			user.setPhone(rs.getString("phone"));
			user.setEmail(rs.getString("email"));
		}
		return user;
	}


	public UserhandPaper fintScore(String uId, Connection conn) throws SQLException {
		
		UserhandPaper up = null; 
		String sql = "select * from t_handPaper where userId=?";
		PreparedStatement ps = 
			conn.prepareStatement(sql);
		ps.setString(1, uId);
		ResultSet rs = 
			ps.executeQuery();
		if(rs.next()){
			up = new UserhandPaper();
			up.setId(rs.getInt("id"));
			up.setUserId(rs.getString("userId"));
			up.setPaperId(rs.getInt("paperId"));
			up.setScore(rs.getFloat("score"));
			
		}
		return up;
	
	}





}
