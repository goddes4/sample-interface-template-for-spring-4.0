package net.octacomm.sample.netty.msg;


/**
 * GUI의 요청 메시지에 대한 응답으로 
 * Boolean 결과만 필요한 경우 사용하는 메시지
 * 
 * Direction : Server -> GUI
 * 
 * @author tykim
 */
public class BooleanResponseMessage extends AbstractResponseMessage {

	boolean result;
	
	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public BooleanResponseMessage(boolean result) {
		super(MessageType.BOOLEAN_RESPONSE);
		this.result = result;
	}
   
	@Override
    public String toString() {
        return "BooleanResponseMessage [result=" + result + "]";
    }
}
