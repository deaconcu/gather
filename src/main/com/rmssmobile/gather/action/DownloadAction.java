package com.rmssmobile.gather.action;

import java.io.File;
import java.io.InputStream;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.rmssmobile.gather.service.FileService;

public class DownloadAction extends ActionSupport{

	private static final long serialVersionUID = 2274403259743694675L;
	//for input
	private String id;
	
	//for inject 
	private FileService fileService;
	
	//for download
	private InputStream inputStream;

	public FileService getFileService() {
		return fileService;
	}

	public void setFileService(FileService fileService) {
		this.fileService = fileService;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public InputStream getTargetFile() {
		return inputStream;
	}

	public String execute() {
		if(id == null) {
			addActionError(getText("download.parameter.is.empty"));
			return ERROR;
		}
		
		if(id.matches("[a-z]{20}_\\d{13}")) {
			inputStream =  fileService.download(
					ServletActionContext.getServletContext().getInitParameter("fileSavePath") + 
					File.separator + id);
			if(inputStream == null) {
				addActionError(getText("download.parameter.is.invalid"));
				return ERROR;
			}
			return SUCCESS;
		}
		else {
			addActionError(getText("download.parameter.is.illegal"));
			return ERROR;
		}
	}
}
