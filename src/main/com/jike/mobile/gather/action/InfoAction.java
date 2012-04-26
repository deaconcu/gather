package com.jike.mobile.gather.action;

import com.jike.mobile.gather.exception.ServiceException;
import com.jike.mobile.gather.model.Info;
import com.jike.mobile.gather.util.ServerConfig;
import com.opensymphony.xwork2.ActionSupport;

public class InfoAction extends ActionSupport{

	private static final long serialVersionUID = 3947691968393912630L;
	
	private Info info;
	
	// action method
	
	public String execute() {
		info = new Info();
		try {
			info.setVersion(ServerConfig.get("androidVersion"));
			info.setVersionCode(ServerConfig.get("androidVersionCode"));
			info.setDownloadUrl(ServerConfig.get("androidDownloadUrl"));
			info.setVersionDesc(ServerConfig.get("androidVersionDesc"));
			return SUCCESS;
			
		} catch (ServiceException e) {
			return ERROR;
		}
	}
	
	//setter && getter
	
	public Info getInfo() {
		return info;
	}

	public void setInfo(Info info) {
		this.info = info;
	}
}
