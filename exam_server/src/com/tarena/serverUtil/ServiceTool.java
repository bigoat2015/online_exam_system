package com.tarena.serverUtil;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Wrapper;
import java.util.ArrayList;
import java.util.List;

/**
 * 服务端工具类。
 * @author xiaoyao
 *
 */
public class ServiceTool {

	public static List<Integer> StringToIntegerArray(String str){
		
		List<Integer> answers = new ArrayList<Integer>();
		
		String[] ss = str.trim().split("/");
		for(String s:ss){
			Integer i = Integer.parseInt(s);
			answers.add(i);
		}
		
		return answers;
		
	}
	
	public static void close(Connection conn){
		try {
			if(conn != null){
				conn.close();
				conn = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(InputStream in, OutputStream os,Socket socket){
		
		try {
			try{
				if(in != null){
					in.close();
					in = null;
				}
				} finally {
					try{
						if(os != null){
							os.close();
							os = null;
						}
					}finally{
						if(socket != null){
							socket.close();
							socket = null;
						}
					} 
			}
				
		} catch (IOException e) {
		}
	}
	
	public static void close(ResultSet rs, Statement stat){
		try {
			try{
				if(rs != null){
					rs.close();
					rs = null;
				}
				}finally{
					if(stat != null){
						stat.close();
						stat = null;
					}
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void close(Connection conn, ResultSet rs, Statement stat){
		close(conn);
		close(rs,stat);
	}
	
}
