package com.jike.mobile.gather.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class AppUser implements Serializable{

	private static final long serialVersionUID = -1537963861940975871L;
	
	private Integer id;
	private String userId;
	private App app;
	private Long createTime;
	private Set<AppUser> friends = new HashSet<AppUser>();
	private Set<AppUserGroup> groups = new HashSet<AppUserGroup>();
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public App getApp() {
		return app;
	}
	public void setApp(App app) {
		this.app = app;
	}
	public Long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}
	public Set<AppUser> getFriends() {
		return friends;
	}
	public void setFriends(Set<AppUser> friends) {
		this.friends = friends;
	}
	public Set<AppUserGroup> getGroups() {
		return groups;
	}
	public void setGroups(Set<AppUserGroup> groups) {
		this.groups = groups;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o instanceof AppUser) {
			if(userId.equals(((AppUser) o).getUserId()) && app.getId() == ((AppUser)o).getApp().getId()) return true;
			return false;
		}
		else return false;
	}
	
	@Override
	public String toString() {
		return "userId: " + userId + " appid: " + app.getId() + " friends: " + friends;
	}
}














