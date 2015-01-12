package com.philihp.weblabora.jpa;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class State {

	private int stateId;

	private String token;

	private List<State> dstStates;

	private State srcState;
	
	private Game game;
	
	private boolean active;

	public int getStateId() {
		return stateId;
	}

	public void setStateId(int stateId) {
		this.stateId = stateId;
	}

	public List<State> getDstStates() {
		return dstStates;
	}

	public void setDstStates(List<State> dstStates) {
		this.dstStates = dstStates;
	}

	public State getSrcState() {
		return srcState;
	}

	public void setSrcState(State srcState) {
		this.srcState = srcState;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public List<State> getStates() {
		List<State> list = new ArrayList<State>();
		State state = this;
		do {
			if(state.getToken() != null) 
				list.add(state);
			state = state.getSrcState();
		} while(state != null);
		Collections.reverse(list);
		return list;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

}
