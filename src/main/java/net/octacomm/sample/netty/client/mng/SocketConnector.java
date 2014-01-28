package net.octacomm.sample.netty.client.mng;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import net.octacomm.sample.netty.client.exception.ConnectionFailureException;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Taeyoung, Kim
 */
public class SocketConnector implements Connector {
	
	private ClientBootstrap bootstrap = new ClientBootstrap(new NioClientSocketChannelFactory(
									    		Executors.newCachedThreadPool(), 
									    		Executors.newCachedThreadPool()));
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
    public Channel connect(String address, int port, ChannelPipelineFactory factory) throws ConnectionFailureException
    {
        // Configure the event pipeline factory.
        bootstrap.setPipelineFactory(factory);
       
        bootstrap.setOption("tcpNoDelay", true);
        bootstrap.setOption("keepAlive", true);

        // Make a new connection.
        ChannelFuture future = bootstrap.connect(new InetSocketAddress(address, port));
        future.awaitUninterruptibly();
        if (!future.isSuccess()) {
        	logger.error("connect fail : {}", future.getCause().getMessage());
            throw new ConnectionFailureException(future.getCause());
        }
        
        return future.getChannel();
    }
}