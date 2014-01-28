package net.octacomm.sample.spring;

import net.octacomm.sample.netty.client.mng.ConnectionManager;
import net.octacomm.sample.netty.client.mng.GuiEventManager;
import net.octacomm.sample.service.LoginService;

public interface SpringConfigInterface {

	ConnectionManager getConnectionManager();

	GuiEventManager getEventManager();

	LoginService getLoginService();

}