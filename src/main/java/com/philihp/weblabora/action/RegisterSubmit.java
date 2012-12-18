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

import com.philihp.weblabora.form.RegisterForm;
import com.philihp.weblabora.jpa.User;
import com.philihp.weblabora.util.FacebookUtil;
import com.philihp.weblabora.util.UserUtil;

public class RegisterSubmit extends BaseAction {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response, User user)
			throws Exception {

		RegisterForm form = (RegisterForm) actionForm;
		
		String username = form.getUsername().trim();
		String email = form.getEmail().trim();
		String password = form.getPassword();
		String facebookId = (String)request.getSession().getAttribute(FacebookUtil.FACEBOOK_ID);

		EntityManager em = (EntityManager) request.getAttribute("em");
		TypedQuery<User> query;
		List<User> results;
		
		query = em.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class);
		query.setParameter("username", username);
		results = query.getResultList();
		
		if(results.size() != 0) {
			ActionMessages errors = getErrors(request);
			errors.add("username", new ActionMessage("errors.usernameTaken"));
			saveErrors(request, errors);
			return mapping.findForward("input");
		}
		
		query = em.createQuery("SELECT u FROM User u WHERE u.email = :email AND u.facebookId != :facebookId OR u.unvalidatedEmail = :email", User.class);
		query.setParameter("email", email);
		query.setParameter("facebookId", facebookId);
		results = query.getResultList();
		
		if(results.size() != 0) {
			ActionMessages errors = getErrors(request);
			errors.add("email", new ActionMessage("errors.emailTaken"));
			saveErrors(request, errors);
			return mapping.findForward("input");
		}
		
		query = em.createQuery("SELECT u FROM User u WHERE u.facebookId = :facebookId", User.class);
		query.setParameter("facebookId", facebookId);
		results = query.getResultList();
		if(results.size() == 0) {
			user = new User();
			user.setFacebookId(facebookId);
		}
		else {
			user = results.get(0);
		}
		user.setUsername(username);
		user.setUnvalidatedEmail(email);
		user.setEmailValidator(UUID.randomUUID().toString());
		if(password == null || password.equals("")) {
			user.setPassword(null);
		}
		else {
			user.setPassword(UserUtil.md5(password));
		}
		em.persist(user);
		
		//send confirm email
		String emailBody = "Hi "
				+ user.getUsername()
				+ ",\n\nWelcome to Weblabora! Please visit this link to confirm your email address:\n\nhttp://"
				+ request.getServerName()
				+ request.getContextPath()
				+ "/registerValidate.do?validator="
				+ user.getEmailValidator()
				+ "\n\nIf you didn't sign up, please ignore this email. Someone at "
				+ request.getRemoteAddr()
				+ " tried (and failed) to register using your email address.\n\nLove,\nTeam Weblabora";
		String emailSubject = "Weblabora Confirm Email";
		UserUtil.sendEmail(user.getUnvalidatedEmail(), emailSubject, emailBody, (String)getServlet().getServletContext().getAttribute("gmail_password"));
		
		
		ActionMessages messages = getMessages(request);
		messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.needEmailValidation", user.getUnvalidatedEmail()));
		saveMessages(request.getSession(), messages);
		
		
		return mapping.findForward("root");
	}

}
