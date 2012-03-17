package com.philihp.weblabora.form;

import org.apache.struts.action.ActionForm;

public class JoinGameForm extends GameForm {

	private int seat;

	public void reset() {
		super.reset();
		this.seat = 0;
	}

	public int getSeat() {
		return seat;
	}

	public void setSeat(int seat) {
		this.seat = seat;
	}

}
