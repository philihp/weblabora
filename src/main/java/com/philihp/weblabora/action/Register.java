package com.philihp.weblabora.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.philihp.weblabora.form.RegisterForm;
import com.philihp.weblabora.jpa.User;
import com.philihp.weblabora.util.FacebookCredentials;
import com.philihp.weblabora.util.FacebookUtil;

public class Register extends BaseAction {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response, User user)
			throws Exception {
		
		if(user == null) {
			return new ActionForward(mapping.getParameter());
		}
		else {
			return mapping.findForward("root");
		}
	}
}
