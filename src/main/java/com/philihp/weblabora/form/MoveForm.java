package com.philihp.weblabora.form;


public class MoveForm extends GameForm {

	/*
	 * TODO: make token required by making this a validator form
	 */
	private String token;

	private Integer stateId;

	public Integer getStateId() {
		return stateId;
	}

	public void setStateId(Integer stateId) {
		this.stateId = stateId;
	}

	public void reset() {
		super.reset();
		this.token = null;
		this.stateId = null;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
