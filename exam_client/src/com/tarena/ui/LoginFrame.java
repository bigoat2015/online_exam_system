package com.tarena.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * 登录界面
 * @author zhengyin
 *
 */
public class LoginFrame extends JFrame {

	private int width;//描述窗体宽
	
	private int height;//描述窗体高
	
//	public static void main(String[] args) {
//		LoginFrame loginFrame = 
//			new LoginFrame(350,200);
//	}
	
	public LoginFrame(int width,int height){
		this.width = width;
		this.height = height;
		this.init();
	}
	
	
	/**
	 * 完成界面的初始化
	 */
	private void init(){
		//设置窗体尺寸
		this.setSize(width, height);
		//设置默认的关闭操作
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//设置窗体标题
		this.setTitle("在线考试系统");
		//设置窗体不能放大
		this.setResizable(false);
		//设置窗体居中
		this.setLocationRelativeTo(null);
		this.setContentPane(createContentPane());
		// 无边框。
		setUndecorated(true);
		this.showView();
	}
	
	/**
	 * 
	 * @return contentPane 内容面板
	 */
	private JPanel createContentPane(){
		JPanel contentPane = new JPanel();
		contentPane.setOpaque(false);
		//被contentPane设置布局
		contentPane.setLayout(new BorderLayout());
		JLabel l = new JLabel("<html><font size=8 color=blue>登录界面</font></html>",JLabel.CENTER);
		contentPane.add(l,BorderLayout.NORTH);
		contentPane.add(createCenterPane(),BorderLayout.CENTER);
		contentPane.add(createToolsPane(),BorderLayout.SOUTH);
		return contentPane;
	}
	
	private JLabel error;
	/**
	 * 
	 * @return centerPane 中部面板
	 */
	private JPanel createCenterPane(){
		JPanel centerPane = new JPanel();
		centerPane.setOpaque(false);
		centerPane.setLayout(new BorderLayout());
		centerPane.add(createInputPane(),BorderLayout.NORTH);
		error = new JLabel("",JLabel.CENTER);
		centerPane.add(error,BorderLayout.CENTER);
		return centerPane;
	}
	
	/**
	 * 显示错误提示
	 * @param 错误消息
	 */
	public void showError(String msg){
		error.setText(msg);
	}
		
	private JPanel createInputPane(){
		JPanel inputPane = new JPanel();
		inputPane.setOpaque(false);
		inputPane.setLayout(new GridLayout(2, 1));
		inputPane.add(createUserPane());
		inputPane.add(createPasswordPane());
		return inputPane;
	}
	
	private JPasswordField passwordField;
	
	private JPanel createPasswordPane(){
		JPanel passwordPane = new JPanel();
		passwordPane.setOpaque(false);
		passwordPane.setLayout(new FlowLayout());
		JLabel l = new JLabel("密码");
		passwordPane.add(l);
		passwordField = new JPasswordField(15);
		passwordPane.add(passwordField);
		return passwordPane;
	}
	
	private JTextField userField;
	
	private JPanel createUserPane(){
		JPanel userPane = new JPanel();
		userPane.setOpaque(false);
		userPane.setLayout(new FlowLayout());
		JLabel l = new JLabel("用户");
		userPane.add(l);
		userField = new JTextField(15);
		userPane.add(userField);
		return userPane;
	}
	
	private JButton loginBtn;
	private JButton registBtn;
	/**
	 * 
	 * @return 按钮面板
	 */
	private JPanel createToolsPane(){
		JPanel toolsPane = new JPanel();
		toolsPane.setOpaque(false);
		//FlowLayout流式布局
		toolsPane.setLayout(new FlowLayout(FlowLayout.CENTER));
		loginBtn = new JButton("登录");
		loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clientContext.login();
			}
		});
		
		registBtn = new JButton("注册");
		registBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clientContext.regist();
			}
		});
		JButton exitBtn = new JButton("退出");
		exitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		toolsPane.add(loginBtn);
		toolsPane.add(registBtn);
		toolsPane.add(exitBtn);
		return toolsPane;
	}
	
	/**
	 * 显示窗体
	 */
	public void showView(){
		this.setVisible(true);
	}
	
	/**
	 * 隐藏窗体
	 */
	public void hideView(){
		this.setVisible(false);
	}

	/**
	 * 
	 * @return 返回输入的用户id
	 */
	public String getUser(){
		//trim()去掉首尾空格
		return userField.getText().trim();
	}
	
	/**
	 * 
	 * @return 返回输入的用户密码
	 */
	public String getPassword(){
		return passwordField.getText();
	}
	
	private ClientContext clientContext;
	
	public void setClientContext(ClientContext clientContext) {
		this.clientContext = clientContext;
	}

	public JPasswordField getPasswordField() {
		return passwordField;
	}


	public JTextField getUserField() {
		return userField;
	}	
	
}
