package com.philihp.weblabora.form;

import org.apache.struts.validator.ValidatorForm;

public class LoginForm extends ValidatorForm {
	
	private String username;
	private String password;
	private String referer;
	private String validator;
	
	public void reset() {
		this.username = null;
		this.password = null;
		this.referer = null;
		this.validator = null;
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

	public String getValidator() {
		return validator;
	}

	public void setValidator(String validator) {
		this.validator = validator;
	}

}
