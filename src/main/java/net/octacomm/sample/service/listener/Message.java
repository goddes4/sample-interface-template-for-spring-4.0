package net.octacomm.sample.service.listener;

import java.io.Serializable;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Message implements Serializable {

	public static Message DUMMY_MSG = new Message(Type.DUMMY_TYPE);
	
	public enum Type {
		DUMMY_TYPE
	}
	
	private Type type;
	
	private Object content;

	private Message(Type type) {
		this(type, null);
	}

	private Message(Type type, Object content) {
		this.type = type;
		this.content = content;
	}
	
}
