package net.octacomm.sample.netty.msg;

import lombok.Getter;

public class ExceptionResponseMessage extends AbstractResponseMessage {

	@Getter
	private RuntimeException exception;
	
	public ExceptionResponseMessage(RuntimeException ex) {
		super(MessageType.EXCEPTION_RESPONSE);
		this.exception = ex;
	}
}
