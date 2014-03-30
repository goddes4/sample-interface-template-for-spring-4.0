package net.octacomm.sample.netty.client.mng;

import io.netty.channel.ChannelHandler;
import net.octacomm.sample.netty.client.handler.GuiClientHandler;
import net.octacomm.sample.netty.msg.ResponseMessage;
import net.octacomm.sample.service.listener.MessageListener;

public class DefaultConnectionManager extends AbstractConnectionManager implements GuiEventManager {

	private GuiClientHandler clientHandler = new GuiClientHandler();
	
	@Override
	protected ChannelHandler clientHandler() {
		return clientHandler;
	}

	@Override
	protected ReceivedLock<ResponseMessage> lock() {
		return clientHandler;
	}

	@Override
	public void addMessageListener(MessageListener listener) {
		clientHandler.addMessageListener(listener);
	}

	@Override
	public void removeMessageListener(MessageListener listener) {
		clientHandler.removeMessageListener(listener);
	}

}
