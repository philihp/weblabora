package com.philihp.weblabora.model;

import java.util.ArrayList;
import java.util.List;

public class CommandParameters {

	private String command;

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

	private TurnHistory history;

	public CommandParameters(TurnHistory history) {
		this.mustBePrior = (history!=null ? history.isPreviousBuild() : false);
		this.history = history;
	}

	public TurnHistory getHistory() {
		return history;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
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

	public String get(int i) throws WeblaboraException {
		if (params.size() <= i) {
			throw new WeblaboraException("Incorrect number of parameters for command " + command + ".");
		}
		return params.get(i);
	}

	public String getUpperCased(int i) throws WeblaboraException {
		String s = get(i);
		if(s != null) s = s.toUpperCase();
		return s;
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

	public void setMustBePrior(boolean mustBePrior) {
		this.mustBePrior = mustBePrior;
	}
}
