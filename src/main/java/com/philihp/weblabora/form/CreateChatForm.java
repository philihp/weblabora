package com.philihp.weblabora.form;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.validator.ValidatorForm;

import com.philihp.weblabora.jpa.Game;

public class CreateChatForm extends ValidatorForm {
	private Integer gameId;

	public Integer getGameId() {
		return gameId;
	}

	public void setGameId(Integer gameId) {
		this.gameId = gameId;
	}

	private String text;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void reset() {
		this.gameId = null;
		this.text = null;
	}

	@Override
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		ActionErrors errors = super.validate(mapping, request);
		EntityManager em = (EntityManager) request.getAttribute("em");

		if(gameId != null) {
			Game game = em.find(Game.class, gameId);
			if(game == null) {
				errors.add("gameId", new ActionMessage("message.invalidGameId", gameId));
			}
		}

		return errors;
	}
}
