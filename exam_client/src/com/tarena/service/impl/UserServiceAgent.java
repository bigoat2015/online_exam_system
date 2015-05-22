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
	 * 用户登陆。
	 */
	public void login(String uId, String pwd) throws UserOrPasswordException,
			IOException {
	
		if("".equals(uId)||"".equals(pwd)){
			throw new UserOrPasswordException("亲,用户名或者密码不能为空");
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
			throw new UserOrPasswordException("亲,用户名或者密码错误");
		}
		
		loginUser = (User)res.getData("user");
	}

	/**
	 * 返回登陆后的用户。
	 */
	public User getLoginUser() {
		return loginUser;
	}

	/**
	 * 向服务器 发送请求前
	 * 判断用户输入的信息
	 * 是否吻合。
	 */
	public void regist(String uId, String name, String password,
			String rePassword, String phone, String email)
			throws RegistException {
		
		if(uId.equals("") || 
				name.equals("") || 
				password.equals("")|| 
				phone.equals("")|| 
				email.equals("")){
			throw new RegistException("<html><font color=red>亲,注册 信息不能为空！</font></html>");
	
		}else if(!password.equals(rePassword)){
			throw new RegistException("<html><font color=red>亲,两次输入的密码不一致！</font></html>");
		
		}else if(password.length()<4){
			throw new RegistException("<html><font color=red>亲,密码过于简单请修改！</font></html>");
			
		} else if(!phone.matches("\\d{3,15}")){
			throw new RegistException("<html><font color=red>亲,您输入的手机号码有误！</font></html>");
		
		} else if(!email.matches("[\\w[.-]]+@[\\w[.-]]+\\.[\\w]+")){
			throw new RegistException("<html><font color=red>亲,您输入邮箱有误！</font></html>");
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
				throw new RegistException("<html><font color=red>亲，该用户已存在！</font></html>");
			}
			
			loginUser = (User) res.getData("regist");
		} catch (IOException e) {
			throw new RegistException("亲,连接错误！");
		} 
		
	}

	/**
	 * 查找用户 试卷。
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
			throw new ExamException("亲,连接错误！");
		}
		
		int resState = 
			res.getResState();
		if(resState == Response.FINT_USER_PAPER_FALL_RES){
			throw new ExamException("亲,你还未 考试！");
		}
		
		up = (UserhandPaper) res.getData("paperScore");

		return up;
	}
}

