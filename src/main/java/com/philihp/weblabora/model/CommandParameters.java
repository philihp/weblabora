package com.philihp.weblabora.model;

import java.util.ArrayList;
import java.util.List;

public class CommandParameters {

	/**
	 * The first token's first character.
	 */
	private char command;

	/**
	 * Everything in the middle
	 */
	private List<String> params = new ArrayList<String>(2);

	/**
	 * The last token.
	 */
	private String suffix;

	private boolean placeClergyman;

	private boolean mustBePrior;

	private MoveHistory history;

	public CommandParameters(MoveHistory history) {
   		this.mustBePrior = history.isPreviousBuild();
		this.history = history;
	}

	public MoveHistory getHistory() {
		return history;
	}

	public char getCommand() {
		return command;
	}

	public void setCommand(char command) {
		this.command = command;
	}

	public List<String> getParams() {
		return params;
	}

	public void setParams(List<String> params) {
		this.params = params;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public String get(int i) {
		return params.get(i);
	}

	public int size() {
		return params.size();
	}

	public void setPlaceClergyman(boolean placeClergyman) {
		this.placeClergyman = placeClergyman;
	}

	public boolean getPlaceClergyman() {
		return this.placeClergyman;
	}

	public boolean isMustBePrior() {
		return mustBePrior;
	}

}
