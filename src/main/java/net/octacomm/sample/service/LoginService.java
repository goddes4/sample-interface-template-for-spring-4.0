package net.octacomm.sample.service;

import net.octacomm.sample.netty.client.exception.ConnectionFailureException;
import net.octacomm.sample.netty.msg.login.LoginResult;

/**
 * 사용자 인증
 * 
 * @author taeyo
 *
 */
public interface LoginService {
	LoginResult login(String id, String password) throws ConnectionFailureException;

	void logout(String userId);
}
