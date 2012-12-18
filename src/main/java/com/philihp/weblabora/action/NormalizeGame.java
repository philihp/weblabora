package com.philihp.weblabora.action;

import java.util.List;
import java.util.Stack;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.philihp.weblabora.jpa.Game;
import com.philihp.weblabora.jpa.Game.Stage;
import com.philihp.weblabora.jpa.State;
import com.philihp.weblabora.jpa.User;
import com.philihp.weblabora.model.Board;
import com.philihp.weblabora.model.GameCountry;
import com.philihp.weblabora.model.GameLength;
import com.philihp.weblabora.model.GamePlayers;
import com.philihp.weblabora.model.MoveProcessor;
import com.philihp.weblabora.model.WeblaboraException;

public class NormalizeGame extends BaseAction {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response, User user) throws Exception {

		EntityManager em = (EntityManager)request.getAttribute("em");
		
		long startTime = System.nanoTime();
		
		TypedQuery<Game> query = em.createQuery("SELECT g FROM Game g", Game.class);
		List<Game> games = query.getResultList();
		for(Game game : games) {
			int moves = 0;
			System.out.println("Normalizing Game "+game.getGameId());
			Stack<State> states = new Stack<State>();
			{
				State state = em.find(State.class, game.getStateId());
				do {
					state.setGame(game);
					states.push(state);
					state.setActive(false);
				}
				while((state = state.getSrcState()) != null);
			}
			
			try {
				Board board = new Board(
						GamePlayers.valueOf(game.getPlayers()),
						GameLength.valueOf(game.getLength()),
						GameCountry.valueOf(game.getCountry())
						);
				board.populateDetails(game);
				{
					while(states.isEmpty() == false) {
						State state = states.pop();
						moves++;
						if(state.getToken() == null) continue; //ignore the first null state.
						board.preMove(state);
						MoveProcessor.processActions(board,state.getToken());
						board.postMove();
						board.testValidity();
						state.setActive(true);
						state.setGame(game);
					}
				}
				
				if(board.isGameOver()) {
					game.setStage(Stage.FINISHED);
				}
			}
			catch(WeblaboraException e) {
				System.out.println("Game Failed on move "+moves);
			}
		}
		
		ActionMessages messages = getMessages(request);
		messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.normalized",(System.nanoTime()-startTime)/1000000000d));
		saveMessages(request.getSession(), messages);
		return mapping.findForward("root");
	}
}
