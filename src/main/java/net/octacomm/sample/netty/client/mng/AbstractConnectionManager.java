package net.octacomm.sample.netty.client.mng;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import net.octacomm.sample.netty.client.exception.ConnectionFailureException;
import net.octacomm.sample.netty.client.exception.NotResponseException;
import net.octacomm.sample.netty.msg.ExceptionResponseMessage;
import net.octacomm.sample.netty.msg.RequestMessage;
import net.octacomm.sample.netty.msg.ResponseMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractConnectionManager implements ConnectionManager {

	private static final int RESPONSE_WAIT_TIME = 5;
	
	// connect 메소드에 의해서 연결 할때 마다 인스턴스가 변경됨
	private Channel channel;

	private ChannelInitializer<SocketChannel> channelInitializer = new ChannelInitializer<SocketChannel>() {

		@Override
		protected void initChannel(SocketChannel ch) throws Exception {
			ch.pipeline().addLast(
					new ObjectEncoder(),
					new ObjectDecoder(10 * 1024 * 1024, ClassResolvers.softCachingResolver(null)),
					clientHandler());
		}
	};

	// 연결 상태를 통지하는 리스너
	private List<ConnectionListener> listeners = new ArrayList<ConnectionListener>();
	private Logger logger = LoggerFactory.getLogger(getClass());
	private Thread senderThread;
	
	protected abstract ChannelHandler clientHandler();
	
	@Override
	public boolean connect(String address, int port) throws ConnectionFailureException {
		boolean result = false;

		channel = new SocketConnector().connect(address, port, channelInitializer);

		if (channel != null) {
			channel.closeFuture().addListener(new ChannelFutureListener() {
				@Override
				public void operationComplete(ChannelFuture future) throws Exception {
					notifyConnectionState(false);
				}
			});

			result = true;
		}

		notifyConnectionState(result);

		return result;
	}

	@Override
	public void disconnect() {
		if (channel != null) {
			notifyConnectionState(false);
			channel.close();

			if (senderThread != null) {
				senderThread.interrupt();
			}
		}
	}

	protected abstract ReceivedLock<ResponseMessage> lock();
	
	@Override
	public ResponseMessage sendMessage(RequestMessage packet) {
		if (channel == null) {
			throw new ConnectionFailureException();
		}

		lock().getRecvLock().clear();
		channel.writeAndFlush(packet).addListener(ChannelFutureListener.FIRE_EXCEPTION_ON_FAILURE);;

		try {
			senderThread = Thread.currentThread();
			ResponseMessage response = lock().getRecvLock().poll(RESPONSE_WAIT_TIME, TimeUnit.SECONDS);

			if (response != null) {
				if (response instanceof ExceptionResponseMessage) {
					throw ((ExceptionResponseMessage) response).getException();
				}
				return response;
			} else {
				throw new NotResponseException(
						"서버로 부터 응답이 오지 않습니다. (waiting time : "
								+ RESPONSE_WAIT_TIME + ")");
			}
		} catch (InterruptedException e) {
			logger.error("", e);
			throw new ConnectionFailureException(e);
		} finally {
			senderThread = null;
		}
	}

	@Override
	public boolean isConnected() {
		if (channel == null)
			return false;
		return channel.isActive();
	}

	@Override
	public void addConnectionListener(ConnectionListener listener) {
		listeners.add(listener);
	}

	@Override
	public boolean removeConnectionListener(ConnectionListener listener) {
		return listeners.remove(listener);
	}

	@Override
	public void removeAllConnectionListeners() {
		listeners.clear();
	}

	private void notifyConnectionState(boolean state) {
		for (ConnectionListener listener : listeners) {
			listener.stateChanged(state);
		}
	}

}
