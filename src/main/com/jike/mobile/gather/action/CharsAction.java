package com.jike.mobile.gather.action;

import com.jike.mobile.gather.service.AudioService;
import com.opensymphony.xwork2.ActionSupport;

public class CharsAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	//for inject
	AudioService audioService;
	
	//for output
	String chars;
	
	public String getChars() {
		return chars;
	}

	public void setChars(String chars) {
		this.chars = chars;
	}

	public AudioService getAudioService() {
		return audioService;
	}

	public void setAudioService(AudioService audioService) {
		this.audioService = audioService;
	}

	public String execute() { 
		chars = audioService.setRandomChars("randomChars", 20, 65, 58);	
		return SUCCESS;
	}
}