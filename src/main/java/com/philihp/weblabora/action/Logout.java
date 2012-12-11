package com.philihp.weblabora.action;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.philihp.weblabora.form.LoginForm;
import com.philihp.weblabora.jpa.User;
import com.philihp.weblabora.util.FacebookUtil;

public class Logout extends BaseAction {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response, User user) throws Exception {
		
		LoginForm form = (LoginForm)actionForm;
		String referer = form.getReferer();
		if(referer == null) {
			referer = mapping.findForward("root").getPath();
		}
		
		request.getSession().setAttribute("user", null);
		request.getSession().setAttribute(FacebookUtil.FACEBOOK_ID, null);
		for(Cookie cookie : request.getCookies()) {
			if(COOKIE_KEY.equals(cookie.getName())) {
				cookie.setMaxAge(0);
				response.addCookie(cookie);
				System.out.println("Set Cookie "+cookie+" maxAge=0");
			}
		}

		return new ActionForward(referer, true);
	}

}
