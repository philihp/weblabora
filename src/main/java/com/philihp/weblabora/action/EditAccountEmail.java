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

import com.philihp.weblabora.form.AccountForm;
import com.philihp.weblabora.jpa.User;
import com.philihp.weblabora.util.UserUtil;

public class EditAccountEmail extends BaseAction {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response, User user)
			throws Exception {

		AccountForm form = (AccountForm)actionForm;

		EntityManager em = (EntityManager) request.getAttribute("em");
		String email = form.getEmail();
		
		TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.email = :email OR u.unvalidatedEmail = :email", User.class);
		query.setParameter("email", email);
		List<User> results = query.getResultList();
		
		if(email.equals(user.getEmail())) {
			user.setUnvalidatedEmail(null);
			user.setEmailValidator(null);
			return mapping.findForward("success");
		}
		else if(results.size() != 0) {
			ActionMessages errors = getErrors(request);
			errors.add("email", new ActionMessage("errors.emailTaken"));
			saveErrors(request, errors);
			return mapping.findForward("failure");
		}
		else {
			user.setUnvalidatedEmail(email);
			user.setEmailValidator(UUID.randomUUID().toString());
			
			String emailSubject = "Weblabora Confirm Email";
			String emailBody = "Hi "+user.getUsername()+",\n\nPlease confirm your new email by visiting this link:\n\nhttp://"+request.getServerName()+request.getContextPath()+"/registerValidate.do?validator="+user.getEmailValidator()+"\n\nIf you didn't sign up, please ignore this email. Someone at "+request.getRemoteAddr()+" tried (and failed) to register using your email address.\n\nLove,\nTeam Weblabora";
			UserUtil.sendEmail(user.getUnvalidatedEmail(), emailSubject, emailBody, (String)getServlet().getServletContext().getAttribute("gmail_password"));
			
			ActionMessages messages = getMessages(request);
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.emailConfirmationSent", user.getUnvalidatedEmail()));
			saveMessages(request.getSession(), messages);
			
			return mapping.findForward("success");
		}
		
	}
	}
