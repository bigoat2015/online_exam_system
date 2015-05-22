package com.tarena.serverUtil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtil {

	private static Properties props;
	
	static{
		//加载数据库驱动
		try {
			props = new Properties();
			props.load(ConnectionUtil.class.getClassLoader().getResourceAsStream("db.properties"));
			
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection()throws SQLException{
		String url = props.getProperty("url");
		String user = props.getProperty("user");
		String password = props.getProperty("password");
		Connection conn = 
			DriverManager.getConnection(url, user, password);
		return conn;
	}
	
}
