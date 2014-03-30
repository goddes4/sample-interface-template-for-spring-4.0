package net.octacomm.sample.netty.client.handler;


import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

import lombok.Getter;
import net.octacomm.sample.netty.client.mng.ReceivedLock;
import net.octacomm.sample.netty.msg.NotifyMessageRequestMessage;
import net.octacomm.sample.netty.msg.PDU;
import net.octacomm.sample.netty.msg.ResponseMessage;
import net.octacomm.sample.service.listener.MessageListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * GuiClientHandler
 * 
 * @author tykim
 * 
 */
@Sharable
public class GuiClientHandler extends SimpleChannelInboundHandler<PDU> implements ReceivedLock<ResponseMessage> {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	// 요청 메시지에 대한 응답을 기다리는 blockingQueue	
	@Getter
	private BlockingQueue<ResponseMessage> recvLock = new SynchronousQueue<ResponseMessage>();
	private	List<MessageListener> listeners = new ArrayList<MessageListener>();
	
	public void addMessageListener(MessageListener listener) {
		if (!listeners.contains(listener)) {
			listeners.add(listener);
		}
	}
	
	public void removeMessageListener(MessageListener listener) {
		listeners.remove(listener);
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, PDU pdu) throws Exception {
		logger.debug("MessageListener : {}\n{}", listeners, pdu);

		if (pdu instanceof ResponseMessage) {
			if (recvLock != null) {
				recvLock.offer((ResponseMessage) pdu);
			}
		} else {
			NotifyMessageRequestMessage req = (NotifyMessageRequestMessage) pdu;
			for (MessageListener listener : listeners) {
				listener.messageReceived(req.getMessage());
			}
		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		logger.error("{}", cause);
	}
}
