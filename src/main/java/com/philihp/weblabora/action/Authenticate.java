package com.philihp.weblabora.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.FacebookApi;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;

import com.philihp.weblabora.jpa.User;

public class Authenticate extends BaseAction {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response, User user)
			throws Exception {

		String clientId = (String)getServlet().getServletContext().getAttribute("client_id");
		String clientSecret = (String)getServlet().getServletContext().getAttribute("client_secret");
		String redirectURI = (String)getServlet().getServletContext().getAttribute("redirect_uri");
		
		OAuthService service = new ServiceBuilder().provider(FacebookApi.class)
				.apiKey(clientId).apiSecret(clientSecret)
				.callback(redirectURI).build();
		
		String verifier = request.getParameter("code");
		
		if(verifier == null) {
			String authURL = service.getAuthorizationUrl(null);
			System.out.println("Authorization URL: "+authURL);
			return new ActionForward(authURL, true);
		}
		else {
			Token accessToken = service.getAccessToken((Token)null, new Verifier(verifier));
			OAuthRequest authRequest = new OAuthRequest(Verb.GET, "https://graph.facebook.com/me");
			service.signRequest(accessToken, authRequest);
			Response authResponse = authRequest.send();
			
			request.setAttribute("me.json", authResponse.getBody());
			
			return mapping.findForward("getInfo");
		}
	}
}
