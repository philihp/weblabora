package com.philihp.weblabora.action;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.servlet.http.*;

import org.apache.struts.action.*;
import org.apache.struts.validator.DynaValidatorForm;

import com.google.gson.*;
import com.philihp.weblabora.form.RegisterForm;
import com.philihp.weblabora.jpa.User;
import com.philihp.weblabora.util.FacebookCredentials;
import com.philihp.weblabora.util.FacebookCredentialsDeserializer;
import com.philihp.weblabora.util.FacebookUtil;

import org.scribe.builder.*;
import org.scribe.builder.api.*;
import org.scribe.exceptions.OAuthException;
import org.scribe.model.*;
import org.scribe.oauth.*;

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

				TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.facebookId = :facebookId", User.class);
				query.setParameter("facebookId", credentials.getFacebookId());
				List<User> results = query.getResultList();
				
				if(results.size() == 0) {
					if(user == null) {
						RegisterForm form = (RegisterForm)actionForm;
						form.setEmail(credentials.getEmail());
						form.setUsername(credentials.getUsername());
						request.getSession().setAttribute(FacebookUtil.FACEBOOK_ID, credentials.getFacebookId());
						ActionMessages messages = getMessages(request);
						messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.detail","The form has been populated with your Facebook information. Please check and make sure it is still accurate."));
						saveMessages(request, messages);
						return mapping.findForward("register");
					}
					else {
						user.setFacebookId(credentials.getFacebookId());
						return mapping.findForward("account");
					}
				}
				else {
					user = results.get(0);
					user.setEmail(credentials.getEmail());
					request.getSession().setAttribute("user", user);
					saveUserFingerprint(em, response, user);	
					return mapping.findForward("root");
				}
			}
			catch(OAuthException e) {
				ActionMessages errors = getErrors(request);
				errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.detail", "Please try again. The Facebook API returned this error: "+e.getLocalizedMessage()));
				saveErrors(request, errors);
				return mapping.findForward("error");
			}
		}
	}
}