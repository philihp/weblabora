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
import com.philihp.weblabora.util.Facebook;
import com.philihp.weblabora.util.FacebookCredentials;
import com.philihp.weblabora.util.FacebookCredentialsDeserializer;

public class AuthenticateGetInfo extends BaseAction {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response, FacebookCredentials credentials) throws AuthenticationException, Exception {

		String accessToken = (String) request.getSession().getAttribute("accessToken");
		if (accessToken == null)
			throw new AuthenticationException();

		URL url = new URL("https://graph.facebook.com/me/?access_token=" + accessToken);
		HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
		if (connection.getResponseCode() == 400)
			throw new AuthenticationException();

		Gson gson = new GsonBuilder().registerTypeAdapter(FacebookCredentials.class, new FacebookCredentialsDeserializer())
				.create();
		credentials = gson.fromJson(new InputStreamReader(connection.getInputStream()),
				FacebookCredentials.class);

		request.getSession().setAttribute("facebook", credentials);

		return mapping.findForward("default");

	}
}
