package com.philihp.weblabora.form;

import org.apache.struts.action.ActionForm;

public class GameForm extends ActionForm {

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

}
