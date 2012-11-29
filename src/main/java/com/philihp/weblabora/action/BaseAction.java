package com.philihp.weblabora.action;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.philihp.weblabora.jpa.User;
import com.philihp.weblabora.util.AuthenticationException;
import com.philihp.weblabora.util.FacebookSignedRequest;
import com.philihp.weblabora.util.FacebookSignedRequestDeserializer;
import com.philihp.weblabora.util.FacebookUtil;


abstract public class BaseAction extends Action {

	protected static final int COOKIE_EXPIRES = 2592000; //30 days
	protected static final String COOKIE_KEY = "weblabora-login";
	
	@SuppressWarnings("unchecked")
	private static final Set<Object> PUBLIC_ACTIONS = new HashSet<Object>(Arrays.asList(ShowGame.class, ShowGameState.class, ShowLobby.class, Offline.class, Login.class));
	
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
			user = Login.getUserFromFingerprint(em, request);
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
	                User user = query.getSingleResult();
	                request.getSession().setAttribute("user", user);
	                return user;
	            }
	        }
	    }
	    return null;
	}
}
