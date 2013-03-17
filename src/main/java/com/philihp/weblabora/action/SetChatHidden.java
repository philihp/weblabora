package com.philihp.weblabora.action;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.philihp.weblabora.form.ChatHiddenForm;
import com.philihp.weblabora.form.GameForm;
import com.philihp.weblabora.jpa.Game;
import com.philihp.weblabora.jpa.Game.Stage;
import com.philihp.weblabora.jpa.User;

public class SetChatHidden extends BaseAction {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response, User user) throws Exception {

		ChatHiddenForm form = (ChatHiddenForm)actionForm;
		
		user.setChatHidden(form.isChatHidden());
		
		System.out.println("Set Chat Hidden" + form.isChatHidden());
		
		return null;
	}

}
