package net.octacomm.sample.netty.client.mng;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

import net.octacomm.sample.netty.client.exception.ConnectionFailureException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Taeyoung, Kim
 */
public class SocketConnector implements Connector {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	
	@Override
	public Channel connect(String address, int port, ChannelInitializer<SocketChannel> channelInitializer) throws ConnectionFailureException {
		EventLoopGroup group = new NioEventLoopGroup();
		Bootstrap b = new Bootstrap();
		b.group(group)
			.channel(NioSocketChannel.class)
			.option(ChannelOption.TCP_NODELAY, true)
			.option(ChannelOption.SO_KEEPALIVE, true)
			.handler(channelInitializer);
       
        // Make a new connection.
        ChannelFuture future = b.connect(new InetSocketAddress(address, port));
        future.awaitUninterruptibly();
        if (!future.isSuccess()) {
        	logger.error("connect fail : {}", future.cause().getMessage());
        	group.shutdownGracefully();
        	throw new ConnectionFailureException(future.cause());
        }
        
        return future.channel();
	}

}