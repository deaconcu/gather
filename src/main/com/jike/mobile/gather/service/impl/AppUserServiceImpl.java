package com.jike.mobile.gather.service.impl;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jike.mobile.gather.dao.AppDao;
import com.jike.mobile.gather.dao.AppUserDao;
import com.jike.mobile.gather.dao.AppUserGroupDao;
import com.jike.mobile.gather.exception.ServiceException;
import com.jike.mobile.gather.model.App;
import com.jike.mobile.gather.model.AppUser;
import com.jike.mobile.gather.model.AppUserGroup;
import com.jike.mobile.gather.service.AppUserService;

public class AppUserServiceImpl implements AppUserService {
	
	Logger log = LoggerFactory.getLogger(AppUserServiceImpl.class);

	private AppDao appDao;
	private AppUserDao appUserDao;
	private AppUserGroupDao appUserGroupDao;

	@Override
	public void saveFriend(ArrayList<AppUser> appUserList) throws ServiceException {
		try {
			for(AppUser appUser : appUserList ) {
				App app = appDao.findById(appUser.getApp().getId());
				if(app == null) continue;
	
				Set<AppUser> friends = appUser.getFriends();
	
				Iterator<AppUser> iterator = friends.iterator();
				List<AppUser> friendsPersisted = new ArrayList<AppUser>();
				while(iterator.hasNext()) {
					AppUser friend = iterator.next();
					List<AppUser> list = appUserDao.findByUserIdAndApp(friend.getUserId(),app);
	
					if(list.size() == 0) {
						friend.setCreateTime(System.currentTimeMillis());
						appUserDao.save(friend);
					} else {
						friendsPersisted.add(list.get(0));
						iterator.remove();
					}
				}
				friends.addAll(friendsPersisted);
	
				List<AppUser> list = appUserDao.findByUserIdAndApp(appUser.getUserId(), app);
				if(list.size() == 0) {
					appUser.setCreateTime(System.currentTimeMillis());
					appUserDao.save(appUser);
				} else {
					AppUser appUserPersisted = list.get(0);
					appUserPersisted.setFriends(appUser.getFriends());
					appUserDao.update(appUserPersisted);
				}
			}
		} catch (RuntimeException re) {
			log.error("runtimeException");
			throw new ServiceException("system.internal.error");
		}
	}

	@Override
	public void saveGroup(ArrayList<AppUser> appUserList) throws ServiceException {
		Set<AppUser> contain = new HashSet<AppUser>();
		String containString = "";
		for(AppUser appUser: appUserList) {
			App app = appDao.findById(appUser.getApp().getId());
			if(app != null) {
				List<AppUser> list = appUserDao.findByUserIdAndApp(appUser.getUserId(), app);
				if(list.size() == 0) {
					appUser.setCreateTime(System.currentTimeMillis());
					appUserDao.save(appUser);
					contain.add(appUser);
				} else {
					contain.add(list.get(0));
				}
				containString += appUser.getUserId() + "|" + app.getId() + ","; 
			}
		}
		
		if(contain.size() > 1) {
			List<AppUserGroup> list = appUserGroupDao.findByProperty("containString", containString);

			if(list.size() == 0) {
				AppUserGroup appUserGroup = new AppUserGroup();
				appUserGroup.setCreateTime(System.currentTimeMillis());
				appUserGroup.setContains(contain);
				appUserGroupDao.save(appUserGroup);
			}
		}
	}

	public AppDao getAppDao() {
		return appDao;
	}

	public void setAppDao(AppDao appDao) {
		this.appDao = appDao;
	}

	public AppUserDao getAppUserDao() {
		return appUserDao;
	}

	public void setAppUserDao(AppUserDao appUserDao) {
		this.appUserDao = appUserDao;
	}

	public AppUserGroupDao getAppUserGroupDao() {
		return appUserGroupDao;
	}

	public void setAppUserGroupDao(AppUserGroupDao appUserGroupDao) {
		this.appUserGroupDao = appUserGroupDao;
	}

}
