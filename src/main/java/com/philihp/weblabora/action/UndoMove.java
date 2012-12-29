package com.philihp.weblabora.action;

import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.philihp.weblabora.form.GameForm;
import com.philihp.weblabora.jpa.Game;
import com.philihp.weblabora.jpa.State;
import com.philihp.weblabora.jpa.User;
import com.philihp.weblabora.model.WeblaboraException;

public class UndoMove extends BaseAction {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response, User user) throws Exception {
		GameForm form = (GameForm) actionForm;
		EntityManager em = (EntityManager)request.getAttribute("em");

		Game game = em.find(Game.class, form.getGameId());

		int gameCurrentStateId = game.getState().getStateId();
		if(gameCurrentStateId != form.getStateId()) {
			ActionMessages messages = getMessages(request);
			messages.add(
					ActionMessages.GLOBAL_MESSAGE,
					new ActionMessage(
							"message.cantUndo",form.getStateId(), form.getGameId(), gameCurrentStateId));
			
			saveMessages(request.getSession(), messages);
			return mapping.findForward("root"); 
		}
		else {
			game.getState().setActive(false);
			game.updateTimeStamps();
		
			ActionForward forward = mapping.findForward("undone");
			forward = new ActionForward(forward.getPath()+"?gameId="+game.getGameId(), forward.getRedirect());
			return forward;
		}
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
