package com.philihp.weblabora.model;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.philihp.weblabora.model.building.AbstractBuilding;
import com.philihp.weblabora.model.building.BuildingEnum;

public class Board {

	protected final GamePlayers gamePlayers;

	protected final GameType gameType;

	protected Wheel wheel;

	protected Player[] players;

	private int activePlayer;

	private List<AbstractBuilding> unbuiltBuildings;

	private List<Wonder> unclaimedWonders;

	protected int currentMove;

	public Board() {
		gamePlayers = GamePlayers.FOUR;
		gameType = GameType.LONG;

		wheel = new Wheel(this);
		getWheel().pushArm();

		players = new Player[4];
		players[0] = new Player(this, Color.RED);
		players[1] = new Player(this, Color.GREEN);
		players[2] = new Player(this, Color.BLUE);
		players[3] = new Player(this, Color.WHITE);
		currentMove = 1;

		players[0].gameStart();
		players[1].gameStart();
		players[2].gameStart();
		players[3].gameStart();

		unbuiltBuildings = gameStartBuildings();
		unclaimedWonders = gameStartWonders();

		activePlayer = 0;
	}

	public Wheel getWheel() {
		return wheel;
	}

	public Player getPlayer(int i) {
		return players[i];
	}

	public Player[] getPlayers() {
		return players;
	}

	public int getActivePlayer() {
		return activePlayer;
	}

	private List<AbstractBuilding> gameStartBuildings() {
		List<AbstractBuilding> buildings = new ArrayList<AbstractBuilding>();
		for (BuildingEnum buildingId : BuildingEnum.values()) {
			AbstractBuilding building = buildingId.getInstance();
			if ("".equals(building.getStage()))
				buildings.add(building);
		}
		return buildings;
	}

	private List<Wonder> gameStartWonders() {
		List<Wonder> wonders = new ArrayList<Wonder>(8);
		wonders.add(new Wonder());
		wonders.add(new Wonder());
		wonders.add(new Wonder());
		wonders.add(new Wonder());
		wonders.add(new Wonder());
		wonders.add(new Wonder());
		wonders.add(new Wonder());
		wonders.add(new Wonder());
		return wonders;
	}

	public Wonder claimWonder() {
		return unclaimedWonders.remove(unclaimedWonders.size() - 1);
	}

	public List<AbstractBuilding> getUnbuiltBuildings() {
		return unbuiltBuildings;
	}

	public int getCurrentMove() {
		return currentMove;
	}

	public void nextActivePlayer() {
		if (++activePlayer >= players.length)
			activePlayer = 0;
	}

}
