package com.tarena.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tarena.dao.ExamDAO;
import com.tarena.entity.Option;
import com.tarena.entity.Paper;
import com.tarena.entity.Question;
import com.tarena.entity.UserhandPaper;
import com.tarena.serverUtil.ConnectionUtil;
import com.tarena.serverUtil.ServiceTool;

public class ExamDAOImpl implements ExamDAO{
	
	public List<Option> findOptionByQuestionId(Integer questionId ,Connection conn) throws SQLException {
		 
		Option option = null;
		
		List<Option> options = new ArrayList<Option>();
		
		String sql = "Select * from t_option where questionId=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, questionId);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			option = new Option();
			option.setId(rs.getInt("id"));
			option.setOption_desc(rs.getString("option_desc"));

			options.add(option);
		}
		
		ServiceTool.close(rs, ps);
		return options;
	}

	public List<Question> findQuestion(Integer paperId, Integer level,Connection conn)throws SQLException {
		
		Question question = null;
		
		List<Question> questions = new ArrayList<Question>();
		
		String sql = "Select * from t_question where paperId=? and q_level=?";
		
		PreparedStatement ps = 
				conn.prepareStatement(sql);
			ps.setInt(1, paperId);
			ps.setInt(2, level);
			ResultSet rs = 
				ps.executeQuery();
			while(rs.next()){
				 question = new Question();
				question.setId(rs.getInt("id"));
				question.setScore(rs.getFloat("score"));
				question.setQ_level(rs.getInt("q_level"));
				question.setQue_desc(rs.getString("que_desc"));
		
				// 通过静态 方法 StringToIntegerArray() 从数据库 读取问题的正确答案转换为Integer 集合。
				question.setAnswer(ServiceTool.StringToIntegerArray(rs.getString("answer")));
				
				// 添加问题到 问题集合中 返回
				questions.add(question);
			
			}
			
			ServiceTool.close(rs, ps);

			return questions;
	}

	public Paper findPaper(Integer paperId,Connection conn) throws SQLException {
		
		Paper paper = null;
		String sql = "select * from t_paper where id=?";
		
		PreparedStatement ps = 
			conn.prepareStatement(sql);
		ps.setInt(1, paperId);
		ResultSet rs = 
			ps.executeQuery();
		while(rs.next()){
			paper = new Paper();
			paper.setId(rs.getInt("id"));
			paper.setPaper_desc(rs.getString("paper_desc"));
			paper.setExam_time(rs.getInt("exam_time"));
			paper.setTotal_score(rs.getInt("total_score"));
					
		}
		
		ServiceTool.close(rs, ps);
		return paper;
	}


	public UserhandPaper handExamPaper(String userId, int paperId, float score, Connection conn)throws SQLException {
		
		String sql = "insert into t_handPaper(userId,paperId,score) values(?,?,?)";
		
		PreparedStatement ps = 
			conn.prepareStatement(sql);
		ps.setString(1, userId);
		ps.setInt(2, paperId);
		ps.setFloat(3, score);
		ps.executeUpdate();

		UserhandPaper userhandPaper = new UserhandPaper();
		userhandPaper.setPaperId(paperId);
		userhandPaper.setUserId(userId);
		userhandPaper.setScore(score);
		
		ServiceTool.close(null,ps);
		return userhandPaper;
	}

}
