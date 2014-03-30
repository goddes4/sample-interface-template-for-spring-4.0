package net.octacomm.sample.netty.client.mng;

import java.util.concurrent.BlockingQueue;

public interface ReceivedLock<M> {
	
	BlockingQueue<M> getRecvLock();
}
