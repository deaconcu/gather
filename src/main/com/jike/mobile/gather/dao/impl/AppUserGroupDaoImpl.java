package com.jike.mobile.gather.dao.impl;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jike.mobile.gather.dao.AppUserGroupDao;
import com.jike.mobile.gather.model.AppUser;
import com.jike.mobile.gather.model.AppUserGroup;

public class AppUserGroupDaoImpl extends BaseDaoImpl<AppUserGroup> implements AppUserGroupDao {
	Logger log = LoggerFactory.getLogger(AppUserDaoImpl.class);
	@Override
	public List<AppUserGroup> findByUserIds(Set<AppUser> userIds) {
		try {
			String queryString = "from AppUserGroup group where '1' in indices(group.contains)";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find by userId and app failed: " + re.toString());
			throw re;
		}
	}
	
}
