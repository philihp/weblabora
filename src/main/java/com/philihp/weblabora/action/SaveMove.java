package com.philihp.weblabora.action;

import java.util.Arrays;
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
import com.philihp.weblabora.form.JoinGameForm;
import com.philihp.weblabora.form.MoveForm;
import com.philihp.weblabora.jpa.Game;
import com.philihp.weblabora.jpa.State;
import com.philihp.weblabora.jpa.User;
import com.philihp.weblabora.model.Board;
import com.philihp.weblabora.model.MoveProcessor;
import com.philihp.weblabora.model.WeblaboraException;
import com.philihp.weblabora.util.EntityManagerManager;
import com.philihp.weblabora.util.FacebookCredentials;

public class SaveMove extends BaseAction {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response, User user) throws Exception {
		MoveForm form = (MoveForm) actionForm;

		Game game = ShowGame.findGame(form.getGameId());
		Game.Player player = ShowGame.findPlayerInGame(game, user);
		if(player != null) {
			player.setMove(form.getToken());
		}
		
		return mapping.findForward("root");
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
