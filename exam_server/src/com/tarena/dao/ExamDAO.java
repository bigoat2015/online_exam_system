package com.tarena.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.tarena.entity.Option;
import com.tarena.entity.Paper;
import com.tarena.entity.Question;
import com.tarena.entity.UserhandPaper;
 
public interface ExamDAO {
	
	//通过question的id查找该question所有option
	public List<Option> findOptionByQuestionId(Integer questionId, Connection conn)throws SQLException;
	//查找试卷同一等级下的所有的题目
	public List<Question> findQuestion(Integer paperId,Integer level, Connection conn)throws SQLException;
	//通过试卷id去查找该试卷
	public Paper findPaper(Integer paperId,Connection conn) throws SQLException;
 
	// 提交试卷
	public UserhandPaper handExamPaper(
			String userId, int paperId, float score, Connection conn) throws SQLException;
	
	 
} 
