package net.octacomm.sample.spring;

import net.octacomm.sample.netty.client.mng.ConnectionManager;
import net.octacomm.sample.netty.client.mng.DefaultConnectionManager;
import net.octacomm.sample.netty.client.mng.GuiEventManager;
import net.octacomm.sample.service.LoginService;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringAccessor implements SpringConfigInterface {
	private static final SpringAccessor accessor = new SpringAccessor();
	private ApplicationContext context;
	
	public static SpringConfigInterface getInstance() {
		return accessor;
	}
	
	private SpringAccessor() {
		context = new AnnotationConfigApplicationContext(SpringConfig.class);
	}
	
	@Override
	public ConnectionManager getConnectionManager() {
		return context.getBean(DefaultConnectionManager.class);
	}
	
	@Override
	public GuiEventManager getEventManager() {
		return context.getBean(DefaultConnectionManager.class);
	}

	@Override
	public LoginService getLoginService() {
		return context.getBean(LoginService.class);
	}

}
