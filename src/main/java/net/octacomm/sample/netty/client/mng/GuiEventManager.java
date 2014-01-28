package net.octacomm.sample.netty.client.mng;

import net.octacomm.sample.service.listener.MessageUpdateListener;

public interface GuiEventManager {

	void addMessageUpdatelistener(MessageUpdateListener listener);

	void removeMessageUpdatelistener(MessageUpdateListener listener);

}
