package com.rmssmobile.gather.action;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com.rmssmobile.gather.model.IFile;
import com.rmssmobile.gather.service.FileService;

import static com.rmssmobile.gather.service.FileService.*;

public class UploadAction extends ActionSupport{

	private static final long serialVersionUID = -8149407251034458649L;

	@SuppressWarnings("unused")
	private static final Logger log = LoggerFactory.getLogger(UploadAction.class);
	
	//for input
	private File file;
	private String fileContentType;	
	private String fileFileName;	
	private String md5;
	
	//for inject
	private FileService fileService;

	//for output
	private IFile uploadFile;
	
	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

	public FileService getFileService() {
		return fileService;
	}

	public void setFileService(FileService fileService) {
		this.fileService = fileService;
	}

	public IFile getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(IFile uploadFile) {
		this.uploadFile = uploadFile;
	}

	@InputConfig(methodName="uploadCheckError")
	public String execute() throws Exception{

		//检查上传文件是否为空
		if(file == null || md5 == null) {
			addActionError(getText("upload.failed.field.is.null")); 
			return ERROR;
		}

		uploadFile = new IFile(file, fileContentType, fileFileName);
		
	    int result = fileService.uploadWithFileBytesMd5(uploadFile, md5);
		//int result = fileService.uploadWithRandomStringMd5(uploadFile, md5);
	    
	    switch (result) {	    
			case UPLOAD_SUCCESS: 
				return SUCCESS;
			case UPLOAD_FAILED_IOEXCEPTION:	
				addActionError(getText("upload.failed.ioexception")); return ERROR;
			case UPLOAD_FAILED_MD5_WRONG: 
				addActionError(getText("upload.failed.md5.wrong")); return ERROR;
			case UPLOAD_FAILED_MD5_EMPTY: 
				addActionError(getText("upload.failed.md5.empty")); return ERROR;
			default: 
				return NONE;		
		}
	}
	
	public String uploadCheckError() {
		try {
			addActionError(getFieldErrors().get("file").get(0));
			return ERROR;
		}
		catch (Exception e) {
			return INPUT;
		}
	}
}




















