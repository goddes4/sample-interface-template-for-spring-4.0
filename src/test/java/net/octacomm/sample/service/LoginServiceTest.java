package net.octacomm.sample.service;

import net.octacomm.sample.netty.client.mng.ConnectionManager;
import net.octacomm.sample.service.listener.Message;
import net.octacomm.sample.service.listener.MessageListener;
import net.octacomm.sample.spring.SpringConfig;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringConfig.class})
public class LoginServiceTest implements MessageListener {
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private ConnectionManager connectionManager;
	
	@Before
	public void before() {
		connectionManager.connect("localhost", 9000);
	}
	
	@After
	public void after() {
		connectionManager.disconnect();
	}
	
	@Test
	public void loginService() {
		loginService.login("admin", "1234");
	}

	@Override
	public void messageReceived(Message message) {
		System.err.println(message);
	}

}
