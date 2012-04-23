package com.jike.mobile.gather.service;

import java.io.InputStream;

import com.jike.mobile.gather.exception.ServiceException;
import com.jike.mobile.gather.model.UploadFile;

public interface AudioService {
	
	public static final int UPLOAD_SUCCESS = 100;
	public static final int UPLOAD_FAILED_IOEXCEPTION = 201;
	public static final int UPLOAD_FAILED_MD5_WRONG = 202;
	public static final int UPLOAD_FAILED_MD5_EMPTY = 203;
	
	public void uploadWithRandomStringMd5(UploadFile uploadFile, String md5) throws ServiceException;
	
	public void uploadWithFileBytesMd5(UploadFile uploadFile, String md5) throws ServiceException;
	
	public InputStream download(String path);
	
	public String setRandomChars(String sessionName, int length, int start, int range);
}
