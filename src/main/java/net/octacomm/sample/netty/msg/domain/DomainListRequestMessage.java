package net.octacomm.sample.netty.msg.domain;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.octacomm.sample.domain.Domain;
import net.octacomm.sample.netty.msg.AbstractRequestMessage;
import net.octacomm.sample.netty.msg.MessageType;
import net.octacomm.sample.service.CRUDService;

/**
 * 도메인 리스트 요청
 * 1. domainClass에 요청할 클래스를 등록
 * 
 * Direction : GUI --> Server
 * 
 * @author Hiya
 *
 */
@Setter
@Getter
@ToString
public class DomainListRequestMessage extends AbstractRequestMessage<CRUDService<Domain>, DomainListResponseMessage> {
	
	private Class<? extends Domain> domainClass;
	
	public DomainListRequestMessage(Class<? extends Domain> domainClass) {
		super(MessageType.DOMAIN_LIST_REQUEST);
		this.domainClass = domainClass;
	}

	@Override
	public DomainListResponseMessage process(CRUDService<Domain> crudService) {
		List<? extends Domain> domains = crudService.getList();
		return new DomainListResponseMessage(true, domains);
	}
}
