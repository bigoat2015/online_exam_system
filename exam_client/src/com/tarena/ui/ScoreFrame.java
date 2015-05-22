package com.tarena.ui;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

/**
 * 分数界面。
 * @author xiaoyao
 *
 */
public class ScoreFrame extends DecorateFrame{

	
	public ScoreFrame(int width, int height) {
		super(width, height);
	}
	
	
	public JPanel createContentPane() {
		JPanel createContentPane = new JPanel(new BorderLayout());
		
		JLabel scoreInfo = new JLabel("<html><font color=red size=10>分   数</font></html>",JLabel.CENTER);
		createContentPane.add(scoreInfo,BorderLayout.NORTH);
		createContentPane.add(scoreContentPane(),BorderLayout.CENTER);
		
		return createContentPane;
	}

	
	private  JTextArea scoreInfo;
	private ClientContext clientContext;
	
	private JScrollPane scoreContentPane() {
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new TitledBorder("查看分数："));
		
		scoreInfo = new JTextArea();
		scoreInfo.setEditable(false);
		
		scrollPane.getViewport().add(scoreInfo);
		return scrollPane;
	}

	public void showScoreInfo(String info){
		this.scoreInfo.setText(info); 
	}
	
	public void setClientContext(ClientContext clientContext) {
		this.clientContext = clientContext;
	}
	
	
	

}
