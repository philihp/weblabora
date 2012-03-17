package com.philihp.weblabora.form;

import org.apache.struts.action.ActionForm;

public class MoveForm extends GameForm {

	/*
	 * TODO: make token required by making this a validator form
	 */
	private String token;

	public void reset() {
		super.reset();
		this.token = null;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
