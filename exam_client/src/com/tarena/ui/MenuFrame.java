package com.tarena.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import sun.misc.Cleaner;

public class MenuFrame extends DecorateFrame implements 
ActionListener{
	
	public MenuFrame(int width, int height) {
		super(width, height);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
	}
	
	private ClientContext clientContext;
	
	public void setClientContext(ClientContext clientContext) {
		this.clientContext = clientContext;
	}
	

	
	
	public JPanel createContentPane(){
		JPanel contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout());
		URL url = MenuFrame.class.getResource("title.png");
		ImageIcon icon = new ImageIcon(url);
		JLabel l = new JLabel(icon);
		contentPane.add(l,BorderLayout.NORTH);
		contentPane.add(createCenterPane(),BorderLayout.CENTER);
		JLabel l2 = new JLabel("���ڿƼ�-��Ȩ���� ����ؾ�",JLabel.RIGHT);
		contentPane.add(l2,BorderLayout.SOUTH);
		return contentPane;
	}
	
	private JLabel userInfo;
	private JPanel createCenterPane(){
		JPanel centerPane = new JPanel();
		centerPane.setLayout(new BorderLayout());
		userInfo = new JLabel("",JLabel.CENTER);
		centerPane.add(userInfo,BorderLayout.NORTH);
		centerPane.add(createBtnPane(),BorderLayout.CENTER);
		return centerPane;
	}
	
	private JButton startBtn;
	private JButton scoreBtn;
	private JButton messageBtn;
	private JButton exitBtn;
	
	private JPanel createBtnPane(){
		JPanel btnPane = new JPanel();
		btnPane.setLayout(new FlowLayout(FlowLayout.CENTER));
		startBtn = createImgBtn("��ʼ", "exam.png");
		scoreBtn = createImgBtn("����", "result.png");
		scoreBtn.setEnabled(false);
		messageBtn = createImgBtn("���Թ���", "message.png");
		exitBtn = createImgBtn("�뿪", "exit.png");
		startBtn.addActionListener(this);
		scoreBtn.addActionListener(this);
		messageBtn.addActionListener(this);
		exitBtn.addActionListener(this);
		btnPane.add(startBtn);
		btnPane.add(scoreBtn);
		btnPane.add(messageBtn);
		btnPane.add(exitBtn);
		return btnPane;
	}
	
	private JButton createImgBtn(String text,String name){
		ImageIcon icon = new ImageIcon(MenuFrame.class.getResource(name));
		JButton btn = new JButton(text, icon);
		//����ˮƽ�ı�λ��
		btn.setHorizontalTextPosition(JButton.CENTER);
		//���ô�ֱ�ı�λ��
		btn.setVerticalTextPosition(JButton.BOTTOM);
		return btn;
	}
	
	
	public void showUserInfo(String info){
		this.userInfo.setText(info);
	}
	
	public JButton getStartBtn(){
		return startBtn;
	}
	
	public JButton getScoreBtn(){
		return scoreBtn;
	}

	public void actionPerformed(ActionEvent event) {
		//����¼�Դ
		Object source = 
			event.getSource();
		if(source == startBtn){
			clientContext.start();
		
		}else if(source == scoreBtn){ 
			
			clientContext.lookScoreInfo();
		
		}else if(source == messageBtn){
			clientContext.lookExamInfo();
			
		}else if(source == exitBtn){
			System.exit(0);
		}
	}


	
}
