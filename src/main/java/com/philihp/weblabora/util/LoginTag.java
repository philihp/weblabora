package com.philihp.weblabora.util;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.struts.taglib.TagUtils;

public class LoginTag extends TagSupport {
	
	private String scope = null;

  public int doStartTag() throws JspException {
  	TagUtils tag = TagUtils.getInstance();
  	
  	FacebookCredentials creds = (FacebookCredentials)pageContext.getSession().getAttribute("facebook");
  	tag.write(pageContext, creds.toString());

  	return (SKIP_BODY);
  }

  public void release() {
      super.release();
  }
  
}
