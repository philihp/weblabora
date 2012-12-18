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
import com.philihp.weblabora.util.UserUtil;

public class ResetPasswordSubmit extends BaseAction {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response, User user)
			throws Exception {

		ResetPasswordForm form = (ResetPasswordForm)actionForm;
		EntityManager em = (EntityManager) request.getAttribute("em");

		TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.passwordValidator = :validator", User.class);
		query.setParameter("validator", form.getValidator());
		List<User> results = query.getResultList();
		
		if(results.size() == 0 || form.getValidator() == null) {
			ActionMessages errors = getErrors(request);
			errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.invalidResetValidator"));
			saveErrors(request, errors);
			return mapping.findForward("failure");
		}
		else {
			user = results.get(0);
			user.setPasswordValidator(null);
			user.setPassword(UserUtil.md5(form.getNewPassword()));
			
			ActionMessages messages = getMessages(request);
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.detail", "Password has been reset"));
			saveMessages(request.getSession(), messages);

			request.getSession().setAttribute("user", user);
			saveUserFingerprint(em, response, user);
			return mapping.findForward("success");
		}
		
	}
}
