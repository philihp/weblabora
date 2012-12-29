package com.philihp.weblabora.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.philihp.weblabora.form.AccountForm;
import com.philihp.weblabora.jpa.User;

public class ShowAccount extends BaseAction {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response, User user)
			throws Exception {
		AccountForm form = (AccountForm)actionForm;
		
		form.setEmail(user.getEmail());

		//if the user never set a password (like if they just signed up with facebook
		//then let them just set a password without saying their current password.
		request.setAttribute("ALLOW_HARD_RESET", user.getPassword() == null);
		
		return mapping.findForward("view");
	}
}
