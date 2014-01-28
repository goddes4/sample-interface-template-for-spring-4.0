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
 * 도메인 편집 요청 메시지
 * 1. domain에 작업할 객체를 저장 - 필수 (등록, 수정, 삭제 일 경우 필수)
 * 2. domainClass에 조회할 클래스를 저장 (조회 일 경우 필수)
 * 3. EditType은 REGIST(등록), READ(조회), UPDATE(수정), DELETE(삭제)
 * 
 * Direction : GUI --> Server
 * 
 * @author Hiya
 *
 */
@Setter
@Getter
@ToString
public class DomainCRUDRequestMessage extends AbstractRequestMessage<CRUDService<Domain>, DomainCRUDResponseMessage> {
	
	private Domain domain;
	private CRUDType crudType;
	private Class<? extends Domain> domainClass;
	private List<? extends Domain> domains;
	
	public DomainCRUDRequestMessage(Class<? extends Domain> domainClass) {
		super(MessageType.DOMAIN_CRUD_REQUEST);
		this.domainClass = domainClass;
	}

	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public DomainCRUDResponseMessage process(CRUDService crudService) {
		boolean ret = false;
		
		switch (crudType) {
			case REGIST:
				// MySQL에의 자동 생성되는 Key경우에는 GUI에 알려야 한다.
//				try {
					ret = crudService.add(domain);
//				} catch (DuplicateKeyException e) {
//					logger.error("{}", e.getMessage());
//				}
				break;
				
			case UPDATE:
				ret = crudService.edit(domain);
				break;
				
			case DELETE:
				ret = crudService.delete(domain);
				break;

			case DELETE_SELECTION:
				List<Domain> domains = (List<Domain>) getDomains();
				if (domains != null && domains.size() > 0) {
					ret = crudService.deleteSelection(domains);
				}
				break;
				
			case DELETE_ALL:
				ret = crudService.deleteAll();
				break;

			default:
				break;
		}
		
		return new DomainCRUDResponseMessage(ret, domain);
	}

}
