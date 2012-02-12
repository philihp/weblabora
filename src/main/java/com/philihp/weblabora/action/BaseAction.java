package com.philihp.weblabora.action;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.philihp.weblabora.util.Facebook;
import com.philihp.weblabora.util.FacebookCredentials;


abstract class BaseAction extends Action {
	
	private static final Set<Object> PUBLIC_ACTIONS = new HashSet<Object>(Arrays.asList(Authenticate.class, AuthenticateGetInfo.class));
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		FacebookCredentials credentials = (FacebookCredentials)request.getSession().getAttribute("facebook");
		if(isActionPrivate() && credentials == null) throw new AuthenticationException();

		System.out.println("Action: "+this.getClass().getCanonicalName());

		return execute(mapping, form, request, response, credentials);
	}
	
	abstract ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response, FacebookCredentials credentials)
			throws AuthenticationException, Exception;

	protected String readURL(URL url) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		InputStream is = url.openStream();
		int r;
		while ((r = is.read()) != -1) {
			baos.write(r);
		}
		return new String(baos.toByteArray());
	}

	private boolean isActionPrivate() {
		return PUBLIC_ACTIONS.contains(this.getClass()) == false;
	}

}
