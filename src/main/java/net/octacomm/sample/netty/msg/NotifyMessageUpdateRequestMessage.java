package net.octacomm.sample.netty.msg;

import lombok.Getter;
import lombok.ToString;

/**
 * 예비군 정보 업데이트 
 * 
 * Direction : Server --> GUI
 * 
 * @author taeyo
 *
 */
@Getter
@ToString
public class NotifyMessageUpdateRequestMessage extends PDU implements RequestMessage {

	private String message;
	
	public NotifyMessageUpdateRequestMessage(String message) {
		super(MessageType.NOTIFY_MESSAGE_UPDATE_REQUEST);
		this.message = message;
	}
}
