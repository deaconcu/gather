package com.jike.mobile.gather.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import com.jike.mobile.gather.exception.ServiceException;
import com.jike.mobile.gather.model.AppUser;

public interface AppUserService {

	public void saveFriend(ArrayList<AppUser> appUserList) throws ServiceException;

	public void saveGroup(HashSet<AppUser> appUserSet) throws ServiceException;

	public AppUser findAppUser(String userId, Integer appId) throws ServiceException;
	
}
