package net.octacomm.sample.netty.client.handler;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

import lombok.Getter;
import net.octacomm.sample.netty.client.mng.ReceivedLock;
import net.octacomm.sample.netty.msg.NotifyMessageUpdateRequestMessage;
import net.octacomm.sample.netty.msg.PDU;
import net.octacomm.sample.netty.msg.ResponseMessage;
import net.octacomm.sample.service.listener.MessageUpdateListener;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * GuiClientHandler
 * 
 * @author tykim
 * 
 */
public class GuiClientHandler extends SimpleChannelHandler implements ReceivedLock<ResponseMessage> {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	// 요청 메시지에 대한 응답을 기다리는 blockingQueue	
	@Getter
	private BlockingQueue<ResponseMessage> recvLock = new SynchronousQueue<ResponseMessage>();
	private	List<MessageUpdateListener> listeners = new ArrayList<MessageUpdateListener>();
	
	public void addMessageUpdateListener(MessageUpdateListener listener) {
		if (!listeners.contains(listener)) {
			listeners.add(listener);
		}
	}
	
	public void removeMessageUpdateListener(MessageUpdateListener listener) {
		listeners.remove(listener);
	}
	
	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) {

		logger.debug("MessageUpdateListener : {}", listeners);
		PDU pdu = (PDU) e.getMessage();

		if (pdu instanceof ResponseMessage) {
			if (recvLock != null) {
				recvLock.offer((ResponseMessage) pdu);
			}
		} else {
			NotifyMessageUpdateRequestMessage req = (NotifyMessageUpdateRequestMessage) pdu;
			for (MessageUpdateListener listener : listeners) {
				listener.updateReservist(req.getMessage());
			}
		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) {
		logger.error("{}", e.getCause());
	}
}
