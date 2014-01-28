package net.octacomm.sample.netty.client.mng;

public interface ConnectionListener {
	void stateChanged(boolean isConnected);
}
