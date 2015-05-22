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
 * 客户端工具类。 
 * @author xiaoyao
 *
 */

public class ClientTool {

	/**
	 * 将字符串转换为  List<ArrayList>。
	 * @param 
	 * str 需要转换成 List<ArrayList> 字符串。
	 * @return  
	 * List<Integer> 容器。 
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
	 * 关闭连接。
	 * @param conn
	 * 想要关闭的连接。 
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
	 * 关闭  输入流 ，输出流，socket。
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
	 * 关闭 
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
	 * 格式化字符串。
	 * @param question
	 * 需要格式化的问题。
	 * @return
	 * 格式化后的问题
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
	 * 格式化 题目的 答案
	 * 方法 formatQuestionShow() 调用。 
	 * @param options
	 * @return
	 */
	private static String formatOption(List<Option> options) {
		StringBuffer formatOpton = new StringBuffer(); 
		char c = 65;
		for(int i=0; i<options.size();i++){
			formatOpton.append(c+"："+options.get(i).getOption_desc()+"\n"+"\n");
			++c;
		}	
		return formatOpton.toString();
	}

	/**
	 * 从文件读取内容 打包成 String 返回。
	 * @param fileName
	 * 文件名
	 * @return
	 * 
	 * @throws IOException
	 * 文件不存在
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
	 * 用图片装饰窗体。
	 * @param imgFrame
	 * @param bg
	 * @return
	 */
	public static JFrame getImgPane(JFrame imgFrame,ImageIcon bg){
		
	   JFrame imageframe = imgFrame;
	   JPanel imagePanel;
	 
	   	//把背景图片显示在一个标签里面
        JLabel label = new JLabel(bg);
      
        // 把标签的大小位置设置为图片刚好填充整个面板
        label.setBounds(0,0,bg.getIconWidth(),bg.getIconHeight());
        
        // 把内容窗格转化为JPanel，否则不能用方法setOpaque()来使内容窗格透明       
        imagePanel = (JPanel)imageframe.getContentPane();   
        imagePanel.setOpaque(false);     
       
        imageframe.getLayeredPane().setLayout(null);
        // 把背景图片添加到分层窗格的最底层作为背景       
        imageframe.getLayeredPane().add(label,new Integer(Integer.MIN_VALUE));    
      
        imageframe.setSize(bg.getIconWidth(),bg.getIconHeight());
		return imageframe;    
	}
	
	/**
	 * 根据提供的信息 弹出消息框。
	 * @param msg
	 * 消息。
	 */
	public static void showMessage(String msg){
		
		final JFrame message = new JFrame();
		message.setLayout(null);
		message.setSize(200, 200);
		//设置窗体标题
		message.setTitle("提示消息！");
		//设置窗体不能放大
		message.setResizable(false);
		//设置窗体居中
		message.setLocationRelativeTo(null);
		message.setUndecorated(true);
		JButton b = new JButton("关闭"); 
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
