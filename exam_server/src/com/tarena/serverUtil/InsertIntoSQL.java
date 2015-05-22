package com.tarena.serverUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *  测试 向数据库中 插入数据：测试。
 * @author Administrator
 *
 */
public class InsertIntoSQL {

	public static void main(String[] args) throws IOException {
		
		String sql;
		String sql2;
		
		float score = 5;
		int level = 1;
		String desc = "sdsdsdsd";
		String answer = "1/2";
		int paper = 1;
		
		String a = "aaaaaa";
		int b = 1;
		
		BufferedReader br = new BufferedReader(new FileReader(new File("src/corejava.txt"))); 	
		
		String tmp = null;
		int c = 0;
			

		
			
sql = "insert into t_question(score,q_level,que_desc,answer,paperId) values("+score+","+level+",'"+desc+"','"+answer+"',"+paper+");"; 
			
sql2 = "insert into t_option(option_desc,questionId) values('"+a+"',"+b+");";
			
	System.out.println(sql);
	System.out.println(sql2);
	
}
}