package com.tarena.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tarena.dao.ExamDAO;
import com.tarena.dao.impl.ExamDAOImpl;
import com.tarena.entity.Option;
import com.tarena.entity.Paper;
import com.tarena.entity.Question;
import com.tarena.entity.UserhandPaper;
import com.tarena.serverUtil.ConnectionUtil;
import com.tarena.serverUtil.ServiceTool;
import com.tarena.service.ExamService;

public class ExamServiceImpl implements ExamService{

	private ExamDAO examDAO;
	
	public ExamServiceImpl(ExamDAO examDAO){
		this.examDAO = examDAO;
	}
	
	public Paper loadPaper(Integer paperId){
		Connection conn = null;;
		
		Paper paper = null;
		
		List<Question> questions = new ArrayList<Question>(); 
	
		try{
			conn = ConnectionUtil.getConnection();
			paper = examDAO.findPaper(paperId,conn);
			
			for(int i=1; i<21; i++){
				questions.addAll(examDAO.findQuestion(paperId, i,conn));
			}
			
			for(int i=0, size=questions.size(); i<size; i++){
				Question question = questions.get(i);
				List<Option> options = examDAO.findOptionByQuestionId(question.getId(), conn);
				question.setOptions(options);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		paper.setQuestions(questions);
		ServiceTool.close(conn);
		return paper;
	}


	public UserhandPaper handExamPaper(UserhandPaper userHandPaper) {
		UserhandPaper up = null;
		Connection conn = null;
		
		try {
			conn = ConnectionUtil.getConnection();
			up = examDAO.handExamPaper(userHandPaper.getUserId(), 
					userHandPaper.getPaperId(), userHandPaper.getScore(), conn);
			
			
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		
		
		
		
		return up;
	}

}
