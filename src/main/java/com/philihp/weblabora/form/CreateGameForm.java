package com.philihp.weblabora.form;

import org.apache.struts.action.ActionForm;

import com.philihp.weblabora.model.GamePlayers;

import static com.philihp.weblabora.model.GamePlayers.*;

public class CreateGameForm extends ActionForm {

	Integer players;
	String length;
	String country;
	
	public CreateGameForm() {
		reset();
	}

	public Integer getPlayers() {
		return players;
	}

	public void setPlayers(Integer players) {
		this.players = players;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void reset() {
		players = 4;
		length = "LONG";
		country = "FRANCE";
	}

}
