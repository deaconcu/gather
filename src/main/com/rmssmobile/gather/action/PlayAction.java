package com.rmssmobile.gather.action;

import javax.servlet.ServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class PlayAction extends ActionSupport{

	private static final long serialVersionUID = -4564945472276467387L;
	//for input
	private String id;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}	

	public String execute() {
		ServletRequest request = ServletActionContext.getRequest();
		
		if(id == null) {
			addActionError(getText("play.fileid.is.empty")); 
			return ERROR;
		}
		if(id.matches("[a-z]{20}_\\d{13}")) {
			String url = ServletActionContext.getServletContext().getInitParameter("downloadUrl") + "?id=" + id;

			request.setAttribute("url",url);
			return SUCCESS;
		}
		else {
			addActionError(getText("play.fileid.is.invalid")); 
			return ERROR;
		}
	}

}
