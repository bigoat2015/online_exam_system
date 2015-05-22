package com.tarena.ui;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * 	���д���ĸ���
 * 	���� ��½���塣 
 * @author xiaoyao
 *
 */
public class DecorateFrame extends JFrame{

	private int width;//���������
	
	private int height;//���������
	

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
		//���ô���ߴ�
		this.setSize(width, height);
		//���ô������
		this.setTitle("���߿���ϵͳ");
		//���ô��岻�ܷŴ�
		this.setResizable(false);
		//���ô������
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
	 * ���ش���
	 */
	public void hideView(){
		this.setVisible(false);
		
	}
	

	
	
	
	
	
	
	
    
   

    
   

} 

