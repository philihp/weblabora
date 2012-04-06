package com.philihp.weblabora.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.philihp.weblabora.form.HijackForm;
import com.philihp.weblabora.jpa.User;

public class AuthenticateHijack extends BaseAction {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response, User user) throws AuthenticationException, Exception {

		HijackForm form = (HijackForm)actionForm;
		if(form.getFacebookId() == null) form.setFacebookId(user.getFacebookId());

		user = findUser(form.getFacebookId());
		request.getSession().setAttribute("user", user);

		return mapping.findForward("root");

	}
}
