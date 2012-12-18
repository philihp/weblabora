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

import com.philihp.weblabora.form.LoginForm;
import com.philihp.weblabora.jpa.Game;
import com.philihp.weblabora.jpa.User;
import com.philihp.weblabora.util.UserUtil;

public class LoginSubmit extends BaseAction {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response, User user)
			throws Exception {

		LoginForm form = (LoginForm) actionForm;
		
		String referer = form.getReferer();
		if(referer == null) {
			referer = mapping.findForward("root").getPath();
		}
		
		// stale browser window
		if (user != null)
			return new ActionForward(referer, true);

		if (form.getUsername() == null || form.getPassword() == null) {
			return mapping.findForward("input");
		}

		EntityManager em = (EntityManager) request.getAttribute("em");
		TypedQuery<User> query;
		List<User> results;
		
		query = em.createQuery("SELECT u FROM User u WHERE u.username = :username AND u.password = :password", User.class);
		query.setParameter("username", form.getUsername());
		query.setParameter("password", UserUtil.md5(form.getPassword()));
		results = query.getResultList();
		
		if(results.size() == 0) {
			ActionMessages errors = getErrors(request);
			errors.add("password", new ActionMessage("errors.invalidLogin"));
			saveErrors(request, errors);
			return mapping.findForward("input");
		}
		
				
		user = results.get(0);
		
		if(user.isEmailValidated() == false) {
			ActionMessages errors = getErrors(request);
			errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.emailInvalid"));
			saveErrors(request, errors);
			return mapping.findForward("input");
		}
		
		user.setPasswordValidator(null);
		request.getSession().setAttribute("user", user);
		saveUserFingerprint(em, response, user);
		
		//this makes it so if we go back to the login page, it doesn't auto-submit
		//HOWEVER 
		form.setPassword(null);

		return new ActionForward(referer, true);
	}


	protected static List<Game> findGamesForUser(EntityManager em, User user) {
		TypedQuery<Game> query = em.createQuery("SELECT g " + "FROM Game g "
				+ "WHERE g.player1.user = :user "
				+ "OR g.player2.user = :user " + "OR g.player3.user = :user "
				+ "OR g.player4.user = :user " + "ORDER BY g.gameId",
				Game.class);
		query.setParameter("user", user);

		List<Game> results = query.getResultList();
		return results;
	}
}
