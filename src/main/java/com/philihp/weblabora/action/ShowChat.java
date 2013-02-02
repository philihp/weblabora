package com.philihp.weblabora.action;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.philihp.weblabora.form.CreateChatForm;
import com.philihp.weblabora.jpa.Chat;
import com.philihp.weblabora.jpa.Game;
import com.philihp.weblabora.jpa.User;
import com.philihp.weblabora.util.AuthenticationException;

public class ShowChat extends BaseAction {

	@Override
	ActionForward execute(ActionMapping mapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response, User user)
			throws AuthenticationException, Exception {
		EntityManager em = (EntityManager)request.getAttribute("em");

		CreateChatForm form = (CreateChatForm)actionForm;
		if(form.getGameId() == null) {
			return mapping.findForward("no-game");
		}

		Game game = em.find(Game.class, form.getGameId());
		request.setAttribute("game", game);

		TypedQuery<Chat> query = em.createQuery("SELECT c FROM Chat c " +
				"WHERE c.game = :game " +
				"ORDER BY c.dateCreated", Chat.class);
		query.setParameter("game", game);
		List<Chat> chat = query.getResultList();

		request.setAttribute("chat", chat);

		return mapping.findForward("view");
	}

}
