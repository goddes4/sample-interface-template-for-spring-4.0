package net.octacomm.sample.netty.client.mng;

import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelPipelineFactory;

public interface Connector
{
	Channel connect(String address, int port, ChannelPipelineFactory factory);
}