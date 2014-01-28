/**
 * 
 */
package net.octacomm.sample.netty;

import net.octacomm.sample.netty.client.mng.ConnectionManager;
import net.octacomm.sample.netty.msg.ResponseMessage;
import net.octacomm.sample.netty.msg.login.LoginRequestMessage;
import net.octacomm.sample.netty.msg.login.LoginResponseMessage;
import net.octacomm.sample.netty.msg.login.LoginResult;
import net.octacomm.sample.service.LoginService;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author taeyo
 *
 */
public class NettyLoginService implements LoginService {

	@Autowired
	private ConnectionManager connectionManager;

	@Override
	public LoginResult login(String id, String password) {
		ResponseMessage response = connectionManager.sendMessage(new LoginRequestMessage(id, password));
		return ((LoginResponseMessage) response).getResult();
	}

	@Override
	public void logout(String userId) {
		
	}

}
