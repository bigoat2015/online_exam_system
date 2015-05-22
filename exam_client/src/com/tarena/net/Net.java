package com.tarena.net;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.tarena.ClientUtil.ClientTool;
import com.tarena.ClientUtil.SocketUtil;

/**
 * ∑√Œ Õ¯¬Á°£
 * @author xiaoyao
 *
 */
public class Net {
	
	public static Response remoteCall(Request req)throws IOException{
		Socket socket = 
			SocketUtil.getSocket();
	
		ObjectOutputStream out = 
			new ObjectOutputStream( socket.getOutputStream() );
		
		ObjectInputStream in = 
			new ObjectInputStream( socket.getInputStream() );
		
		out.writeObject(req);
		out.flush();
		Response res = null;
		try {
			res = (Response)in.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		ClientTool.close(in, out, socket);
		return res;
	}
	
}
