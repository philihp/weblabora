package com.philihp.weblabora.model;

public class Board {
	
	protected final GamePlayers gamePlayers;
	
	protected final GameType gameType;

	protected Wheel wheel;
	
	public Board() {
		gamePlayers = GamePlayers.FOUR;
		gameType = GameType.LONG;
		wheel = new Wheel(this);
	}
	
	public Wheel getWheel() {
		return wheel;
	}
	
}
