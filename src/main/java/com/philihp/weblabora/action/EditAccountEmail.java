package com.philihp.weblabora.action;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

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
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.taglib.html.Constants;
import org.apache.struts.validator.DynaValidatorForm;

import com.philihp.weblabora.form.LoginForm;
import com.philihp.weblabora.jpa.User;
import com.philihp.weblabora.util.UserUtil;

public class EditAccountEmail extends BaseAction {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response, User user)
			throws Exception {

		DynaActionForm form = (DynaActionForm)actionForm;

		EntityManager em = (EntityManager) request.getAttribute("em");
		String email = form.getString("email");
		
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
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.detail", "A confirmation email has been sent to that email. It must be confirmed before it is active."));
			saveMessages(request.getSession(), messages);
			
			return mapping.findForward("success");
		}
		
	}
	}
