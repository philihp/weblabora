package com.philihp.weblabora.form;

import org.apache.struts.validator.ValidatorForm;

public class AccountForm extends ValidatorForm {

	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
