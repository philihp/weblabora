package com.philihp.weblabora.form;

import org.apache.struts.validator.ValidatorForm;

public class ForgotPasswordForm extends ValidatorForm {

	private String username;
	private String email;
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
