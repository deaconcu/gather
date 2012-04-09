package com.rmssmobile.gather.model;

import java.io.File;

public class IFile {
	File file;
	String ContentType;
	String filename;
	String savePath;
	String id;
	String playUrl;

	public IFile() {}
	
	public IFile(File file, String contentType, String filename) {
		this.file = file;
		this.ContentType = contentType;
		this.filename = filename;
	}
	
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public String getContentType() {
		return ContentType;
	}
	public void setContentType(String contentType) {
		ContentType = contentType;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}	
	public String getSavePath() {
		return savePath;
	}
	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPlayUrl() {
		return playUrl;
	}

	public void setPlayUrl(String playUrl) {
		this.playUrl = playUrl;
	}
	
	
}
