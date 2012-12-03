package com.philihp.weblabora.action;

import java.math.BigInteger;

import java.security.MessageDigest;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
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
import org.apache.struts.validator.DynaValidatorForm;

import com.philihp.weblabora.jpa.User;
import com.philihp.weblabora.util.UserUtil;

public class RegisterSubmit extends BaseAction {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response, User user)
			throws Exception {

		DynaValidatorForm form = (DynaValidatorForm) actionForm;
		
		String username = form.getString("username").trim();
		String email = form.getString("email").trim();
		String password = form.getString("password");

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
		
		query = em.createQuery("SELECT u FROM User u WHERE u.email = :email OR u.unvalidatedEmail = :email", User.class);
		query.setParameter("email", email);
		results = query.getResultList();
		
		if(results.size() != 0) {
			ActionMessages errors = getErrors(request);
			errors.add("email", new ActionMessage("errors.emailTaken"));
			saveErrors(request, errors);
			return mapping.findForward("input");
		}
		
		user = new User();
		user.setUsername(username);
		user.setUnvalidatedEmail(email);
		user.setEmailValidator(UUID.randomUUID().toString());
		user.setPassword(UserUtil.md5(password));
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
		messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.detail", "An email has been sent to "+user.getUnvalidatedEmail()+" with instructions for validating your username."));
		saveMessages(request.getSession(), messages);
		
		
		return mapping.findForward("root");
	}

}
