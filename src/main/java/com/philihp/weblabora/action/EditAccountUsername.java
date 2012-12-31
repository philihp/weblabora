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

import com.philihp.weblabora.form.AccountForm;
import com.philihp.weblabora.jpa.User;

public class EditAccountUsername extends BaseAction {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response, User user)
			throws Exception {

		AccountForm form = (AccountForm)actionForm;

		EntityManager em = (EntityManager) request.getAttribute("em");
		String username = form.getUsername();
		
		TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.username = :username AND u != :user", User.class);
		query.setParameter("username", username);
		query.setParameter("user", user);
		List<User> results = query.getResultList();
		
		if(results.size() == 0) {
			user.setUsername(username);
			ActionMessages messages = getMessages(request);
			messages.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("message.usernameChanged", username));
			saveMessages(request.getSession(), messages);
			return mapping.findForward("success");
		}
		else {
			ActionMessages errors = getErrors(request);
			errors.add("username", new ActionMessage("errors.usernameTaken"));
			saveErrors(request.getSession(), errors);
			return mapping.findForward("failure");
		}
		
	}
	}
