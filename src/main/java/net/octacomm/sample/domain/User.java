package net.octacomm.sample.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class User implements Domain {

	String id;
	String password;
	String name;
	String contact;
	String address1;
	String address2;
	String joinDate;
	
	public void setLoginFlag(boolean loginFlag) {
	}

	public boolean isLoginFlag() {
		return false;
	}

}
