package com.philihp.weblabora.action;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.philihp.weblabora.form.ResetPasswordForm;
import com.philihp.weblabora.jpa.User;

public class ResetPassword extends BaseAction {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response, User user)
			throws Exception {

		ResetPasswordForm form = (ResetPasswordForm)actionForm;
		EntityManager em = (EntityManager) request.getAttribute("em");

		TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.passwordValidator = :validator", User.class);
		query.setParameter("validator", form.getValidator());
		List<User> results = query.getResultList();
		
		if(results.size() == 0) {
			ActionMessages errors = getErrors(request);
			errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.invalidResetValidator"));
			saveErrors(request, errors);
			return mapping.findForward("login");
		}
		else {
			user = results.get(0);
			
			form.setUsername(user.getUsername());

			return mapping.findForward("success");
		}
		
	}
}
