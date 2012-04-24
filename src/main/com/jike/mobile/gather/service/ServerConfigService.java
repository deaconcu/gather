package com.jike.mobile.gather.service;

import com.jike.mobile.gather.exception.ServiceException;

public interface ServerConfigService {
	public String get(String key) throws ServiceException;
	
	public Integer getInteger(String key) throws ServiceException;
	
	public void set(String key, String value);
}
