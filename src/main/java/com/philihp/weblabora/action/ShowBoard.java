package com.philihp.weblabora.action;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.philihp.weblabora.form.GameForm;
import com.philihp.weblabora.jpa.Game;
import com.philihp.weblabora.jpa.User;
import com.philihp.weblabora.model.Board;
import com.philihp.weblabora.util.EntityManagerManager;
import com.philihp.weblabora.util.FacebookCredentials;

public class ShowBoard extends BaseAction {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response, User user) throws Exception {
		GameForm form = (GameForm)actionForm;
		
		//since the SetActiveGame.do action uses the same form bean, and it's kept in the session scope, we can do this to prepop the form
		Game activeGame = user.getActiveGame();
		if(activeGame != null) form.setGameId(activeGame.getGameId());

		request.setAttribute("myGames", findGamesForUser(user));

		return mapping.findForward("default");
	}

	private List<Game> findGamesForUser(User user) {
		EntityManager em = EntityManagerManager.get();
		TypedQuery<Game> query = em
				.createQuery(
						"SELECT g " +
						"FROM Game g " +
						"WHERE g.player1.user = :user " +
						   "OR g.player2.user = :user " +
						   "OR g.player3.user = :user " +
						   "OR g.player4.user = :user",
						Game.class);
		query.setParameter("user", user);
		List<Game> results = query.getResultList();
		return results;
	}

}
