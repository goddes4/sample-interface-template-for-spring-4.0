package net.octacomm.sample.spring;

import net.octacomm.sample.netty.NettyLoginService;
import net.octacomm.sample.netty.client.mng.ConnectionManager;
import net.octacomm.sample.netty.client.mng.DefaultConnectionManager;
import net.octacomm.sample.service.LoginService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
	
	@Bean
	public ConnectionManager connectionManager() {
		return new DefaultConnectionManager();
	}

	@Bean
	public LoginService loginService() {
		return new NettyLoginService();
	}

}
