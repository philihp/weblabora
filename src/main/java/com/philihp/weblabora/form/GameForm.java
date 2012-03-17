package com.philihp.weblabora.form;

import org.apache.struts.action.ActionForm;

public class GameForm extends ActionForm {

	private Integer gameId;

	public void reset() {
		this.gameId = 0;
	}

	public Integer getGameId() {
		return gameId;
	}

	public void setGameId(Integer gameId) {
		this.gameId = gameId;
	}

}
