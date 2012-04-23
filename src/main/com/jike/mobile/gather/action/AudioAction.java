package com.jike.mobile.gather.action;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jike.mobile.gather.exception.ServiceException;
import com.jike.mobile.gather.model.UploadFile;
import com.jike.mobile.gather.service.AudioService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;

public class AudioAction extends ActionSupport{

	private static final long serialVersionUID = -8149407251034458649L;
	
	Logger log = LoggerFactory.getLogger(AudioAction.class);

	//input
	private File file;
	private String fileContentType;	
	private String fileFileName;	
	private String md5;
	
	//inject
	private AudioService audioService;

	//output
	private UploadFile uploadFile;
	
	//action method
	
	@InputConfig(resultName=ERROR)
	public String upload() {
		if("POST".equals(ServletActionContext.getRequest().getMethod())) {
			uploadFile = new UploadFile(file, fileContentType, fileFileName);
			
			try {
				audioService.uploadWithFileBytesMd5(uploadFile, md5);
				return SUCCESS;
			} catch(ServiceException se) {
		    	addActionError(getText(se.getMessage()));
		    	return ERROR;
		    }
		} else {
			return INPUT;
		}
	}
	
	//validate method
	
	public void validateUpload() {
		if("POST".equals(ServletActionContext.getRequest().getMethod())) {
			if(file == null || md5 == null || "".equals(md5)) {
				addActionError(getText("upload.failed.field.is.null")); 
			}
		}
	}
	
	//output method
	
	public InputStream getPlayUrl() {
		if("POST".equals(ServletActionContext.getRequest().getMethod())) {
			JSONObject root = new JSONObject();
			root.put("playUrl", getBasePath() + "play.do?id=" + uploadFile.getId());
			return getStreamFromJson(root);
		} else return null;
	}
	
	//private method
	
	private String getBasePath() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		
		return basePath;
	}
	
	private InputStream getStreamFromJson(JSONObject object) {
		log.info(object.toString());
		byte[] json = (object.toString()).getBytes();
		log.info(Arrays.toString(json));
		return new ByteArrayInputStream(json);
	}
	
	//getter && setter
	
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

	public AudioService getAudioService() {
		return audioService;
	}

	public void setAudioService(AudioService audioService) {
		this.audioService = audioService;
	}

	public UploadFile getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(UploadFile uploadFile) {
		this.uploadFile = uploadFile;
	}

}




















