package com.tarena.ui;
/**
 * ����ʱ�䣺
 * @author alone
 *
 */
public class TestTime implements Runnable{

	private int examTime;
	
	public TestTime(ExamFrame examFrame,int examTime){
		this.examTime = examTime;
	}
	
	public void run() {
		try {
			if(examTime <= 0){
				// ʱ�䵽����
			}
			Thread.sleep(1000);
			examTime--;
		
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

}
