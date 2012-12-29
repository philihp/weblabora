package com.philihp.weblabora.action;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.philihp.weblabora.jpa.Game;
import com.philihp.weblabora.jpa.User;

public class ShowLobby extends BaseAction {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response, User user) throws Exception {
		EntityManager em = (EntityManager)request.getAttribute("em");

		request.setAttribute("myGames", findGamesForUser(em, user));
		request.setAttribute("recruitingGames", findGamesOfStageWithoutUser(em, user, Game.Stage.RECRUITING));
		request.setAttribute("inProgressGames", findGamesOfStageWithoutUser(em, user, Game.Stage.IN_PROGRESS));
		request.setAttribute("finishedGames", findGamesOfStage(em, Game.Stage.FINISHED));
		
		return mapping.findForward("view");
	}

	private static List<Game> findGamesOfStageWithoutUser(EntityManager em, User user, Game.Stage stage) {
		if(user == null) {
			user = new User();
		}
		TypedQuery<Game> query = em.createQuery("SELECT g FROM Game g " +
				"WHERE g.stage = :state " +
				"  AND NOT ((g.player1.user IS NOT NULL AND g.player1.user = :user) " +
				"        OR (g.player2.user IS NOT NULL AND g.player2.user = :user) " +
				"        OR (g.player3.user IS NOT NULL AND g.player3.user = :user) " +
				"        OR (g.player4.user IS NOT NULL AND g.player4.user = :user)) " +
				"ORDER BY g.gameId", Game.class);
		query.setParameter("user", user);
		query.setParameter("state", stage);
		List<Game> results = query.getResultList();
		return results;
	}

	private static List<Game> findGamesOfStage(EntityManager em, Game.Stage stage) {
		TypedQuery<Game> query = em.createQuery("SELECT g FROM Game g " +
				"WHERE g.stage = :state " +
				"ORDER BY g.gameId", Game.class);
		query.setParameter("state", stage);
		List<Game> results = query.getResultList();
		return results;
	}

	protected static List<Game> findGamesForUser(EntityManager em, User user) {
		TypedQuery<Game> query = em
				.createQuery(
						"SELECT g " +
						"FROM Game g " +
						"WHERE (g.player1.user = :user " +
						   "OR g.player2.user = :user " +
						   "OR g.player3.user = :user " +
						   "OR g.player4.user = :user " +
						   ") AND g.stage != :stage " +
						"ORDER BY g.gameId",
						Game.class);
		query.setParameter("user", user);
		query.setParameter("stage", Game.Stage.FINISHED);
		
		List<Game> results = query.getResultList();
		return results;
	}
}
