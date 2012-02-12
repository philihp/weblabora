package com.philihp.weblabora.form;

import org.apache.struts.action.ActionForm;

public class HijackForm extends ActionForm {
	
	private String facebookId;
	
	public void reset() {
		this.facebookId = null;
	}

	public String getFacebookId() {
		return facebookId;
	}

	public void setFacebookId(String facebookId) {
		this.facebookId = facebookId;
	}

}
