package com.philihp.weblabora.model;

import com.philihp.weblabora.jpa.State;

public class HistoryEntry {

	private String text;
	private State state;
	private Color color;

	public HistoryEntry(String text) {
		this.text = text;
		this.color = null;
		this.state = null;
	}

	public HistoryEntry(State state, Color color) {
		this.text = state.getToken();
		this.state = state;
		this.color = color;
	}

	public String getText() {
		return text;
	}

	public State getState() {
		return state;
	}
	
	public Color getColor() {
		return color;
	}

	public String toString() {
		if(state == null) {
			return "[state=null, text="+getText()+", color="+getColor()+"]";
		}
		else {
			return "[state="+getState().getStateId()+", text="+getText()+", color="+getColor()+"]";
		}
	}

}
