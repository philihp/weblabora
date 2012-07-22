package com.philihp.weblabora.action;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.philihp.weblabora.form.GameForm;
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

public class UndoMove extends BaseAction {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response, User user) throws Exception {
		GameForm form = (GameForm) actionForm;
		EntityManager em = (EntityManager)request.getAttribute("em");

		TypedQuery<Game> query = em.createQuery("SELECT g FROM Game g WHERE g.gameId = :gameId", Game.class);
		query.setParameter("gameId", form.getGameId());
		Game game = query.getSingleResult();
		
		if(game.getState().getStateId() != form.getStateId()) {
			return mapping.findForward("root");
		}
		
		/*
		if(game.isUndoable() == false) {
			throw new WeblaboraException("More than 24 hours has elapsed since that move has been made.");
		}
		else if (game.getState().getExplorer().equals(user) == false) {
			throw new WeblaboraException("Only the user who made a move can undo it");
		}
		else {
			game.setState(game.getState().getSrcState());
		}
		*/

		game.setState(game.getState().getSrcState());
		
		return mapping.findForward("madeMove");
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
