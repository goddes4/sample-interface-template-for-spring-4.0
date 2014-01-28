package net.octacomm.sample.netty.msg;

import lombok.Getter;

/**
 * 응답 메시지
 * 
 * Direction : Server --> GUI
 * 
 * @author taeyo
 *
 */
@Getter
public abstract class AbstractResponseMessage extends PDU implements ResponseMessage {

	public AbstractResponseMessage(MessageType messageType) {
		super(messageType);
	}
}
