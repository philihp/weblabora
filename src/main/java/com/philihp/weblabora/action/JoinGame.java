package com.philihp.weblabora.action;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.philihp.weblabora.form.JoinGameForm;
import com.philihp.weblabora.jpa.Game;
import com.philihp.weblabora.jpa.User;

public class JoinGame extends BaseAction {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response, User user) throws Exception {
		JoinGameForm form = (JoinGameForm) actionForm;
		EntityManager em = (EntityManager)request.getAttribute("em");
		
		TypedQuery<Game> query = em.createQuery("SELECT g FROM Game g WHERE g.gameId = :gameId", Game.class);
		query.setParameter("gameId", form.getGameId());
		Game game = query.getSingleResult();
		
		for(Game.Player player : game.getAllPlayers()) {
			if(user.equals(player.getUser())) {
				player.setUser(null);
			}
		}

		game.getSeat(form.getSeat()).setUser(user);
		user.setActiveGame(game);
		
		return mapping.findForward("root");
	}

}
