package com.tarena.service.impl;

import java.io.IOException;

import com.tarena.entity.User;
import com.tarena.entity.UserhandPaper;
import com.tarena.exception.ExamException;
import com.tarena.exception.RegistException;
import com.tarena.exception.UserOrPasswordException;
import com.tarena.net.Net;
import com.tarena.net.Request;
import com.tarena.net.Response;
import com.tarena.service.UserService;
/**
 * 
 * @author xiaoyao
 *
 */
public class UserServiceAgent implements UserService{
	
	private User loginUser;
	
	
	/**
	 * �û���½��
	 */
	public void login(String uId, String pwd) throws UserOrPasswordException,
			IOException {
	
		if("".equals(uId)||"".equals(pwd)){
			throw new UserOrPasswordException("��,�û����������벻��Ϊ��");
		}
		
		User user = new User();
		user.setUid(uId);
		user.setPassword(pwd);
		
		Request req = new Request();
		req.addData("user", user);
		req.setReqState(Request.LOGIN_REQ);
		
		Response res = 
			Net.remoteCall(req);
		int resState = 
			res.getResState();
		if(resState == Response.LOGIN_FAIL_RES){
			throw new UserOrPasswordException("��,�û��������������");
		}
		
		loginUser = (User)res.getData("user");
	}

	/**
	 * ���ص�½����û���
	 */
	public User getLoginUser() {
		return loginUser;
	}

	/**
	 * ������� ��������ǰ
	 * �ж��û��������Ϣ
	 * �Ƿ��Ǻϡ�
	 */
	public void regist(String uId, String name, String password,
			String rePassword, String phone, String email)
			throws RegistException {
		
		if(uId.equals("") || 
				name.equals("") || 
				password.equals("")|| 
				phone.equals("")|| 
				email.equals("")){
			throw new RegistException("<html><font color=red>��,ע�� ��Ϣ����Ϊ�գ�</font></html>");
	
		}else if(!password.equals(rePassword)){
			throw new RegistException("<html><font color=red>��,������������벻һ�£�</font></html>");
		
		}else if(password.length()<4){
			throw new RegistException("<html><font color=red>��,������ڼ����޸ģ�</font></html>");
			
		} else if(!phone.matches("\\d{3,15}")){
			throw new RegistException("<html><font color=red>��,��������ֻ���������</font></html>");
		
		} else if(!email.matches("[\\w[.-]]+@[\\w[.-]]+\\.[\\w]+")){
			throw new RegistException("<html><font color=red>��,��������������</font></html>");
		}
				
		User user = new User();
		user.setUid(uId);
		user.setName(name);
		user.setPassword(rePassword);
		user.setPhone(phone);
		user.setEmail(email);
		
		Request req = new Request();
		req.setReqState(Request.REGIST_REQ);
		req.addData("regist", user);
		
		try {
			Response res = Net.remoteCall(req);
			int resState = 
					res.getResState();
			if(resState == Response.REGIST_FALL_RES){
				throw new RegistException("<html><font color=red>�ף����û��Ѵ��ڣ�</font></html>");
			}
			
			loginUser = (User) res.getData("regist");
		} catch (IOException e) {
			throw new RegistException("��,���Ӵ���");
		} 
		
	}

	/**
	 * �����û� �Ծ�
	 */
	public UserhandPaper fintPapr(String userid) throws ExamException {
	
		UserhandPaper up = new UserhandPaper();
		up.setUserId(userid);
		
		Request req = new Request();
		req.addData("paperScore", up);
		req.setReqState(Request.FINT_USER_PAPER_REQ);
		
		Response res = null;
		try {
			res = Net.remoteCall(req);
		} catch (IOException e) {
			throw new ExamException("��,���Ӵ���");
		}
		
		int resState = 
			res.getResState();
		if(resState == Response.FINT_USER_PAPER_FALL_RES){
			throw new ExamException("��,�㻹δ ���ԣ�");
		}
		
		up = (UserhandPaper) res.getData("paperScore");

		return up;
	}
}

