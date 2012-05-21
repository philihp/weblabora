package com.philihp.weblabora.action;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
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
import com.philihp.weblabora.util.EntityManagerManager;
import com.philihp.weblabora.util.FacebookSignedRequest;
import com.philihp.weblabora.util.FacebookSignedRequestDeserializer;
import com.philihp.weblabora.util.FacebookUtil;


abstract public class BaseAction extends Action {
	
	@SuppressWarnings("unchecked")
	private static final Set<Object> PUBLIC_ACTIONS = new HashSet<Object>(Arrays.asList(Authenticate.class, AuthenticateGetInfo.class, Offline.class));
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		System.out.println("Action: "+this.getClass().getCanonicalName());

		User user = null;
		
		//first check signed_request and see if we can find user from that
		if(request.getMethod().equals("POST") && request.getParameter("signed_request") != null) {
			String signedRequest = request.getParameter("signed_request");
			String clientSecret = (String)getServlet().getServletContext().getAttribute("client_secret");
			user = findUserFromSignedRequest(signedRequest, clientSecret);

			if(user != null) 
				request.getSession().setAttribute("user", user);
		}

		//if no user yet, check the session
		if(user == null) {
			user = findUser((User)request.getSession().getAttribute("user"));
			
			if(user != null)
				request.getSession().setAttribute("user", user);
		}
		
		//if still no user, restart authentication process
		if(user == null && isActionPrivate()) {
			throw new AuthenticationException();
		}
			
		return execute(mapping, actionForm, request, response, user);
	}
	
	private User findUserFromSignedRequest(String signedRequest, String clientSecret) throws AuthenticationException {
		String[] segments = signedRequest.split("[.]", 2);
		String givenSignature = segments[0];
		String payload = segments[1];

		if(false == FacebookUtil.isValidSignedRequest(clientSecret, givenSignature, payload)) {
			throw new AuthenticationException();
		}
		
		payload = payload.replace("-","+").replace("_","/").trim();
		String jsonString = new String(Base64.decodeBase64(payload));

		Gson gson = new GsonBuilder().registerTypeAdapter(
				FacebookSignedRequest.class,
				new FacebookSignedRequestDeserializer()).create();
		FacebookSignedRequest fsr = gson.fromJson(jsonString, FacebookSignedRequest.class);

		if (fsr.getUserId() == null)
			return null;
		else
			return findUser(fsr.getUserId());
	}

	abstract ActionForward execute(ActionMapping mapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response, User user)
			throws AuthenticationException, Exception;

	private boolean isActionPrivate() {
		return PUBLIC_ACTIONS.contains(this.getClass()) == false;
	}
	
	public static User findUser(User user) {
		if(user == null) return null;
		return findUser(user.getFacebookId());
	}
	
	public static User findUser(String facebookId) {
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
