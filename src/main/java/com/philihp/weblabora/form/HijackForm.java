package com.philihp.weblabora.form;

import org.apache.struts.action.ActionForm;

public class HijackForm extends ActionForm {
	
	private String facebookId;
	private String password;
	
	public void reset() {
		this.facebookId = null;
		this.password = null;
	}

	public String getFacebookId() {
		return facebookId;
	}

	public void setFacebookId(String facebookId) {
		this.facebookId = facebookId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
