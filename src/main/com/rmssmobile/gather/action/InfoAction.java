package com.rmssmobile.gather.action;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.rmssmobile.gather.model.Info;

public class InfoAction extends ActionSupport{

	private static final long serialVersionUID = 3947691968393912630L;
	
	private Info info;
	
	public Info getInfo() {
		return info;
	}

	public void setInfo(Info info) {
		this.info = info;
	}

	public String execute() {
		info = new Info();
		
		ServletContext sc = ServletActionContext.getServletContext();
		
		info.setVersion(sc.getInitParameter("androidVersion"));
		info.setDownloadUrl(sc.getInitParameter("androidDownloadUrl"));
		info.setVersionDesc(sc.getInitParameter("androidVersionDesc"));
		
		return SUCCESS;
	}

}
