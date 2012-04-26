package com.jike.mobile.gather.service;

import java.util.ArrayList;

import com.jike.mobile.gather.exception.ServiceException;
import com.jike.mobile.gather.model.AppUser;

public interface AppUserService {

	public void saveFriend(ArrayList<AppUser> appUserList) throws ServiceException;

	public void saveGroup(ArrayList<AppUser> appUserList) throws ServiceException;
	
}
