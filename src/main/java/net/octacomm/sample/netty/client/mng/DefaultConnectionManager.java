package net.octacomm.sample.netty.client.mng;

import io.netty.channel.ChannelHandler;
import net.octacomm.sample.netty.client.handler.GuiClientHandler;
import net.octacomm.sample.netty.msg.ResponseMessage;
import net.octacomm.sample.service.listener.MessageUpdateListener;

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
	public void addMessageUpdatelistener(MessageUpdateListener listener) {
		clientHandler.addMessageUpdateListener(listener);
	}

	@Override
	public void removeMessageUpdatelistener(MessageUpdateListener listener) {
		clientHandler.removeMessageUpdateListener(listener);
	}

}
