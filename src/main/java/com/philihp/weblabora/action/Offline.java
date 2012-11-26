package com.philihp.weblabora.action;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.philihp.weblabora.jpa.User;

public class Offline extends BaseAction {
	
	public static final String TOKEN = "AAAB64qTU0LsBAAlVCyaJUlJQpS1UONZBuYxcvzqxHKQqiFAODEbOrnytH41zsqOQXSdXMWmT1fsKPxWY9MzaPfOzkx0aBseUbYioukgZDZD";

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response, User user)
			throws Exception {
		EntityManager em = (EntityManager)request.getAttribute("em");

		user = null;//findUser(em, "11803742");
		request.getSession().setAttribute("user", user);
		
		return mapping.findForward("root");
	}
}
