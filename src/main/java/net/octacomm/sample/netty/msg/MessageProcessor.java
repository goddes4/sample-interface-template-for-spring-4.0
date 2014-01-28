package net.octacomm.sample.netty.msg;


public interface MessageProcessor<S, M extends ResponseMessage> {
	M process(S service);
}
