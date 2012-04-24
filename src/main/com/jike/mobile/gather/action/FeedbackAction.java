package com.jike.mobile.gather.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jike.mobile.gather.exception.ServiceException;
import com.jike.mobile.gather.model.Feedback;
import com.jike.mobile.gather.model.Page;
import com.jike.mobile.gather.service.FeedbackService;
import com.jike.mobile.gather.util.ServerConfig;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;

public class FeedbackAction extends ActionSupport{

	private static final long serialVersionUID = 8774511419429188697L;

	private Logger log = LoggerFactory.getLogger(FeedbackAction.class);
	
	//input
	private Feedback feedback;
	private Page page = new Page();
	
	//inject
	private FeedbackService feedbackService;
	
	//action method
	
	@InputConfig(resultName=ERROR)
	public String getList() {
		if(page == null || page.getCurrent() < 1) {				
			page = new Page();
			page.setCurrent(1);
		}
		try{
			int size;
			try {
				size = ServerConfig.getInteger("feedback_page_size");
			} catch (ServiceException se) {
				log.error(se.toString());
				addActionMessage(getText(se.getMessage()));
				return ERROR;
			}
			List<Feedback> list = feedbackService.getList(page.getCurrent(), size);
			ServletActionContext.getRequest().setAttribute("list", list);
			return SUCCESS;
		}
		catch (RuntimeException re) {
			log.error("error", re);
			addActionMessage(getText("feedback.fetch.error"));
			return ERROR;
		}
	}
	
	@InputConfig(resultName=ERROR)
	public String add() {
		if(ServletActionContext.getRequest().getMethod().equals("GET")) return INPUT;
		else if(ServletActionContext.getRequest().getMethod().equals("POST")) {
			try{
				feedback.setCategoryid(1);
				feedback.setProjectid(1);
				feedbackService.add(feedback);
				
				addActionMessage(getText("feedback.input.success"));
				return SUCCESS;
			} catch (RuntimeException re) {
				addActionError(getText("feedback.input.error"));
				return ERROR;
			}
		}
		return NONE;
	}
	
	//validate method
	
	public void validateAdd() {
		validateFeedback();
	}
	
	public void validateFeedback() {
		if(ServletActionContext.getRequest().getMethod().equals("POST")) {
			if(feedback == null) addActionError(getText("feedback.input.empty"));
			else if(feedback.getTitle().trim().equals("") || feedback.getContent().trim().equals("")) 
				addActionError(getText("feedback.input.empty"));
			else {
				int titleLength = feedback.getTitle().trim().length();
				int contentLength = feedback.getContent().trim().length();
				if(titleLength < 1 || titleLength > 40) addActionError(getText("feedback.input.title.length"));
				if(contentLength < 1 || contentLength > 200) addActionError(getText("feedback.input.content.length"));
			}
		}
	}
	
	//setter && getter
	
	public Feedback getFeedback() {
		return feedback;
	}

	public void setFeedback(Feedback feedback) {
		this.feedback = feedback;
	}
	
	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public FeedbackService getFeedbackService() {
		return feedbackService;
	}

	public void setFeedbackService(FeedbackService feedbackService) {
		this.feedbackService = feedbackService;
	}
}
















