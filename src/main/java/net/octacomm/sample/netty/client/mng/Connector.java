package net.octacomm.sample.netty.client.mng;

import net.octacomm.sample.netty.client.exception.ConnectionFailureException;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

public interface Connector {
	
	Channel connect(String address, int port, ChannelInitializer<SocketChannel> channelInitializer) throws ConnectionFailureException;
}