package com.philihp.weblabora.action;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
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
import com.philihp.weblabora.model.WeblaboraException;
import com.philihp.weblabora.util.EntityManagerManager;
import com.philihp.weblabora.util.FacebookCredentials;

public class ShowBoard extends BaseAction {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response, User user) throws Exception {

		GameForm form = (GameForm)actionForm;
		if(form.getGameId() == null) {
			return mapping.findForward("no-game");
		}
		
		request.setAttribute("myGames", findGamesForUser(user));
		
		Game game = findGame(form.getGameId());
		user.setActiveGame(game);
		request.setAttribute("game", game);
		request.setAttribute("board", prepareBoard(game));
		
		return mapping.findForward("view");
	}
	
	protected static Board prepareBoard(Game game) throws WeblaboraException {
		Board board = null;
		if(game != null) {
			board = new Board();
			board.populateDetails(game);
			MoveProcessor.processMoves(board, game.getMoves());
			if(board.isGameOver() == false)
				board.preMove("..."); //upkeep stuff before player makes a move	
		}
		return board;
	}
	
	private Game findGame(int gameId) {
		EntityManager em = EntityManagerManager.get();
		return em.find(Game.class, gameId);
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
