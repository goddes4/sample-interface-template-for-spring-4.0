package net.octacomm.sample.netty.client.exception;

public class ConnectionFailureException extends RuntimeException {

	public ConnectionFailureException() {
		super();
	}
	
	public ConnectionFailureException(Throwable cause) {
		super(cause);
	}
	
}
