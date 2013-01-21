package com.philihp.weblabora.action;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.philihp.weblabora.jpa.Fingerprint;
import com.philihp.weblabora.jpa.User;
import com.philihp.weblabora.util.AuthenticationException;


abstract public class BaseAction extends Action {

	protected static final int COOKIE_EXPIRES = 2592000; //30 days
	protected static final String COOKIE_KEY = "weblabora-login";
	
	@SuppressWarnings("unchecked")
	private static final Set<Object> PUBLIC_ACTIONS = new HashSet<Object>(Arrays.asList(ShowGame.class, ShowGameState.class,
			ShowLobby.class, LoginSubmit.class, Register.class, RegisterSubmit.class, RegisterValidate.class,
			Login.class, ForgotPasswordSubmit.class, ResetPassword.class, ResetPasswordSubmit.class, LoginFacebook.class));
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		System.out.println("Action: "+this.getClass().getCanonicalName());

		EntityManager em = (EntityManager)request.getAttribute("em");
		
		User user = (User)request.getSession().getAttribute("user");
		if(user != null) {
			user = em.merge(user);
			request.getSession().setAttribute("user", user);
		}
		else {
			user = LoginSubmit.getUserFromFingerprint(em, request);
		}
		
		//if still no user, restart authentication process
		if(user == null && isActionPrivate()) {
			throw new AuthenticationException();
		}
		
		return execute(mapping, actionForm, request, response, user);
	}

	abstract ActionForward execute(ActionMapping mapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response, User user)
			throws AuthenticationException, Exception;

	private boolean isActionPrivate() {
		return PUBLIC_ACTIONS.contains(this.getClass()) == false;
	}

	protected static User getUserFromFingerprint(EntityManager em, HttpServletRequest request) {
	    Cookie[] cookies = request.getCookies();
	    if (cookies != null) {
	        for (Cookie cookie : cookies) {
	            if (COOKIE_KEY.equals(cookie.getName())) {
	                String uuid = cookie.getValue();
	                TypedQuery<User> query = em.createQuery("SELECT f.user FROM Fingerprint f WHERE f.uuid = :uuid", User.class);
	                query.setParameter("uuid", uuid);
	                
	                List<User> results = query.getResultList();
	                User user = null;
	                if(results.size() == 1) {
	                	user = results.get(0);
		                request.getSession().setAttribute("user", user);
	                }
	                return user;
	            }
	        }
	    }
	    return null;
	}

	protected void saveUserFingerprint(EntityManager em, HttpServletResponse response,
			User user) {
		Fingerprint loginToken = new Fingerprint();
		loginToken.setUser(user);
		loginToken.setUuid(UUID.randomUUID().toString());
		em.persist(loginToken);
		Cookie cookie = new Cookie(COOKIE_KEY, loginToken.getUuid());
		cookie.setMaxAge(COOKIE_EXPIRES);
		cookie.setPath(this.getServlet().getServletContext().getContextPath());
		response.addCookie(cookie);
	}
}
