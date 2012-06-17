package com.philihp.weblabora.action;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.philihp.weblabora.form.GameForm;
import com.philihp.weblabora.jpa.Game;
import com.philihp.weblabora.jpa.User;

public class ShowGame extends BaseAction {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response, User user) throws Exception {
		EntityManager em = (EntityManager)request.getAttribute("em");

		GameForm form = (GameForm)actionForm;
		if(form.getGameId() == null) {
			return mapping.findForward("no-game");
		}
		
		Game game = findGame(em, form.getGameId());
		user.setActiveGame(game);
		request.setAttribute("game", game);
		
		if(form.getStateId() == null) {
			form.setStateId(game.getState().getStateId());
		}
		
		return mapping.findForward("game-state");
	}

	protected static Game.Player findPlayerInGame(Game game, User user) {
		if(user == null) return null;
		String facebookId = user.getFacebookId();
		if(facebookId == null) return null;
		for(Game.Player player : game.getAllPlayers()) {
			if(player != null && player.getUser() != null && player.getUser().getFacebookId() != null && player.getUser().getFacebookId().equals(facebookId)) {
				return player;
			}
		}
		return null;
	}

	protected static Game findGame(EntityManager em, int gameId) {
		return em.find(Game.class, gameId);
	}

	protected static List<Game> findGamesForUser(EntityManager em, User user) {
		TypedQuery<Game> query = em
				.createQuery(
						"SELECT g " +
						"FROM Game g " +
						"WHERE g.player1.user = :user " +
						   "OR g.player2.user = :user " +
						   "OR g.player3.user = :user " +
						   "OR g.player4.user = :user " +
						"ORDER BY g.gameId",
						Game.class);
		query.setParameter("user", user);
		
		List<Game> results = query.getResultList();
		return results;
	}

}
