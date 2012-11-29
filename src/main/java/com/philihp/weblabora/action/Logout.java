package com.philihp.weblabora.action;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.philihp.weblabora.jpa.User;

public class Logout extends BaseAction {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response, User user) throws Exception {
		
		request.getSession().setAttribute("user", null);
		for(Cookie cookie : request.getCookies()) {
			if(COOKIE_KEY.equals(cookie.getName())) {
				cookie.setMaxAge(0);
				response.addCookie(cookie);
				System.out.println("Set Cookie "+cookie+" maxAge=0");
			}
		}

		return mapping.findForward("root");
	}

}
