package com.tarena.ClientUtil;

import java.io.IOException;
import java.net.Socket;
import java.util.Properties;

/**
 *	从指定的文件读取
 *	连接服务器的 IP 和 PORT。
 * @author xiaoyao
 *
 */
public class SocketUtil {
	
	
	private static Properties props;
	
	//静态代码块
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
