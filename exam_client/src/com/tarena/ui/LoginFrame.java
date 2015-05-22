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
 * ��¼����
 * @author zhengyin
 *
 */
public class LoginFrame extends JFrame {

	private int width;//���������
	
	private int height;//���������
	
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
	 * ��ɽ���ĳ�ʼ��
	 */
	private void init(){
		//���ô���ߴ�
		this.setSize(width, height);
		//����Ĭ�ϵĹرղ���
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//���ô������
		this.setTitle("���߿���ϵͳ");
		//���ô��岻�ܷŴ�
		this.setResizable(false);
		//���ô������
		this.setLocationRelativeTo(null);
		this.setContentPane(createContentPane());
		// �ޱ߿�
		setUndecorated(true);
		this.showView();
	}
	
	/**
	 * 
	 * @return contentPane �������
	 */
	private JPanel createContentPane(){
		JPanel contentPane = new JPanel();
		contentPane.setOpaque(false);
		//��contentPane���ò���
		contentPane.setLayout(new BorderLayout());
		JLabel l = new JLabel("<html><font size=8 color=blue>��¼����</font></html>",JLabel.CENTER);
		contentPane.add(l,BorderLayout.NORTH);
		contentPane.add(createCenterPane(),BorderLayout.CENTER);
		contentPane.add(createToolsPane(),BorderLayout.SOUTH);
		return contentPane;
	}
	
	private JLabel error;
	/**
	 * 
	 * @return centerPane �в����
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
	 * ��ʾ������ʾ
	 * @param ������Ϣ
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
		JLabel l = new JLabel("����");
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
		JLabel l = new JLabel("�û�");
		userPane.add(l);
		userField = new JTextField(15);
		userPane.add(userField);
		return userPane;
	}
	
	private JButton loginBtn;
	private JButton registBtn;
	/**
	 * 
	 * @return ��ť���
	 */
	private JPanel createToolsPane(){
		JPanel toolsPane = new JPanel();
		toolsPane.setOpaque(false);
		//FlowLayout��ʽ����
		toolsPane.setLayout(new FlowLayout(FlowLayout.CENTER));
		loginBtn = new JButton("��¼");
		loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clientContext.login();
			}
		});
		
		registBtn = new JButton("ע��");
		registBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clientContext.regist();
			}
		});
		JButton exitBtn = new JButton("�˳�");
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
	 * ��ʾ����
	 */
	public void showView(){
		this.setVisible(true);
	}
	
	/**
	 * ���ش���
	 */
	public void hideView(){
		this.setVisible(false);
	}

	/**
	 * 
	 * @return ����������û�id
	 */
	public String getUser(){
		//trim()ȥ����β�ո�
		return userField.getText().trim();
	}
	
	/**
	 * 
	 * @return ����������û�����
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
