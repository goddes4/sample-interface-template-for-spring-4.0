package net.octacomm.sample.service;

import net.octacomm.sample.service.listener.Message;
import net.octacomm.sample.service.listener.MessageListener;
import net.octacomm.sample.spring.SpringAccessor;
import net.octacomm.sample.spring.SpringConfigInterface;

public class SpringLauncherTest {
	public static void main(String[] args) {
		SpringConfigInterface spring = SpringAccessor.getInstance();
		try {
			spring.getConnectionManager().connect("localhost", 9000);
			spring.getEventManager().addMessageListener(new MessageListener() {
				
				@Override
				public void messageReceived(Message message) {
					System.out.println(message);
				}
			});
			System.out.println("Result : " + spring.getLoginService().login("admin", "1234"));
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
