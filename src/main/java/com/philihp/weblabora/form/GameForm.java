package com.philihp.weblabora.form;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.validator.ValidatorForm;

import com.philihp.weblabora.jpa.Game;
import com.philihp.weblabora.jpa.State;

public class GameForm extends ValidatorForm {

	private Integer gameId;

	private Integer stateId;

	public void reset() {
		this.gameId = null;
		this.stateId = null;
	}

	public Integer getGameId() {
		return gameId;
	}

	public void setGameId(Integer gameId) {
		this.gameId = gameId;
	}

	public Integer getStateId() {
		return stateId;
	}

	public void setStateId(Integer stateId) {
		this.stateId = stateId;
	}

	@Override
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		ActionErrors errors = super.validate(mapping, request);
		EntityManager em = (EntityManager) request.getAttribute("em");
		
		//by trying to look up the game and state, eclipselink will also cache it, since the
		//action is probably going to use it later... so looking it up instead of checking the
		//existence actually saves us a query, instead of doubling the # of queries.
		if(gameId != null) {
			Game game = em.find(Game.class, getGameId());
			if(game == null) {
				errors.add("gameId", new ActionMessage("message.invalidGameId", getGameId()));
			}
		}
		if(stateId != null) {
			State state = em.find(State.class, getStateId());
			if(state == null) {
				errors.add("stateId", new ActionMessage("message.invalidStateId", getStateId()));
			}
		}
		
		return errors;
	}

}
