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
import java.util.List;

import javax.net.ssl.HttpsURLConnection;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import antlr.StringUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.philihp.weblabora.jpa.User;
import com.philihp.weblabora.util.EntityManagerManager;
import com.philihp.weblabora.util.Facebook;
import com.philihp.weblabora.util.FacebookCredentials;
import com.philihp.weblabora.util.FacebookCredentialsDeserializer;

public class AuthenticateGetInfo extends BaseAction {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response, User user) throws AuthenticationException, Exception {

		String meJson = (String)request.getAttribute("me.json");
		if(meJson == null) throw new AuthenticationException();

		Gson gson = new GsonBuilder().registerTypeAdapter(
				FacebookCredentials.class,
				new FacebookCredentialsDeserializer()).create();
		FacebookCredentials credentials = gson.fromJson(meJson, FacebookCredentials.class);

		user = findUser(credentials.getFacebookId());
		user.setName(credentials.getName());
		request.getSession().setAttribute("user", user);

		return mapping.findForward("root");

	}

}
