package com.rmssmobile.gather.jsp.tagext;

import java.io.IOException;
import java.util.Date;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class HelloWorld extends SimpleTagSupport{
	public void doTag() throws JspException, IOException {
		getJspContext().getOut().write("Hello World " + new Date(System.currentTimeMillis()));
	}
}
