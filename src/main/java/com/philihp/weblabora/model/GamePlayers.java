package com.philihp.weblabora.model;

public enum GamePlayers {
  ONE(1), TWO(2), THREE(3), FOUR(4);
  
  private int num;
  private GamePlayers(int num) {
	  this.num = num;
  }
  public int getAsNumber() {
	  return num;
  }
  public static GamePlayers valueOf(int i) {
	  for(GamePlayers gamePlayers: GamePlayers.values()) {
		  if(gamePlayers.getAsNumber() == i) {
			  return gamePlayers;
		  }
	  }
	  return null;
  }
}
