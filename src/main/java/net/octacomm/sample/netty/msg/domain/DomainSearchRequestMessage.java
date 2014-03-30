package net.octacomm.sample.netty.msg.domain;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.octacomm.sample.domain.Domain;
import net.octacomm.sample.netty.msg.AbstractRequestMessage;
import net.octacomm.sample.netty.msg.MessageType;
import net.octacomm.sample.service.crud.CRUDService;

/**
 * 도메인 검색 요청 메시지
 * - 검색 조건에 해당되는 도메인 리스트를 요청
 * 
 * Direction : GUI --> Server
 * 
 * @author Hiya
 *
 */
@Setter
@Getter
@ToString
public class DomainSearchRequestMessage extends AbstractRequestMessage<CRUDService<Domain>, DomainListResponseMessage> {
	
	private Domain domain;
	
	private Class<? extends Domain> domainClass;
	
	public DomainSearchRequestMessage(Class<? extends Domain> domainClass) {
		super(MessageType.DOMAIN_SEARCH_REQUEST);
		this.domainClass = domainClass;
	}

	@Override
	public DomainListResponseMessage process(CRUDService<Domain> service) {
		List<? extends Domain> domains = service.getListByCondition(domain);
		return new DomainListResponseMessage(true, domains);
	}

}
