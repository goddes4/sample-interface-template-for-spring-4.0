package net.octacomm.sample.netty.msg;

/**
 * REQUEST   	: GUI    -> Server 에 요청하는 메시지, 반드시 응답해야함
 * RESPONSE 	: Server -> GUI    에 응답하는 메시지
 * NOTIFY		: GUI -> Server or Server -> GUI 에 보고 또는 알림 메시지, 응답 없음
 * 
 * @author Hiya
 *
 */
public enum MessageType {

	PING,
	
	// 로그인 요청 메시지
    LOGIN_REQUEST, 
    LOGIN_RESPONSE, 
    
    // 도메인 리스트 요청 메시지
    DOMAIN_LIST_REQUEST,
    DOMAIN_LIST_RESPONSE,
    
    // 도메인 편집 요청 메시지
    DOMAIN_CRUD_REQUEST,
    DOMAIN_CRUD_RESPONSE,
    DOMAIN_SEARCH_REQUEST,
    
    // 메지시 통지 요청
    NOTIFY_MESSAGE_REQUEST,

    // 예외 메시지
    EXCEPTION_RESPONSE,

    BOOLEAN_RESPONSE; 
}
