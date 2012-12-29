package com.philihp.weblabora.action;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.philihp.weblabora.form.MoveForm;
import com.philihp.weblabora.jpa.Game;
import com.philihp.weblabora.jpa.State;
import com.philihp.weblabora.jpa.User;
import com.philihp.weblabora.model.Board;
import com.philihp.weblabora.model.GameCountry;
import com.philihp.weblabora.model.GameLength;
import com.philihp.weblabora.model.GamePlayers;
import com.philihp.weblabora.model.MoveProcessor;
import com.philihp.weblabora.model.WeblaboraException;

public class MakeMove extends BaseAction {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response, User user) throws Exception {
		MoveForm form = (MoveForm) actionForm;
		EntityManager em = (EntityManager)request.getAttribute("em");

		String submit = request.getParameter("submit");
		if(submit != null && submit.equalsIgnoreCase("Explore") == false) return mapping.findForward("save");

		TypedQuery<Game> query = em.createQuery("SELECT g FROM Game g WHERE g.gameId = :gameId", Game.class);
		query.setParameter("gameId", form.getGameId());
		Game game = query.getSingleResult();
		
		if(game.getState().getStateId() != form.getStateId()) {
			ActionMessages messages = getMessages(request);
			messages.add(
					ActionMessages.GLOBAL_MESSAGE,
					new ActionMessage(
							"message.badState",form.getStateId(),game.getState().getStateId()));
			

			saveMessages(request.getSession(), messages);
			return calculateGameForward(mapping, game);
		}
		
		Board board = new Board(
				GamePlayers.valueOf(game.getPlayers()),
				GameLength.valueOf(game.getLength()),
				GameCountry.valueOf(game.getCountry())
				);
		MoveProcessor.processMoves(board, game.getActiveStates(), null);
		try {
			if(Arrays.asList(game.getAllUsers()).contains(user) == false)
				throw new WeblaboraException("User "+user+" is not one of the players in game "+game.getGameId()); 
			
			if(board.isGameOver())
				throw new WeblaboraException("Game has already ended.");
			
			board.preMove(new State());
			MoveProcessor.processActions(board, form.getToken());
			board.testValidity();
		}
		catch(WeblaboraException e) {
			request.setAttribute("error", e);
			request.setAttribute("game", game);
			request.setAttribute("token", form.getToken());
			return mapping.findForward("badMove");
		}
		
		State state = searchForExploredState(game, form.getToken());
		if (state != null) {
			// all of this has happened before. all of this will happen again.
			state.setActive(true);
		} else {
			state = new State();
			state.setToken(form.getToken());
			state.setSrcState(game.getState());
			state.getSrcState().getDstStates().add(state); //needed for bidirectional relationships
			state.setExplorer(user);
			state.setGame(game);
			state.setActive(true);
			em.persist(state); //dstStates isn't updated automatically
			game.getStates().add(state);

			Game.Player player = ShowGame.findPlayerInGame(game, user);
			if(player != null) {
				player.setMove("");
			}
		}
		game.updateTimeStamps();
		
		
		return calculateGameForward(mapping, game);
	}
	
	private ActionForward calculateGameForward(ActionMapping mapping, Game game) {
		ActionForward forward = mapping.findForward("show-game");
		String path = forward.getPath()+"?gameId="+game.getGameId();
		return new ActionForward(forward.getName(), path, forward.getRedirect(), forward.getModule());
	}

	protected State searchForExploredState(Game game, String token) throws WeblaboraException {
		List<State> possibleStates = game.getState().getDstStates();
		for (State state : possibleStates) {
			if (token.equals(state.getToken()))
				return state;
		}
		return null;

	}

}
