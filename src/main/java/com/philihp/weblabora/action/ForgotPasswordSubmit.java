package com.philihp.weblabora.action;

import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.philihp.weblabora.form.ForgotPasswordForm;
import com.philihp.weblabora.jpa.User;
import com.philihp.weblabora.util.UserUtil;

public class ForgotPasswordSubmit extends BaseAction {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response, User user)
			throws Exception {

		ForgotPasswordForm form = (ForgotPasswordForm) actionForm;
		
		String username = form.getUsername().trim();
		String email = form.getEmail().trim();

		EntityManager em = (EntityManager) request.getAttribute("em");
		TypedQuery<User> query;
		List<User> results;
		
		query = em.createQuery("SELECT u FROM User u WHERE u.username = :username AND u.email = :email", User.class);
		query.setParameter("username", username);
		query.setParameter("email", email);
		results = query.getResultList();
		
		if(results.size() == 0) {
			ActionMessages errors = getErrors(request);
			errors.add("username", new ActionMessage("errors.recoverNotFound"));
			saveErrors(request, errors);
			return mapping.findForward("input");
		}
		
		user = results.get(0);
		user.setPasswordValidator(UUID.randomUUID().toString());
		em.persist(user);
		
		//send confirm email
		String emailSubject = "Weblabora Password Recovery";
		String emailBody = "Hi "
				+ user.getUsername()
				+ ",\n\nTo reset your password, please visit this link:\n\nhttp://"
				+ request.getServerName()
				+ request.getContextPath()
				+ "/resetPassword.do?validator="
				+ user.getPasswordValidator()
				+ "\n\nIf you didn't request to reset your password, please ignore this email; it was requested by someone at "
				+ request.getRemoteAddr() + ".\n\nLove,\nTeam Weblabora";
		UserUtil.sendEmail(user.getEmail(), emailSubject, emailBody, (String)getServlet().getServletContext().getAttribute("gmail_password"));
		
		ActionMessages messages = getMessages(request);
		messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.detail", "An email has been sent to "+user.getEmail()+" containing a link to reset your password."));
		saveMessages(request.getSession(), messages);
		
		
		return mapping.findForward("root");
	}
}
