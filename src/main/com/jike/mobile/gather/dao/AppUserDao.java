package com.jike.mobile.gather.dao;

import java.util.List;

import com.jike.mobile.gather.model.App;
import com.jike.mobile.gather.model.AppUser;

public interface AppUserDao extends BaseDao<AppUser>{

	List<AppUser> findByUserIdAndApp(String userId, App app);
	
}
