package net.octacomm.sample.netty.msg;

import lombok.Getter;

/**
 * 요청 메시지
 * 
 * Direction : GUI --> Server
 * 
 * @author taeyo
 *
 */
@Getter
public abstract class AbstractRequestMessage<S, M extends ResponseMessage> extends PDU implements RequestMessage, MessageProcessor<S, M> {

	public AbstractRequestMessage(MessageType messageType) {
		super(messageType);
	}
}
