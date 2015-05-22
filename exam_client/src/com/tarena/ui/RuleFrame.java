package com.tarena.ui;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

/**
 * 考试规则界面
 * @author xiaoyao
 *
 */
public class RuleFrame extends RegistFrame{

	public RuleFrame(int width, int height) {
		super(width, height);
		
	}
	
	private ClientContext clientContext;
	
	public void setClientContext(ClientContext clientContext) {
		this.clientContext = clientContext;
	}

	private JTextArea examInfo;
	
	public JPanel createContentPane() {
		JPanel createContentPane = new JPanel(new BorderLayout());
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new TitledBorder("考试信息："));
		
		examInfo = new JTextArea();
		examInfo.setEditable(false);
		
		scrollPane.getViewport().add(examInfo);
		
		createContentPane.add(scrollPane,BorderLayout.CENTER);
		return createContentPane;
	}
	
	public void showExamInfo(String info){
		examInfo.setText(info);
	}

}
