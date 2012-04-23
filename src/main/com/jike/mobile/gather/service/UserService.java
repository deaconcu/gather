package com.jike.mobile.gather.service;

import com.jike.mobile.gather.model.UserLogin;

public interface UserService {
	
	public String login(UserLogin userLogin);
	
	public String logOut(UserLogin userLogin);
	
	public String signIn(UserLogin userLogin);
}
