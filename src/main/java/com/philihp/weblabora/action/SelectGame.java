package com.philihp.weblabora.action;

import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.philihp.weblabora.jpa.Game;
import com.philihp.weblabora.jpa.User;

public class SelectGame extends BaseAction {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response, User user) throws Exception {

		ActionForward forward = mapping.findForward("show-board");
		EntityManager em = (EntityManager)request.getAttribute("em");
		
		Integer gameId = user.getActiveGameId();
		if(gameId == null) {
			List<Game> games = ShowGame.findGamesForUser(em, user);
			if(games.size() >= 1) {
				user.setActiveGame(games.get(0));
				gameId = user.getActiveGameId();
			}
			else {
				System.out.println("No Games");
				return mapping.findForward("no-games");
			}
		}
		
		ActionForward modifiedForward = new ActionForward(forward.getPath()+"?gameId="+gameId, forward.getRedirect());
		
		return modifiedForward;
	}
	
}
