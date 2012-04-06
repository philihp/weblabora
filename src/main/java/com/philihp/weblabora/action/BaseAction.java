package com.philihp.weblabora.action;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.philihp.weblabora.jpa.User;
import com.philihp.weblabora.util.EntityManagerManager;


abstract class BaseAction extends Action {
	
	@SuppressWarnings("unchecked")
	private static final Set<Object> PUBLIC_ACTIONS = new HashSet<Object>(Arrays.asList(Authenticate.class, AuthenticateGetInfo.class, Offline.class));
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		System.out.println("Action: "+this.getClass().getCanonicalName());
		
		//get the User from the session, but use it to find a new User, because
		//the old object might not be registered with the current therad's EntityManager
		User user = findUser((User)request.getSession().getAttribute("user"));
		request.getSession().setAttribute("user", user);
		
		if(isActionPrivate() && user == null) throw new AuthenticationException();

		return execute(mapping, actionForm, request, response, user);
	}
	
	abstract ActionForward execute(ActionMapping mapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response, User user)
			throws AuthenticationException, Exception;

	private boolean isActionPrivate() {
		return PUBLIC_ACTIONS.contains(this.getClass()) == false;
	}
	
	protected User findUser(User user) {
		if(user == null) return null;
		return findUser(user.getFacebookId());
	}
	
	protected User findUser(String facebookId) {
		EntityManager em = EntityManagerManager.get();
		TypedQuery<User> query = em.createNamedQuery("findUserByFacebookId", User.class);
		query.setParameter("facebookId", facebookId);
		List<User> results = query.getResultList();
		if (results.size() == 0) {
			User user = new User();
			em.persist(user);
			user.setFacebookId(facebookId);
			return user;
		} else {
			return results.get(0);
		}
	}

}
