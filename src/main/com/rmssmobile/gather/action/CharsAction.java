package com.rmssmobile.gather.action;

import com.opensymphony.xwork2.ActionSupport;
import com.rmssmobile.gather.service.FileService;

public class CharsAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	//for inject
	FileService fileService;
	
	//for output
	String chars;
	
	public String getChars() {
		return chars;
	}

	public void setChars(String chars) {
		this.chars = chars;
	}

	public FileService getFileService() {
		return fileService;
	}

	public void setFileService(FileService fileService) {
		this.fileService = fileService;
	}

	public String execute() { 
		chars = fileService.setRandomChars("randomChars", 20, 65, 58);	
		return SUCCESS;
	}
}