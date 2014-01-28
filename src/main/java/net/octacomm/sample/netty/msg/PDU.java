package net.octacomm.sample.netty.msg;

import java.io.Serializable;

/**
 * 
 * 모든 메시지의 최상위 클래스이다.
 * TCP Socket을 통해서 객체를 전달하기 때문에 Serializable을 반드시 적용해야 한다.
 * 
 * 요청 메시지는 RequestMessage 인터페이스를 가져야 한다.
 * 응답 메시지는 ResponseMessage 인터페이스를 가져야 한다. 
 * 
 * Direction : GUI -> Server, Server -> GUI
 * 
 * @author tykim
 */
abstract public class PDU implements Serializable {

    public static PDU PING_PDU = new PDU(MessageType.PING){};
    
    protected MessageType messageType;

    public PDU(MessageType messageType) {
        this.messageType = messageType;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    @Override
    public String toString() {
        return "PDU [messageType=" + messageType + "]";
    }
}
