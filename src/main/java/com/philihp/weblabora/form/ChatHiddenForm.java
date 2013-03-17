package com.philihp.weblabora.form;

import org.apache.struts.action.ActionForm;

public class ChatHiddenForm extends ActionForm {
	
	private boolean chatHidden;
	
	public boolean isChatHidden() {
		return chatHidden;
	}

	public void setChatHidden(boolean chatHidden) {
		this.chatHidden = chatHidden;
	}

	public void reset() {
		chatHidden=false;
	}

}
