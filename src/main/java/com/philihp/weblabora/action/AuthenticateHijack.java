package com.philihp.weblabora.action;

import javax.persistence.EntityManager;
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
		EntityManager em = (EntityManager)request.getAttribute("em");
		
		String hijackPassword = (String)getServlet().getServletContext().getAttribute("hijack_password");

		if (form.getFacebookId() != null && form.getPassword() != null && form.getPassword().equals(hijackPassword)) {
			User user = null;//BaseAction.findUser(em, form.getFacebookId());
			request.getSession().setAttribute("user", user);
		}

		return mapping.findForward("root");

	}
}
