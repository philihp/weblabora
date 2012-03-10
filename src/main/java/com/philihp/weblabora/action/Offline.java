package com.philihp.weblabora.action;

import java.io.ByteArrayOutputStream;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import antlr.StringUtils;

import com.philihp.weblabora.jpa.User;
import com.philihp.weblabora.util.Facebook;
import com.philihp.weblabora.util.FacebookCredentials;

public class Offline extends BaseAction {
	
	public static final String TOKEN = "AAAB64qTU0LsBAAlVCyaJUlJQpS1UONZBuYxcvzqxHKQqiFAODEbOrnytH41zsqOQXSdXMWmT1fsKPxWY9MzaPfOzkx0aBseUbYioukgZDZD";

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response, User user)
			throws Exception {

		user = findUser("11803742");
		request.getSession().setAttribute("user", user);
		
		return mapping.findForward("root");
	}
}
