package com.philihp.weblabora.action;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.philihp.weblabora.form.GameForm;
import com.philihp.weblabora.jpa.Game;
import com.philihp.weblabora.jpa.State;
import com.philihp.weblabora.jpa.User;
import com.philihp.weblabora.model.Board;
import com.philihp.weblabora.model.GameCountry;
import com.philihp.weblabora.model.GameLength;
import com.philihp.weblabora.model.GamePlayers;
import com.philihp.weblabora.model.MoveProcessor;
import com.philihp.weblabora.model.WeblaboraException;

public class ShowGameState extends BaseAction {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response, User user) throws Exception {

		GameForm form = (GameForm)actionForm;
		EntityManager em = (EntityManager)request.getAttribute("em");
		
		request.setAttribute("myGames", ShowGame.findGamesForUser(em, user));
		
		Game game = (Game)request.getAttribute("game");
		
		request.setAttribute("board", prepareBoard(game, form.getStateId()));
		request.setAttribute("savedMove", findSavedMove(game, user)); 
		
		return mapping.findForward("view");
	}

	protected static Board prepareBoard(Game game, Integer endState) throws WeblaboraException {
		Board board = null;
		if(game != null) {
			board = new Board(
					GamePlayers.valueOf(game.getPlayers()),
					GameLength.valueOf(game.getLength()),
					GameCountry.valueOf(game.getCountry())
					);
			board.populateDetails(game);
			MoveProcessor.processMoves(board, game.getStates(), endState);
			if(board.isGameOver() == false)
				board.preMove(new State()); //upkeep stuff before player makes a move	
		}
		return board;
	}

	
	protected static String findSavedMove(Game game, User user) {
		Game.Player player = ShowGame.findPlayerInGame(game, user);
		if(player == null) return null;
		return player.getMove();
	}
}
