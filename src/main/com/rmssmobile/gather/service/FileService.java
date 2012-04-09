package com.rmssmobile.gather.service;

import java.io.InputStream;

import com.rmssmobile.gather.model.IFile;

public interface FileService {
	
	public static final int UPLOAD_SUCCESS = 100;
	public static final int UPLOAD_FAILED_IOEXCEPTION = 201;
	public static final int UPLOAD_FAILED_MD5_WRONG = 202;
	public static final int UPLOAD_FAILED_MD5_EMPTY = 203;
	
	public int uploadWithRandomStringMd5(IFile iFile, String md5);
	
	public int uploadWithFileBytesMd5(IFile iFile, String md5);
	
	public InputStream download(String path);
	
	public String setRandomChars(String sessionName, int length, int start, int range);
}
