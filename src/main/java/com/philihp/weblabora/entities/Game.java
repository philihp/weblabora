package com.philihp.weblabora.entities;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.List;

public class Game {

	public static class Player {

		private String color;

		public String getColor() {
			return color;
		}

		public void setColor(String color) {
			this.color = color;
		}

	}

	private int gameId;

	private Player player1;

	private Player player2;

	private Player player3;

	private Player player4;

	private String length;

	private Integer players;

	private String country;

	private List<State> states;

	private Integer stateId;

	private Integer activePlayer;

	public Game() {
		// player1-4 must not be null
		this.player1 = new Player();
		this.player2 = new Player();
		this.player3 = new Player();
		this.player4 = new Player();
	}

	public int getGameId() {
		return gameId;
	}

	public void setGameId(int gameId) {
		this.gameId = gameId;
	}

	public Player getPlayer1() {
		return player1;
	}

	public void setPlayer1(Player player1) {
		this.player1 = player1;
	}

	public Player getPlayer2() {
		return player2;
	}

	public void setPlayer2(Player player2) {
		this.player2 = player2;
	}

	public Player getPlayer3() {
		return player3;
	}

	public void setPlayer3(Player player3) {
		this.player3 = player3;
	}

	public Player getPlayer4() {
		return player4;
	}

	public void setPlayer4(Player player4) {
		this.player4 = player4;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public Integer getPlayers() {
		return players;
	}

	public void setPlayers(Integer players) {
		this.players = players;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Transient
	public Player getSeat(int seat) {
		return getAllPlayers()[seat - 1];
	}

	@Transient
	public Player[] getAllPlayers() {
		return new Player[] { player1, player2, player3, player4 };
	}

	@Transient
	public boolean isUndoable() {
		/*
		 * long movedAt = this.getState().getDateCreated().getTime(); long now =
		 * new Date().getTime(); return (now-movedAt < 24*60*60*1000) &&
		 * (getState().getSrcState() != null);
		 */
		return true;
	}

	public List<State> getStates() {
		if (states == null)
			states = new ArrayList<State>();
		return states;
	}

	public void setStates(List<State> states) {
		this.states = states;
	}

	@Transient
	public List<State> getActiveStates() {
		List<State> activeStates = new ArrayList<State>();
		for (State state : getStates()) {
			if (state.isActive()) {
				activeStates.add(state);
			}
		}
		return activeStates;
	}

	@Transient
	public State getState() {
		List<State> states = getActiveStates();
		return states.get(states.size() - 1);
	}

	public Integer getStateId() {
		return stateId;
	}

	public void setStateId(Integer stateId) {
		this.stateId = stateId;
	}

	public Integer getActivePlayer() {
		return activePlayer;
	}

	public void setActivePlayer(Integer activePlayer) {
		this.activePlayer = activePlayer;
	}
}
