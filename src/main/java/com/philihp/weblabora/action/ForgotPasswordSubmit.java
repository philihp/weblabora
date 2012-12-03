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

public class ForgotPasswordSubmit extends BaseAction {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response, User user)
			throws Exception {

		DynaValidatorForm form = (DynaValidatorForm) actionForm;
		
		String username = form.getString("username").trim();
		String email = form.getString("email").trim();

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
				+ user.getEmailValidator()
				+ "\n\nIf you didn't request to reset your password, please ignore this email; it was requested by someone at "
				+ request.getRemoteAddr() + ".\n\nLove,\nTeam Weblabora";
		UserUtil.sendEmail(user.getEmail(), emailSubject, emailBody, (String)getServlet().getServletContext().getAttribute("gmail_password"));
		
		ActionMessages messages = getMessages(request);
		messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.detail", "An email has been sent to "+user.getEmail()+" containing a link to reset your password."));
		saveMessages(request.getSession(), messages);
		
		
		return mapping.findForward("root");
	}
}
