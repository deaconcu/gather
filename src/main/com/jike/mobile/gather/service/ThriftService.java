package com.jike.mobile.gather.service;

import java.io.File;

import com.jike.mobile.gather.exception.ServiceException;

public interface ThriftService {
	
	public int put(String key, File file) throws ServiceException;
	
	public byte[] read(String key) throws ServiceException;
}
