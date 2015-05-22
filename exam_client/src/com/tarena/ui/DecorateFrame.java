package com.tarena.ui;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * 	所有窗体的父类
 * 	除了 登陆窗体。 
 * @author xiaoyao
 *
 */
public class DecorateFrame extends JFrame{

	private int width;//描述窗体宽
	
	private int height;//描述窗体高
	

	private ClientContext clientContext;
	
	public DecorateFrame(int width,int height){
		this.width = width;
		this.height = height;
		this.init();
	}
	
	public void setClientContext(ClientContext clientContext) {
		this.clientContext = clientContext;
	}
	
	
	private void init(){
		//设置窗体尺寸
		this.setSize(width, height);
		//设置窗体标题
		this.setTitle("在线考试系统");
		//设置窗体不能放大
		this.setResizable(false);
		//设置窗体居中
		this.setLocationRelativeTo(null);
		this.setContentPane(createContentPane());
//		this.showView();
	}
	
	
	public JPanel createContentPane() {
		JPanel createPane = new JPanel();
		
		return createPane;
	}

	public void showView(){
		this.setVisible(true);
	}
	
	/**
	 * 隐藏窗体
	 */
	public void hideView(){
		this.setVisible(false);
		
	}
	

	
	
	
	
	
	
	
    
   

    
   

} 

