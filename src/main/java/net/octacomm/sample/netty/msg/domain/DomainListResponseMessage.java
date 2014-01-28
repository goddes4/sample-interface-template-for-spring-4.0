package net.octacomm.sample.netty.msg.domain;

import java.util.List;

import lombok.Getter;
import lombok.ToString;
import net.octacomm.sample.domain.Domain;
import net.octacomm.sample.netty.msg.AbstractResponseMessage;
import net.octacomm.sample.netty.msg.MessageType;

/**
 * 도메인 리스트 응답 메시지
 * 1. 처리 결과(boolean)는 필수
 * 2. 도메인 리스트 전달
 * 
 * Direction : Server -> GUI
 * 
 * @author Tykim
 */
@Getter
@ToString
public class DomainListResponseMessage extends AbstractResponseMessage {

	private boolean result;	
	private List<? extends Domain> resources;

	public DomainListResponseMessage(boolean result, List<? extends Domain> resources) {
		super(MessageType.DOMAIN_LIST_RESPONSE);
		this.result = result;
		this.resources = resources;
	}
}
