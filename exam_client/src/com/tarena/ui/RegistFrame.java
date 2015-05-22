package com.tarena.ui;

import java.awt.BorderLayout;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class RegistFrame extends DecorateFrame{

	private ClientContext clientContext;
	
	public RegistFrame(int width, int height) {
		super(width, height);

		
	}
	
	public void setClientContext(ClientContext clientContext) {
		this.clientContext = clientContext;
	}
	
	
	public JPanel createContentPane() {
		JPanel createPane = new JPanel(new BorderLayout());
		
		JLabel l = new JLabel("<html><font size=10>ÓÃ»§×¢²á</font></html>",JLabel.CENTER);
		createPane.add(l,BorderLayout.NORTH);
		
		createPane.add(inputPane(),BorderLayout.CENTER);
		createPane.add(southPane(),BorderLayout.SOUTH);
		
		return createPane;
	}
	
	
	private JButton regist;
	private JButton rest;
	private JLabel error;
	
	private JPanel southPane() {
		JPanel southPane = new JPanel(new BorderLayout());
		
		error = new JLabel("",JLabel.CENTER);
		southPane.add(error,BorderLayout.NORTH);
		
		JPanel toolPane = new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		regist = new JButton("×¢²á");
		regist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clientContext.userRegist();
			}
		});
		
		rest = new JButton("ÖØÐÂÌîÐ´");
		rest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clientContext.rest();
			}
		});
		
		toolPane.add(regist);
		toolPane.add(rest);
		
		southPane.add(toolPane, BorderLayout.CENTER);
		
		return southPane;
	}


	private JTextField uid;
	private JTextField userName;
	private JTextField phone;
	private JTextField email;
	private JPasswordField password;
	private JPasswordField rePassword;
	
	
	private JPanel inputPane() {
		JPanel inJPanel = new JPanel(new FlowLayout(FlowLayout.LEFT,5,15));
		
		JLabel uidL = new JLabel("ÕË         ºÅ£º");
		uid = new JTextField(15);
		
		JLabel passwordL = new JLabel("ÃÜ         Âë£º");
		password = new JPasswordField(15);
		
		JLabel rePasswordL = new JLabel("È·¶¨ÃÜÂë£º");
		rePassword = new JPasswordField(15);
		 
		JLabel nameL = new JLabel("ÐÕ         Ãû£º");
		userName = new JTextField(15);
		
		JLabel phoneL = new JLabel("ÊÖ»úºÅÂë£º");
		phone = new JTextField(15);
		
		JLabel emailL = new JLabel("ÓÊ         Ïä£º");
		email = new JTextField(15);
		
		
		inJPanel.add(uidL);
		inJPanel.add(uid);
		inJPanel.add(passwordL);
		inJPanel.add(password);
		inJPanel.add(rePasswordL);
		inJPanel.add(rePassword);
		inJPanel.add(nameL);
		inJPanel.add(userName);
		inJPanel.add(phoneL);
		inJPanel.add(phone);
		inJPanel.add(emailL);
		inJPanel.add(email);
		
		
		return inJPanel;
	}

	public JButton getRegist() {
		return regist;
	}


	public void setRegist(JButton regist) {
		this.regist = regist;
	}


	public JButton getRest() {
		return rest;
	}


	public void setRest(JButton rest) {
		this.rest = rest;
	}


	public JTextField getUid() {
		return uid;
	}


	public void setUid(JTextField uid) {
		this.uid = uid;
	}


	public JTextField getUserName() {
		return userName;
	}


	public void setUserName(JTextField name) {
		this.userName = name;
	}


	public JTextField getPhone() {
		return phone;
	}


	public void setPhone(JTextField phone) {
		this.phone = phone;
	}


	public JTextField getEmail() {
		return email;
	}


	public void setEmail(JTextField email) {
		this.email = email;
	}


	public JPasswordField getPassword() {
		return password;
	}


	public void setPassword(JPasswordField password) {
		this.password = password;
	}


	public JPasswordField getRePassword() {
		return rePassword;
	}


	public void setRePassword(JPasswordField rePassword) {
		this.rePassword = rePassword;
	}


	public JLabel getError() {
		return error;
	}


	public void showError(String error) {
		this.error.setText(error);
	}

}
