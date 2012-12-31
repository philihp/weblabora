package com.philihp.weblabora.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
				PlayerGuesser guesser = new PlayerGuesser();
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
						guesser.addGuess(board.getActivePlayer(), state.getExplorer());
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
					if(game.getPlayer1().getUser() == null)
						game.getPlayer1().setUser(guesser.guessProbableUser(0));
					if(game.getPlayer2().getUser() == null)
						game.getPlayer2().setUser(guesser.guessProbableUser(1));
					if(game.getPlayer3().getUser() == null && game.getPlayers() >= 3)
						game.getPlayer3().setUser(guesser.guessProbableUser(2));
					if(game.getPlayer4().getUser() == null && game.getPlayers() >= 4)
						game.getPlayer4().setUser(guesser.guessProbableUser(3));
					game.setActivePlayer(null);
				}
				else {
					game.setActivePlayer(board.getActivePlayer());
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
	
	private class PlayerGuesser {
		@SuppressWarnings("unchecked")
		private Map<User,Integer> slots[] = (HashMap<User,Integer>[])new HashMap<?,?>[4];
		
		public PlayerGuesser() {
			slots[0] = new HashMap<User,Integer>();
			slots[1] = new HashMap<User,Integer>();
			slots[2] = new HashMap<User,Integer>();
			slots[3] = new HashMap<User,Integer>();
		}
		public void addGuess(int slot,User user) {
			if(slots[slot].containsKey(user)) {
				slots[slot].put(user, slots[slot].get(user)+1);
			}
			else {
				slots[slot].put(user, 1);
			}
		}
		public User guessProbableUser(int slot) {
			Map<User,Integer> map = slots[slot];
			User guess = null;
			int max = 0;
			for(Object key : map.keySet()) {
				if(map.get(key) > max) {
					max = map.get(key);
					guess = (User)key;
				}
			}
			return guess;
		}
	}
	
	
	
	
	
	
	
	
}
