package com.tarena.service;




import com.tarena.entity.Paper;
import com.tarena.entity.UserhandPaper;

public interface ExamService {
	
	/**
	 * �����Ծ�
	 * @param paperId
	 * @return
	 */
	public Paper loadPaper(Integer paperId) ;
	
	/**
	 * ����
	 * @param userHandPaper
	 * @return
	 */
	public UserhandPaper handExamPaper(UserhandPaper userHandPaper) ;

	
	
}
