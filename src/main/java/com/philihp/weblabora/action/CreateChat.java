package com.philihp.weblabora.action;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.philihp.weblabora.form.CreateChatForm;
import com.philihp.weblabora.jpa.Chat;
import com.philihp.weblabora.jpa.Chat.Action;
import com.philihp.weblabora.jpa.Game;
import com.philihp.weblabora.jpa.User;

public class CreateChat extends BaseAction {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response, User user) throws Exception {
		CreateChatForm form = (CreateChatForm)actionForm;
		EntityManager em = (EntityManager)request.getAttribute("em");

		TypedQuery<Game> gameQuery = em.createQuery("SELECT g FROM Game g WHERE g.gameId = :gameId", Game.class);
		gameQuery.setParameter("gameId", form.getGameId());
		Game game = gameQuery.getSingleResult();

		// Note that this silently assumes that a user may take only one player
		// seat in a game even thou it does not have to be so.
		Integer playerSeat = game.getUsersPlayerSeat(user);

		Chat chat = new Chat();
		chat.setGame(game);
		chat.setUser(user);
		chat.setPlayerSeat(playerSeat);
		chat.setAction(Action.create);
		chat.setText(form.getText());
		em.persist(chat);

		ActionForward forward = mapping.findForward("show-chat");
		String path = forward.getPath()+"?gameId="+game.getGameId();
		return new ActionForward(forward.getName(), path, forward.getRedirect(), forward.getModule());
	}
}
