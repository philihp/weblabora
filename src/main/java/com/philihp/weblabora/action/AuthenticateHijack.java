package com.philihp.weblabora.action;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import antlr.StringUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.philihp.weblabora.form.HijackForm;
import com.philihp.weblabora.jpa.User;
import com.philihp.weblabora.util.Facebook;
import com.philihp.weblabora.util.FacebookCredentials;
import com.philihp.weblabora.util.FacebookCredentialsDeserializer;

public class AuthenticateHijack extends BaseAction {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response, User user) throws AuthenticationException, Exception {

		String accessToken = (String) request.getSession().getAttribute("accessToken");
		if (accessToken == null)
			throw new AuthenticationException();

		HijackForm form = (HijackForm)actionForm;
		if(form.getFacebookId() == null) form.setFacebookId(user.getFacebookId());

		URL url = new URL("https://graph.facebook.com/"+form.getFacebookId()+"/?access_token=" + accessToken);
		HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
		if (connection.getResponseCode() == 400)
			throw new AuthenticationException();

		Gson gson = new GsonBuilder().registerTypeAdapter(FacebookCredentials.class, new FacebookCredentialsDeserializer())
				.create();
		FacebookCredentials credentials = gson.fromJson(new InputStreamReader(connection.getInputStream()),
				FacebookCredentials.class);

		request.getSession().setAttribute("user", findUser(credentials));

		return mapping.findForward("default");

	}
}
