package net.octacomm.sample.netty.msg.login;

/**
 * 로그인 결과 클래스
 *   
 *   Server --> GUI
 *   
 * @author Hiya0613
 *
 */
public enum LoginResult {
	SUCCESS,			// 성공
	NOT_EXIST_USER,		// 실패 - 아이디가 존재하지 않음
	INVALID_PASSWORD,	// 실패 - 비밀번호 오류
	DUPLICATED_ADMIN,	// 실패 - 관리자권한 중복 로그인 
	DUPLICATED_LOGIN,	// 실패 - 사용자 아이디 중복 로그인
	CONNECTION_FAILURE, // 실패 - 서버와 통신연결 실패 : GUI Only
	CANCELED			// 실패 - 사용자가 로그인 취소버튼을 누름 : GUI Only
}
