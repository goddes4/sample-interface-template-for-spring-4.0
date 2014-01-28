package net.octacomm.sample.netty.msg.domain;

import lombok.Getter;
import lombok.ToString;
import net.octacomm.sample.domain.Domain;
import net.octacomm.sample.netty.msg.AbstractResponseMessage;
import net.octacomm.sample.netty.msg.MessageType;

/**
 * 도메인 편집 응답 메시지
 * 1. 처리 결과(boolean)는 필수
 * 2. 수정된 아이디가 포함된 domain 객체 전달
 * 
 * Direction : Server -> GUI
 * 
 * @author Tykim
 */
@Getter
@ToString
public class DomainCRUDResponseMessage extends AbstractResponseMessage {

	private boolean result;	
	private Domain domain;

	public DomainCRUDResponseMessage(boolean result, Domain domain) {
		super(MessageType.DOMAIN_CRUD_RESPONSE);
		this.result = result;
		this.domain = domain;
	}
}
