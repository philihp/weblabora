package com.philihp.weblabora.action;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.philihp.weblabora.form.CreateGameForm;
import com.philihp.weblabora.jpa.Game;
import com.philihp.weblabora.jpa.State;
import com.philihp.weblabora.jpa.User;
import com.philihp.weblabora.model.Color;

public class CreateGame extends BaseAction {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response, User user) throws Exception {
		CreateGameForm form = (CreateGameForm)actionForm;
		EntityManager em = (EntityManager)request.getAttribute("em");
		
		State state = new State();
		state.setExplorer(user);
		state.setActive(true);
		em.persist(state);
		
		Game game = new Game();
		game.setStage(Game.Stage.RECRUITING);
		game.getPlayer1().setUser(user);
		game.getPlayer1().setColor(Color.RED.toString());
		game.getPlayer1().setMove("");
		game.getPlayer2().setColor(Color.GREEN.toString());
		game.getPlayer2().setMove("");
		game.getPlayer3().setColor(Color.BLUE.toString());
		game.getPlayer3().setMove("");
		game.getPlayer4().setColor(Color.WHITE.toString());
		game.getPlayer4().setMove("");
		game.setCountry(form.getCountry());
		game.setLength(form.getLength());
		game.setPlayers(form.getPlayers());
		game.getStates().add(state);
		game.setActivePlayer(0);
		em.persist(game);
		
		state.setGame(game);
		
		if(game.getPlayers() == 1) {
			game.setLength(null);
			game.setStage(Game.Stage.IN_PROGRESS);
		}

		return mapping.findForward("root");
	}

}
