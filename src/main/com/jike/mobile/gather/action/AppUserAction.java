package com.jike.mobile.gather.action;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.jike.mobile.gather.exception.ServiceException;
import com.jike.mobile.gather.model.App;
import com.jike.mobile.gather.model.AppUser;
import com.jike.mobile.gather.service.AppUserService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;

public class AppUserAction extends ActionSupport {

	private static final long serialVersionUID = -8821722205826424371L;
	Logger log = LoggerFactory.getLogger(AppUserAction.class);

	//fields
	private String idAndfriends;
	private String group;
	
	//inject
	private AppUserService appUserService;
	
	//action method
	@InputConfig(resultName=ERROR)
	public String saveFriend() {
		ArrayList<AppUser> appUserList = new ArrayList<AppUser>();
		try {
			readFriendsFromJson(idAndfriends, appUserList);
			appUserService.saveFriend(appUserList);
			
			addActionMessage(getText("operation.success"));
			return SUCCESS;
			
		} catch (ServiceException se) {
			addActionError(getText(se.getMessage()));
			return ERROR;
		}
	}
	
	@InputConfig(resultName=ERROR)
	public String saveGroup() {
		ArrayList<AppUser> appUserList = new ArrayList<AppUser>();
		try {
			readGroupFromJson(getGroup(), appUserList);
			appUserService.saveGroup(appUserList);
			
			addActionMessage(getText("operation.success"));
			return SUCCESS;
		} catch (ServiceException se) {
			addActionError(getText(se.getMessage()));
			return ERROR;
		}
	}

	//validate
	public void validateSaveFriend() {
		if(idAndfriends == null && "".equals(idAndfriends)) {
			addActionError(getText("input.argu.is.null"));
		}
	}
	
	public void validateSaveGroup() {
		if(getGroup() == null && "".equals(getGroup())) {
			addActionError(getText("input.argu.is.null"));
		}
	}
	
	//private
	
	/**
	 * 将请求的json数据转成AppUser List
	 * @param s
	 * @param appUserList
	 * @throws ServiceException
	 */
	public void readFriendsFromJson(String s, List<AppUser> appUserList) throws ServiceException {
		try {
			JSONArray array = JSONArray.fromObject(s);
			for(int i = 0; i < array.size(); i++) {
				JSONObject item = array.getJSONObject(i);

				Integer appId = Integer.parseInt(item.getString("appId"));
				if(appId == null) continue;
				App app = new App(appId);

				String userId = item.getString("userId");
				if(userId == null || "".equals(userId) || userId.length() > 64) continue;

				JSONArray friendArray = item.getJSONArray("friends");
				HashSet<AppUser> friendSet = new HashSet<AppUser>();
				for(int j = 0; j < friendArray.size(); j++) {
					String friendUserId = friendArray.getString(j);
					if(friendUserId == null || "".equals(friendUserId) || friendUserId.length() > 64) continue;
					AppUser appUser = new AppUser();
					appUser.setUserId(friendUserId);
					appUser.setApp(app);
					friendSet.add(appUser);
				}
				if(friendSet.size() == 0) continue;

				AppUser appUser = new AppUser();
				appUser.setUserId(userId);
				appUser.setApp(app);
				appUser.setFriends(friendSet);

				appUserList.add(appUser);
			}
		} catch (Exception e) {
			log.error("Friends JSON String convert failed! exception: " + e.toString());
			throw new ServiceException("input.argu.is.valid");
		}
		if(appUserList.size() == 0) throw new ServiceException("input.argu.is.valid");
	}
	

	private void readGroupFromJson(String s, ArrayList<AppUser> appUserList) throws ServiceException {
		try {
			JSONArray array = JSONArray.fromObject(s);
			for(int i = 0; i < array.size(); i++) {
				JSONObject item = array.getJSONObject(i);
				Integer appId = Integer.parseInt(item.getString("appId"));
				if(appId == null) continue;
				App app = new App(appId);
				
				String userId = item.getString("userId");
				if(userId == null || "".equals(userId) || userId.length() > 64) continue;
				AppUser appUser = new AppUser();
				appUser.setUserId(userId);
				appUser.setApp(app);
				
				appUserList.add(appUser);
			}
		} catch (Exception e) {
			log.error("Group JSON String convert failed! exception: " + e.toString());
			throw new ServiceException("input.argu.is.valid");
		}
		if(appUserList.size() == 0) throw new ServiceException("input.argu.is.valid");
		System.out.println(appUserList);
	}

	//setter&&getter
	
	public String getIdAndfriends() {
		return idAndfriends;
	}

	public void setIdAndfriends(String idAndfriends) {
		this.idAndfriends = idAndfriends;
	}

	public AppUserService getAppUserService() {
		return appUserService;
	}

	public void setAppUserService(AppUserService appUserService) {
		this.appUserService = appUserService;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

}




















