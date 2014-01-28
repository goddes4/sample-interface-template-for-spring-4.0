package net.octacomm.sample.netty.msg.login;

import lombok.Getter;
import lombok.ToString;
import net.octacomm.sample.netty.msg.AbstractResponseMessage;
import net.octacomm.sample.netty.msg.MessageType;

/**
 * 로그인 결과 응답 메시지
 * 
 * Direction : Server --> GUI
 * 
 * @author tykim
 * 
 */
@Getter
@ToString
public class LoginResponseMessage extends AbstractResponseMessage {

	private LoginResult result;

	public LoginResponseMessage(LoginResult result) {
		super(MessageType.LOGIN_RESPONSE);
		this.result = result;
	}
}
