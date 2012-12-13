package com.philihp.weblabora.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.validator.ValidatorForm;

public class RegisterForm extends ValidatorForm {

	private String username;
	private String password;
	private String email;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		ActionErrors errors = super.validate(mapping, request);
		
		String facebookId = (String)request.getSession().getAttribute("FACEBOOK_ID");
		if(facebookId == null) {
			// validate password only if user isn't going to be linked to a Facebook account
			if(password == null) {
				errors.add("password", new ActionMessage("errors.required","password"));
			}
			else if(password.length() < 5) {
				errors.add("password", new ActionMessage("errors.minlength","password",5));
			}
		}
		
		return errors;
	}
	
	

}
