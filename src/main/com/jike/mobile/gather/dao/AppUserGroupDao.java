package com.jike.mobile.gather.dao;

import java.util.List;
import java.util.Set;

import com.jike.mobile.gather.model.AppUser;
import com.jike.mobile.gather .model.AppUserGroup;

public interface AppUserGroupDao extends BaseDao<AppUserGroup>{
	List<AppUserGroup> findByUserIds(Set<AppUser> userIds);
}
