package com.philihp.weblabora.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.philihp.weblabora.jpa.Game;
import com.philihp.weblabora.jpa.User;

public class LeaveGame extends BaseAction {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response, User user) throws Exception {
		
		Game game = user.getActiveGame();
		for(Game.Player player : game.getAllPlayers()) {
			if(user.equals(player.getUser())) {
				player.setUser(null);
			}
		}
		user.setActiveGame(null);
		
		return mapping.findForward("root");
	}

}
