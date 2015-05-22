package com.tarena.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.tarena.entity.Option;
import com.tarena.entity.Paper;
import com.tarena.entity.Question;
import com.tarena.entity.UserhandPaper;
 
public interface ExamDAO {
	
	//ͨ��question��id���Ҹ�question����option
	public List<Option> findOptionByQuestionId(Integer questionId, Connection conn)throws SQLException;
	//�����Ծ�ͬһ�ȼ��µ����е���Ŀ
	public List<Question> findQuestion(Integer paperId,Integer level, Connection conn)throws SQLException;
	//ͨ���Ծ�idȥ���Ҹ��Ծ�
	public Paper findPaper(Integer paperId,Connection conn) throws SQLException;
 
	// �ύ�Ծ�
	public UserhandPaper handExamPaper(
			String userId, int paperId, float score, Connection conn) throws SQLException;
	
	 
} 
