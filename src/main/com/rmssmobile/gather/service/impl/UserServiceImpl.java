package com.rmssmobile.gather.service.impl;

import com.rmssmobile.gather.dao.UserLoginDao;
import com.rmssmobile.gather.model.UserLogin;
import com.rmssmobile.gather.service.UserService;

public class UserServiceImpl implements UserService{
	private UserLoginDao userLoginDao;
	
	public UserLoginDao getUserLoginDao() {
		return userLoginDao;
	}

	public void setUserLoginDao(UserLoginDao userLoginDao) {
		this.userLoginDao = userLoginDao;
	}

	private boolean isEmpty(UserLogin userLogin) {
		String username = userLogin.getUserName().trim();
		String password = userLogin.getPassword().trim();
		if(username == null || username.equals("") || password == null || password.equals("")){
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public String login(UserLogin userLogin) {
		if(isEmpty(userLogin)) return "login";
		//String username = userLogin.getUserName().trim();
		//String password = userLogin.getPassword().trim();
		
		
		return null;
	}

	@Override
	public String logOut(UserLogin userLogin) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String signIn(UserLogin userLogin) {
		// TODO Auto-generated method stub
		return null;
	}
	
}	
