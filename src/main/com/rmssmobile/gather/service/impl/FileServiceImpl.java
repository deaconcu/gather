package com.rmssmobile.gather.service.impl;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionContext;
import com.rmssmobile.gather.model.IFile;
import com.rmssmobile.gather.service.FileService;
import com.rmssmobile.gather.util.Random;
import com.rmssmobile.gather.util.Transfer;

public class FileServiceImpl implements FileService{
	private static final Logger log = LoggerFactory.getLogger(FileServiceImpl.class);
	
	@Override
	public int uploadWithRandomStringMd5(IFile iFile, String md5) {	
		if(md5 == null) return UPLOAD_FAILED_MD5_EMPTY;
		if(!check(md5, "randomChars", "uploadCode")) return UPLOAD_FAILED_MD5_WRONG;
		
		removeRandomChars("randomChars");
		return  upload(iFile);
	}

	@Override
	public int uploadWithFileBytesMd5(IFile iFile, String md5) {
		byte[] b = null;
		try {
			b = getBytes(iFile, 128);
		}
		catch (IOException e) {
			return UPLOAD_FAILED_IOEXCEPTION;
		}
		
		if(!check(md5, b, "uploadCode")){;
			return UPLOAD_FAILED_MD5_WRONG;
		}
		
		return upload(iFile);
	}

	private int upload(IFile ifile) {	
		
		File file = ifile.getFile();		
		ServletContext sc = ServletActionContext.getServletContext();
		
		FileOutputStream fos = null;
		FileInputStream fis = null;
		
		try{
			String fileId = Random.getChars(20, 97, 24) +  "_" 
					+ Long.toString(System.currentTimeMillis());
			String savePath = sc.getRealPath("/") + sc.getInitParameter("fileSavePath") 
					+ File.separator + fileId;
			
			fos = new FileOutputStream(savePath);
			fis = new FileInputStream(file);
			
			log.info("uploadPath: " + savePath);
			
			byte[] buffer = new byte[1024];
			int len = 0;
			
			while((len = fis.read(buffer)) > 0) {
				fos.write(buffer, 0, len);
			}
			
			ifile.setSavePath(savePath);
			ifile.setId(fileId);
			ifile.setPlayUrl(getPlayUrlFromId(fileId));

			return UPLOAD_SUCCESS;
		}
		catch (IOException e) {
			log.debug(e.toString());
			return UPLOAD_FAILED_IOEXCEPTION;
		}
		finally{
			try {
				fos.close();
				fis.close();
			} catch (IOException e) {
				log.warn("file not close");
			}
		}
	}

	@Override
	public InputStream download(String path) {
		log.info(path);
		return ServletActionContext.getServletContext().getResourceAsStream(path);
	}

	private byte[] getBytes(IFile ifile, int n) throws IOException{
		byte[] b = new byte[n];
		InputStream iStream = new BufferedInputStream(new FileInputStream(ifile.getFile()));
		iStream.read(b);
		iStream.close();
		return b;
	}

	private boolean check(String md5Input, String sessionName, String codeName) {
		try {
			return check(md5Input, 
					(ServletActionContext.getServletContext().getInitParameter(codeName) 
					+ ActionContext.getContext().getSession().get(sessionName))
					.getBytes("utf-8"));
		} catch (UnsupportedEncodingException e) {
			log.info(e.toString());
			return false;
		}
	}
	
	private boolean check(String md5Input, byte[] fileBytes, String codeName) {
		byte[] codeNameBytes = null;
		try {
			codeNameBytes = ServletActionContext.getServletContext().
					getInitParameter(codeName).getBytes("utf-8"); 
		} catch (UnsupportedEncodingException e) {
			return false;
		}
		
		byte[] b = new byte[codeNameBytes.length + fileBytes.length];
		
		System.arraycopy(codeNameBytes, 0, b, 0, codeNameBytes.length);
		System.arraycopy(fileBytes, 0, b, codeNameBytes.length, fileBytes.length);
		
		return check(md5Input, b);
	}
	
	private boolean check(String md5Input, byte[] b) {
		String md5 = Transfer.getMD5(b);
		
		log.info("md5Client: " + md5Input + " | md5Server: " + md5);
		if(md5.equals(md5Input)) return true;
		else return false;
	}
	
	private void removeRandomChars(String sessionName) {
		ActionContext.getContext().getSession().put(sessionName, null);
	}
	

	@Override
	public String setRandomChars(String sessionName, int length, int start,	int range) {
		String chars = Random.getChars(length, start, range);
		ActionContext.getContext().getSession().put(sessionName, chars);
		return chars;
	}

	private String getPlayUrlFromId(String fileId) {
		HttpServletRequest request = ServletActionContext.getRequest();
		ServletContext context = ServletActionContext.getServletContext();
		return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() 
				+ request.getContextPath() + context.getInitParameter("playUrl") + "?id=" + fileId;
	}
}













