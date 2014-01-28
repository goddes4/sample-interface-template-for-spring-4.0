package net.octacomm.sample.netty;

import java.util.List;

import net.octacomm.sample.domain.Domain;
import net.octacomm.sample.netty.client.exception.ConnectionFailureException;
import net.octacomm.sample.netty.client.mng.ConnectionManager;
import net.octacomm.sample.netty.msg.ResponseMessage;
import net.octacomm.sample.netty.msg.domain.CRUDType;
import net.octacomm.sample.netty.msg.domain.DomainCRUDRequestMessage;
import net.octacomm.sample.netty.msg.domain.DomainCRUDResponseMessage;
import net.octacomm.sample.netty.msg.domain.DomainListRequestMessage;
import net.octacomm.sample.netty.msg.domain.DomainListResponseMessage;
import net.octacomm.sample.service.CRUDService;

import org.springframework.beans.factory.annotation.Autowired;

public abstract class NettyCRUDService<T extends Domain> implements CRUDService<T> {

	@Autowired 
	private ConnectionManager connectionManager;
	
	private DomainListRequestMessage domainListMsg = new DomainListRequestMessage(getDomainClass());
	protected DomainCRUDRequestMessage domainCRUDMsg = new DomainCRUDRequestMessage(getDomainClass());

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getList() throws ConnectionFailureException {
		ResponseMessage response = connectionManager.sendMessage(domainListMsg);

		return (List<T>) ((DomainListResponseMessage) response).getResources();
	}
	
	public abstract Class<? extends Domain> getDomainClass();

	@Override
	public boolean add(T domain) throws ConnectionFailureException {
		domainCRUDMsg.setCrudType(CRUDType.REGIST);
		DomainCRUDResponseMessage res = sendDomainCRUDReq(domain);
		
		return res.isResult();
	}

	@Override
	public boolean edit(T domain) throws ConnectionFailureException {
		domainCRUDMsg.setCrudType(CRUDType.UPDATE);
		DomainCRUDResponseMessage res = sendDomainCRUDReq(domain);
		
		return res.isResult();
	}

	@Override
	public boolean delete(T domain) throws ConnectionFailureException {
		domainCRUDMsg.setCrudType(CRUDType.DELETE);
		DomainCRUDResponseMessage res = sendDomainCRUDReq(domain);
		
		return res.isResult();
	}

	@Override
	public boolean deleteAll() throws ConnectionFailureException {
		domainCRUDMsg.setCrudType(CRUDType.DELETE_ALL);
		DomainCRUDResponseMessage res = sendDomainCRUDReq();
		
		return res.isResult();
	}

	@Override
	public boolean deleteSelection(List<T> domains) throws ConnectionFailureException {
		domainCRUDMsg.setCrudType(CRUDType.DELETE_SELECTION);
		DomainCRUDResponseMessage res = sendDomainCRUDReq(domains);
		
		return res.isResult();
	}
	
	protected DomainCRUDResponseMessage sendDomainCRUDReq(List<T> domains) {
		domainCRUDMsg.setDomains(domains);
		return sendDomainCRUDReq();
	}
	
	protected DomainCRUDResponseMessage sendDomainCRUDReq(T domain) {
		domainCRUDMsg.setDomain(domain);
		return sendDomainCRUDReq();
	}
	
	private DomainCRUDResponseMessage sendDomainCRUDReq() {
		ResponseMessage response = connectionManager.sendMessage(domainCRUDMsg);
		return (DomainCRUDResponseMessage) response;
	}

}