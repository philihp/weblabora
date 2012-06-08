package com.philihp.weblabora.action;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.philihp.weblabora.jpa.Game;
import com.philihp.weblabora.jpa.State;
import com.philihp.weblabora.jpa.User;
import com.philihp.weblabora.model.Color;

public class CreateGame extends BaseAction {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response, User user) throws Exception {

		EntityManager em = (EntityManager)request.getAttribute("em");

		Game game = new Game();
		game.getPlayer1().setUser(user);
		game.getPlayer1().setColor(Color.RED.toString());
		game.getPlayer1().setMove("");
		game.getPlayer2().setColor(Color.GREEN.toString());
		game.getPlayer2().setMove("");
		game.getPlayer3().setColor(Color.BLUE.toString());
		game.getPlayer3().setMove("");
		game.getPlayer4().setColor(Color.WHITE.toString());
		game.getPlayer4().setMove("");
		user.setActiveGame(game);
		game.setState(em.find(State.class, 1));
		em.persist(game);

		return mapping.findForward("root");
	}

}
