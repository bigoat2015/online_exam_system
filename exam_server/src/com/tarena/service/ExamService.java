package com.tarena.service;




import com.tarena.entity.Paper;
import com.tarena.entity.UserhandPaper;

public interface ExamService {
	
	/**
	 * ¼ÓÔØÊÔ¾í
	 * @param paperId
	 * @return
	 */
	public Paper loadPaper(Integer paperId) ;
	
	/**
	 * ½»¾í¡£
	 * @param userHandPaper
	 * @return
	 */
	public UserhandPaper handExamPaper(UserhandPaper userHandPaper) ;

	
	
}
