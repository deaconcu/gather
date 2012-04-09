package com.rmssmobile.gather.service;

import com.rmssmobile.gather.model.UserLogin;

public interface UserService {
	
	public String login(UserLogin userLogin);
	
	public String logOut(UserLogin userLogin);
	
	public String signIn(UserLogin userLogin);
}
