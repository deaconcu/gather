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

	// fields
	
	// input
	private String idAndFriends;
	private String group;
	
	private String userId;
	private Integer appId;
	
	// output
	private AppUser appUser;

	// inject
	private AppUserService appUserService;

	// action method
	@InputConfig(resultName = ERROR)
	public String saveFriend() {
		ArrayList<AppUser> appUserList = new ArrayList<AppUser>();
		try {
			readFriendsFromJson(idAndFriends, appUserList);
			appUserService.saveFriend(appUserList);

			addActionMessage(getText("operation.success"));
			return SUCCESS;

		} catch (ServiceException se) {
			addActionError(getText(se.getMessage()));
			return ERROR;
		}
	}

	@InputConfig(resultName = ERROR)
	public String saveGroup() {
		HashSet<AppUser> appUserSet = new HashSet<AppUser>();
		try {
			readGroupFromJson(getGroup(), appUserSet);
			appUserService.saveGroup(appUserSet);

			addActionMessage(getText("operation.success"));
			return SUCCESS;
		} catch (ServiceException se) {
			addActionError(getText(se.getMessage()));
			return ERROR;
		}
	}

	@InputConfig(resultName = ERROR)
	public String findUser() {
		try {
			appUser = appUserService.findAppUser(userId, appId);
			return SUCCESS;
		} catch(ServiceException se) {
			addActionError(getText(se.getMessage()));
			return ERROR;
		}
	}

	// validate
	public void validateSaveFriend() {
		if (idAndFriends == null && "".equals(idAndFriends)) {
			addActionError(getText("input.argu.is.null"));
		}
	}

	public void validateSaveGroup() {
		if (getGroup() == null && "".equals(getGroup())) {
			addActionError(getText("input.argu.is.null"));
		}
	}

	public void validateFindUser() {
		validateUserIdAndAppId();
	}

	public void validateUserIdAndAppId() {
		if(userId == null || "".equals(userId) || appId == null) {
			addActionError(getText("input.argu.is.null"));
		}
		else if(userId.length() > 64) {
			addActionError(getText("input.argu.is.invalid"));
		}
	}

	// private

	/**
	 * 将请求的json数据转成AppUser List
	 * 
	 * @param s
	 * @param appUserList
	 * @throws ServiceException
	 */
	public void readFriendsFromJson(String s, List<AppUser> appUserList)
			throws ServiceException {
		try {
			JSONArray array = JSONArray.fromObject(s);
			for (int i = 0; i < array.size(); i++) {
				JSONObject item = array.getJSONObject(i);

				Integer appId = Integer.parseInt(item.getString("appId"));
				if (appId == null)
					continue;
				App app = new App(appId);

				String userId = item.getString("userId");
				if (userId == null || "".equals(userId) || userId.length() > 64)
					continue;

				JSONArray friendArray = item.getJSONArray("friends");
				HashSet<AppUser> friendSet = new HashSet<AppUser>();
				for (int j = 0; j < friendArray.size(); j++) {
					String friendUserId = friendArray.getString(j);
					if (friendUserId == null || "".equals(friendUserId)
							|| friendUserId.length() > 64)
						continue;
					AppUser appUser = new AppUser();
					appUser.setUserId(friendUserId);
					appUser.setApp(app);
					friendSet.add(appUser);
				}
				if (friendSet.size() == 0)
					continue;

				AppUser appUser = new AppUser();
				appUser.setUserId(userId);
				appUser.setApp(app);
				appUser.setFriends(friendSet);

				appUserList.add(appUser);
			}
		} catch (Exception e) {
			log.error("Friends JSON String convert failed! exception: "
					+ e.toString());
			throw new ServiceException("input.argu.is.invalid");
		}
		if (appUserList.size() == 0)
			throw new ServiceException("input.argu.is.invalid");
	}

	private void readGroupFromJson(String s, HashSet<AppUser> appUserSet)
			throws ServiceException {
		try {
			JSONArray array = JSONArray.fromObject(s);
			for (int i = 0; i < array.size(); i++) {
				JSONObject item = array.getJSONObject(i);
				Integer appId = Integer.parseInt(item.getString("appId"));
				if (appId == null)
					continue;
				App app = new App(appId);

				String userId = item.getString("userId");
				if (userId == null || "".equals(userId) || userId.length() > 64)
					continue;
				AppUser appUser = new AppUser();
				appUser.setUserId(userId);
				appUser.setApp(app);

				appUserSet.add(appUser);
			}
		} catch (Exception e) {
			log.error("Group JSON String convert failed! exception: "
					+ e.toString());
			throw new ServiceException("input.argu.is.invalid");
		}
		if (appUserSet.size() == 0)
			throw new ServiceException("input.argu.is.invalid");
		System.out.println(appUserSet);
	}

	// setter&&getter


	public String getIdAndFriends() {
		return idAndFriends;
	}

	public void setIdAndFriends(String idAndFriends) {
		this.idAndFriends = idAndFriends;
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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getAppId() {
		return appId;
	}

	public void setAppId(Integer appId) {
		this.appId = appId;
	}

	public AppUser getAppUser() {
		return appUser;
	}

	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}
}
