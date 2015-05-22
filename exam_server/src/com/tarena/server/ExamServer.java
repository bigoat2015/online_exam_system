package com.tarena.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import com.tarena.entity.User;
import com.tarena.net.Request;
import com.tarena.net.Response;
import com.tarena.serverUtil.ServiceTool;


public class ExamServer {

	private ServerSocket serverSocket;
	
	private ExamServerControl examServerControl;
	
	public ExamServer(ExamServerControl examServerControl)throws IOException{
		serverSocket = new ServerSocket(10000);
		this.examServerControl = examServerControl;
		startExamService();
	}
	
	private class ExamServerThread extends Thread{

		private Socket socket;
		
		private ObjectInputStream in;
		
		private ObjectOutputStream out;
		
		public ExamServerThread(Socket socket){
			this.socket = socket;
		}
		
		public void run() {
			try {
				in = new ObjectInputStream(socket.getInputStream());
				out = new ObjectOutputStream(socket.getOutputStream());
				Request req = (Request)in.readObject();

				Response res = 
					examServerControl.control(req);
			
				out.writeObject(res);
				out.flush();

				ServiceTool.close(in, out, socket);
			} catch (IOException e) {
				e.printStackTrace();
			}catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	private void startExamService(){
		System.out.println("开始服务器已启动......");
		try {
			while(true){
				Socket socket = 
					serverSocket.accept();
				new ExamServerThread(socket).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
