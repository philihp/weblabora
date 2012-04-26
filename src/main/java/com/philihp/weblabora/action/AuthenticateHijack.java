package com.philihp.weblabora.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.philihp.weblabora.form.HijackForm;
import com.philihp.weblabora.jpa.User;

public class AuthenticateHijack extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HijackForm form = (HijackForm) actionForm;

		if (form.getFacebookId() != null) {
			User user = BaseAction.findUser(form.getFacebookId());
			request.getSession().setAttribute("user", user);
		}

		return mapping.findForward("root");

	}
}
