package com.tarena.ClientUtil;

import java.io.IOException;
import java.net.Socket;
import java.util.Properties;

/**
 *	��ָ�����ļ���ȡ
 *	���ӷ������� IP �� PORT��
 * @author xiaoyao
 *
 */
public class SocketUtil {
	
	
	private static Properties props;
	
	//��̬�����
	static{
		props = new Properties();
		try {
			props.load(
					SocketUtil.class.
						getClassLoader().
							getResourceAsStream("conn.properties")
							);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public static Socket getSocket()throws IOException{
		String ip = props.getProperty("server_ip");
		Integer port = Integer.parseInt( props.getProperty("server_port") );
		Socket socket = new Socket(ip,port);
		return socket;
	}
}
