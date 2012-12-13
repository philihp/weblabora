package com.philihp.weblabora.action;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.util.MD5Encoder;
import org.apache.commons.codec.binary.Hex;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.taglib.html.Constants;
import org.apache.struts.validator.DynaValidatorForm;

import com.philihp.weblabora.form.LoginForm;
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
