package com.tarena.server;

import com.tarena.entity.Paper;
import com.tarena.entity.User;
import com.tarena.entity.UserhandPaper;
import com.tarena.net.Request;
import com.tarena.net.Response;
import com.tarena.service.ExamService;
import com.tarena.service.UserService;

public class ExamServerControl {

	private UserService userService;
	
	private ExamService examService;
	
	public void setExamService(ExamService examService) {
		this.examService = examService;
	}
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	public Response control(Request req) {

		Response res = new Response();

		int reqState = req.getReqState();
	
		if (reqState == Request.LOGIN_REQ) {
			// TODO 完成登录
			User user = (User) req.getData("user");
			User u = userService.login(user);
			if (u == null) {
				res.setResState(Response.LOGIN_FAIL_RES);
				return res;
			}
			res.setResState(Response.LOGIN_SUCCESS_RES);
			res.addData("user", u);

		} else if (reqState == Request.REGIST_REQ) {
			// TODO 完成注册
			User user = (User) req.getData("regist");
			User u = userService.regist(user);
			if(u == null){
				res.setResState(Response.REGIST_FALL_RES);
				return res;
			}
			res.setResState(Response.REGIST_SUCCESS_RES);
			res.addData("regist", u);

		} else if (reqState == Request.LOAD_PAPER_REQ) {
			// TODO 提取试卷
			Paper paper = (Paper) req.getData("loadPaper");
			Paper p = examService.loadPaper(paper.getId());
			if (p == null) {
				res.setResState(Response.LOAD_LOGIN_FAIL_RES);
				return res;
			}
			res.setResState(Response.LOAD_PAPER_SUCCESS_RES);
			res.addData("loadPaper", p);

		} else if (reqState == Request.WRITE_USER_PAPER_REQ){
			// TODO 保存答卷信息
			UserhandPaper userHandPaper = (UserhandPaper) req.getData("handExamPaper");
			UserhandPaper uhp = examService.handExamPaper(userHandPaper);
			if(uhp == null){
				res.setResState(Response.WRITE_USER_PAPER_FALL_RES);
				return res;
			}
			res.setResState(Response.WRITE_USER_PAPER_SUCCESS_RES);
			res.addData("handExamPaper", uhp);
		
		} else if (reqState == Request.FINT_USER_PAPER_REQ){
			// 查找试卷：
			UserhandPaper u = (UserhandPaper) req.getData("paperScore");
			UserhandPaper uhp = userService.findScore(u.getUserId());
			if(uhp == null){
				res.setResState(Response.FINT_USER_PAPER_FALL_RES);
				return res;
			}
			res.setResState(Response.FINT_USER_PAPER_SUCCESS_RES);
			res.addData("paperScore", uhp);
		}
			
		return res;
	}

}
