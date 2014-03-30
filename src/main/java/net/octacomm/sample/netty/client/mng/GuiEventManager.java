package net.octacomm.sample.netty.client.mng;

import net.octacomm.sample.service.listener.MessageListener;

public interface GuiEventManager {

	void addMessageListener(MessageListener listener);

	void removeMessageListener(MessageListener listener);
}
