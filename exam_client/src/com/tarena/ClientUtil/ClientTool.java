package com.tarena.ClientUtil;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.tarena.entity.Option;
import com.tarena.entity.Question;

/**
 * �ͻ��˹����ࡣ 
 * @author xiaoyao
 *
 */

public class ClientTool {

	/**
	 * ���ַ���ת��Ϊ  List<ArrayList>��
	 * @param 
	 * str ��Ҫת���� List<ArrayList> �ַ�����
	 * @return  
	 * List<Integer> ������ 
	 */
	public static List<Integer> StringToIntegerArray(String str) throws NumberFormatException {
		
		List<Integer> answers = new ArrayList<Integer>();
		
		String[] ss = str.trim().split("/");
		for(String s:ss){
			Integer i = Integer.parseInt(s);
			answers.add(i);
		}	
		return answers;
	}
	
	/**
	 * �ر����ӡ�
	 * @param conn
	 * ��Ҫ�رյ����ӡ� 
	 */
	public static void close(Connection conn){
		try {
			if(conn != null){
				conn.close();
				conn = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * �ر�  ������ ���������socket��
	 * @param in
	 * {@link InputStream};
	 * @param os
	 * @param socket
	 */
	public static void close(InputStream in, OutputStream os,Socket socket){		
		try {
			try{
				if(in != null){
					in.close();
					in = null;
				}
				} finally {
					try{
						if(os != null){
							os.close();
							os = null;
						}
					}finally{
						if(socket != null){
							socket.close();
							socket = null;
						}
					} 
			}
				
		} catch (IOException e) {
		}
	}
	
	/**
	 * �ر� 
	 * {@link ResultSet}
	 * {@link Statement}
	 * @param rs
	 * @param stat
	 */
	public static void close(ResultSet rs, Statement stat){
		try {
			try{
				if(rs != null){
					rs.close();
					rs = null;
				}
				}finally{
					if(stat != null){
						stat.close();
						stat = null;
					}
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	
	public static void close(Connection conn, ResultSet rs, Statement stat){
		close(conn);
		close(rs,stat);
	}
	
	/**
	 * ��ʽ���ַ�����
	 * @param question
	 * ��Ҫ��ʽ�������⡣
	 * @return
	 * ��ʽ���������
	 */
	public static String formatQuestionShow(Question question){
		StringBuffer formatQuestion = new StringBuffer();
		String que_desc = question.getQue_desc();
		String formatOption = formatOption(question.getOptions());
		
		formatQuestion.append(que_desc+"\n"+"\n");	
		formatQuestion.append(formatOption);	
		
		return formatQuestion.toString();
	}

	/**
	 * ��ʽ�� ��Ŀ�� ��
	 * ���� formatQuestionShow() ���á� 
	 * @param options
	 * @return
	 */
	private static String formatOption(List<Option> options) {
		StringBuffer formatOpton = new StringBuffer(); 
		char c = 65;
		for(int i=0; i<options.size();i++){
			formatOpton.append(c+"��"+options.get(i).getOption_desc()+"\n"+"\n");
			++c;
		}	
		return formatOpton.toString();
	}

	/**
	 * ���ļ���ȡ���� ����� String ���ء�
	 * @param fileName
	 * �ļ���
	 * @return
	 * 
	 * @throws IOException
	 * �ļ�������
	 */
	public static String readFileInfo(String fileName) throws IOException{
		
		StringBuffer sb = new StringBuffer();			
		File f = new File("src/"+fileName);
		BufferedReader br = new BufferedReader(new FileReader(f)); 
			
		String tmp = null;
			while((tmp=br.readLine()) != null ){
				sb.append(tmp+"\n");
			}		
		return sb.toString();
	}
	
	/**
	 * ��ͼƬװ�δ��塣
	 * @param imgFrame
	 * @param bg
	 * @return
	 */
	public static JFrame getImgPane(JFrame imgFrame,ImageIcon bg){
		
	   JFrame imageframe = imgFrame;
	   JPanel imagePanel;
	 
	   	//�ѱ���ͼƬ��ʾ��һ����ǩ����
        JLabel label = new JLabel(bg);
      
        // �ѱ�ǩ�Ĵ�Сλ������ΪͼƬ�պ�����������
        label.setBounds(0,0,bg.getIconWidth(),bg.getIconHeight());
        
        // �����ݴ���ת��ΪJPanel���������÷���setOpaque()��ʹ���ݴ���͸��       
        imagePanel = (JPanel)imageframe.getContentPane();   
        imagePanel.setOpaque(false);     
       
        imageframe.getLayeredPane().setLayout(null);
        // �ѱ���ͼƬ��ӵ��ֲ㴰�����ײ���Ϊ����       
        imageframe.getLayeredPane().add(label,new Integer(Integer.MIN_VALUE));    
      
        imageframe.setSize(bg.getIconWidth(),bg.getIconHeight());
		return imageframe;    
	}
	
	/**
	 * �����ṩ����Ϣ ������Ϣ��
	 * @param msg
	 * ��Ϣ��
	 */
	public static void showMessage(String msg){
		
		final JFrame message = new JFrame();
		message.setLayout(null);
		message.setSize(200, 200);
		//���ô������
		message.setTitle("��ʾ��Ϣ��");
		//���ô��岻�ܷŴ�
		message.setResizable(false);
		//���ô������
		message.setLocationRelativeTo(null);
		message.setUndecorated(true);
		JButton b = new JButton("�ر�"); 
		b.setBounds(70, 170, 60, 20);
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				message.setVisible(false);
			}
		});
		message.add(b);
		JLabel l = new JLabel(msg,JLabel.CENTER);
		l.setBounds(5, 5, 140,110 );
		message.add(l);
		message.setVisible(true);
	}
}
