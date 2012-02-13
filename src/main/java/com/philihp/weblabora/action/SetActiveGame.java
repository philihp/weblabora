package com.philihp.weblabora.action;

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
import com.philihp.weblabora.jpa.Game;
import com.philihp.weblabora.jpa.User;
import com.philihp.weblabora.model.Board;
import com.philihp.weblabora.util.EntityManagerManager;
import com.philihp.weblabora.util.FacebookCredentials;

public class SetActiveGame extends BaseAction {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response, User user) throws Exception {
		GameForm form = (GameForm) actionForm;
		EntityManager em = EntityManagerManager.get();

		TypedQuery<Game> query = em.createQuery("SELECT g FROM Game g WHERE g.gameId = :gameId", Game.class);
		query.setParameter("gameId", form.getGameId());
		Game game = query.getSingleResult();

		if (game.isUserAPlayer(user) == false)
			throw new Exception("You are not a player of that game.");

		System.out.println("Setting Active Game: " + game.getGameId() + " \t " + game);
		request.getSession().setAttribute("activeGame", game);

		return mapping.findForward("default");
	}

}
