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
import com.philihp.weblabora.model.MoveProcessor;
import com.philihp.weblabora.util.EntityManagerManager;
import com.philihp.weblabora.util.FacebookCredentials;

public class ShowBoard extends BaseAction {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response, User user) throws Exception {

		request.setAttribute("myGames", findGamesForUser(user));
		request.setAttribute("game", user.getActiveGame());
		
		Board board = null;
		if(user.getActiveGame() != null) {
			board = new Board();
			board.populateDetails(user.getActiveGame());
			MoveProcessor.processMoves(board, user.getActiveGame().getMoves());
			request.setAttribute("board", board);
		}
		board.preMove(); //upkeep stuff before player makes a move	
		
		return mapping.findForward("view");
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
