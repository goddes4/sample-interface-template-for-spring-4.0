package net.octacomm.sample.netty.msg;

import lombok.Getter;
import lombok.ToString;
import net.octacomm.sample.service.listener.Message;

/**
 * {@link Message} 통지 메시지
 * 
 * Direction : Server --> GUI
 * 
 * @author taeyo
 *
 */
@Getter
@ToString
public class NotifyMessageRequestMessage extends PDU implements RequestMessage {

	private Message message;
	
	public NotifyMessageRequestMessage(Message message) {
		super(MessageType.NOTIFY_MESSAGE_REQUEST);
		this.message = message;
	}
}
