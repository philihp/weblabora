package com.philihp.weblabora.form;

import org.apache.struts.validator.ValidatorForm;

public class AccountForm extends ValidatorForm {

	private String email;
	private String username;
	

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
