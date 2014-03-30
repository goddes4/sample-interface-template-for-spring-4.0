package net.octacomm.sample.netty.client.mng;

import net.octacomm.sample.netty.msg.RequestMessage;
import net.octacomm.sample.netty.msg.ResponseMessage;

public interface ConnectionManager {
	
	boolean connect(String address, int port);
	
	void disconnect();
	
	ResponseMessage sendMessage(RequestMessage message);
	
	boolean isConnected();
	
	void addConnectionListener(ConnectionListener listener);

	boolean removeConnectionListener(ConnectionListener listener);
	
	void removeAllConnectionListeners();
	
}