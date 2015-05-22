package com.tarena.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Message extends JFrame{


	private int width = 100;
	private int height = 100;
	private String msg = "";
	
	private Message(){
		
	}
	

	public static void showMessage(String msg){
		final JFrame message = new JFrame();
		//设置窗体标题
		message.setTitle("提示消息！");
		//设置窗体不能放大
		message.setResizable(false);
		//设置窗体居中
		message.setLocationRelativeTo(null);
		message.setUndecorated(true);
		JButton b = new JButton("关闭"); 
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				message.setVisible(false);
			}
		});
		message.add(b,BorderLayout.SOUTH);
		JLabel l = new JLabel(msg);
		message.add(l,BorderLayout.CENTER);
		message.setVisible(true);

	}
	
	public void showMessage(int w, int h, String message){
	
	}
	
	public static void main(String[] args) {
		
	}
	
	
	
	
}
	
