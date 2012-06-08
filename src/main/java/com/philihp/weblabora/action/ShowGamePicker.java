package com.philihp.weblabora.action;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.philihp.weblabora.jpa.User;

public class ShowGamePicker extends BaseAction {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response, User user) throws Exception {
		EntityManager em = (EntityManager)request.getAttribute("em");

		request.setAttribute("myGames", ShowGame.findGamesForUser(em, user));
		
		return mapping.findForward("view");
	}

}
