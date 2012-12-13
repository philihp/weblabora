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
import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.FacebookApi;
import org.scribe.exceptions.OAuthException;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.philihp.weblabora.form.RegisterForm;
import com.philihp.weblabora.jpa.User;
import com.philihp.weblabora.util.FacebookCredentials;
import com.philihp.weblabora.util.FacebookCredentialsDeserializer;
import com.philihp.weblabora.util.FacebookUtil;

public class LoginFacebook extends BaseAction {

	public ActionForward execute(ActionMapping mapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response, User user)
			throws Exception {
		EntityManager em = (EntityManager)request.getAttribute("em");

		String clientId = (String) getServlet().getServletContext().getAttribute("client_id");
		String clientSecret = (String) getServlet().getServletContext().getAttribute("client_secret");
		String redirectURI = (String) getServlet().getServletContext().getAttribute("redirect_uri");

		OAuthService service = new ServiceBuilder().provider(FacebookApi.class)
				.apiKey(clientId)
				.apiSecret(clientSecret)
				.callback(redirectURI)
				.scope("email")
				.build();

		String verifierCode = request.getParameter("code");

		if (verifierCode == null) {
			String authURL = service.getAuthorizationUrl(null);
			return new ActionForward(authURL, true);
		} else {
			try {
				Token accessToken = service.getAccessToken((Token) null, new Verifier(verifierCode));
				OAuthRequest authRequest = new OAuthRequest(Verb.GET, "https://graph.facebook.com/me");
				service.signRequest(accessToken, authRequest);
				Response authResponse = authRequest.send();
	
				String jsonData = authResponse.getBody();
				
				Gson gson = new GsonBuilder().registerTypeAdapter(FacebookCredentials.class, new FacebookCredentialsDeserializer()).create();
				FacebookCredentials credentials = gson.fromJson(jsonData, FacebookCredentials.class);

				TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.facebookId = :facebookId OR u.email = :email", User.class);
				query.setParameter("facebookId", credentials.getFacebookId());
				query.setParameter("email", credentials.getEmail());
				List<User> results = query.getResultList();
				
				boolean alreadySignedIn = (user != null);

				ActionMessages messages = getMessages(request);
				if(results.size() == 0) {
					if(alreadySignedIn) {
						//user was already logged in -- they are trying to link their facebook, and the emails on facebook
						//and the email we have are different.... this is rare
						user.setFacebookId(credentials.getFacebookId());
						messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.facebookLinked"));
						saveMessages(request.getSession(), messages);
						return mapping.findForward("account");
					}
					else {
						//user is trying to sign up with facebook, and we don't know that facebook user's email
						RegisterForm form = (RegisterForm)actionForm;
						form.setEmail(credentials.getEmail());
						form.setUsername(credentials.getUsername());
						request.getSession().setAttribute(FacebookUtil.FACEBOOK_ID, credentials.getFacebookId());
						messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.facebookSignup"));
						saveMessages(request, messages);
						return mapping.findForward("register");
					}
				}
				else {
					user = results.get(0);
					//System.out.println("OLD EMAIL = "+user.getEmail());
					//System.out.println("NEW EMAIL = "+credentials.getEmail());
					//user.setEmail(credentials.getEmail());
					user.setFacebookId(credentials.getFacebookId());
					request.getSession().setAttribute("user", user);
					saveUserFingerprint(em, response, user);
					if(alreadySignedIn) {
						messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.facebookLinked"));
						saveMessages(request.getSession(), messages);
						return mapping.findForward("account");
					}
					else {
						return mapping.findForward("root");
					}
				}
			}
			catch(OAuthException e) {
				ActionMessages errors = getErrors(request);
				errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.facebookError",e.getLocalizedMessage()));
				saveErrors(request, errors);
				return mapping.findForward("error");
			}
		}
	}
}