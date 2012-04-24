package com.jike.mobile.gather.util;

import com.jike.mobile.gather.exception.ServiceException;
import com.jike.mobile.gather.service.ServerConfigService;

public class ServerConfig {
	private static ServerConfigService serverConfigService;
	
	public void setServerConfigService(ServerConfigService serverConfigService) {
		ServerConfig.serverConfigService = serverConfigService;
	}
	
	public static String get(String key) throws ServiceException {
		return serverConfigService.get(key);
	}
	
	public static Integer getInteger(String key) throws ServiceException {
		return serverConfigService.getInteger(key);
	}
}

















