package com.philihp.weblabora.form;

import org.apache.struts.action.ActionForm;

public class LoginForm extends ActionForm {
	
	private String username;
	private String password;
	private String referer;
	
	public void reset() {
		this.username = null;
		this.password = null;
	}

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

	public String getReferer() {
		return referer;
	}

	public void setReferer(String referer) {
		this.referer = referer;
	}

}
