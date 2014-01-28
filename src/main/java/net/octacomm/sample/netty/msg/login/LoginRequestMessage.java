package net.octacomm.sample.netty.msg.login;

import lombok.Getter;
import lombok.ToString;
import net.octacomm.sample.netty.msg.AbstractRequestMessage;
import net.octacomm.sample.netty.msg.MessageType;
import net.octacomm.sample.service.LoginService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 로그인 요청 메시지 
 * 
 * Direction : GUI --> Server
 * 
 * @author taeyo
 *
 */
@Getter
@ToString()
public class LoginRequestMessage extends AbstractRequestMessage<LoginService, LoginResponseMessage> {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private String id;
	private String password;
	
	public LoginRequestMessage(String id, String password) {
		super(MessageType.LOGIN_REQUEST);
		this.id = id;
		this.password = password;
	}

	@Override
	public LoginResponseMessage process(LoginService service) {
		LoginResult loginResult = service.login(id, password);

		logger.info("Login Result : {}", loginResult);
		
		return new LoginResponseMessage(loginResult);
	}

}
