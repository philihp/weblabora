package com.philihp.weblabora.model;

import java.lang.reflect.Constructor;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;

import com.philihp.weblabora.jpa.Game;
import com.philihp.weblabora.model.building.Building;
import com.philihp.weblabora.model.building.BuildingEnum;
import com.philihp.weblabora.model.building.ClayMound;
import com.philihp.weblabora.model.building.CloisterOffice;
import com.philihp.weblabora.model.building.Farmyard;

import static com.philihp.weblabora.model.building.BuildingEnum.*;

public class Board {

	protected final GamePlayers gamePlayers;

	protected final GameType gameType;

	protected Wheel wheel;

	protected Player[] players;

	private int activePlayer;

	private List<Building> unbuiltBuildings;
	
	/**
	 * This makes lookups from {@link CommandUse CommandUse}
	 */
	private EnumMap<BuildingEnum, Building> allBuildings;

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
		
		allBuildings = generateBuildingsMap();
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

	private List<Building> gameStartBuildings() {
		List<Building> buildings = new ArrayList<Building>();
		for (BuildingEnum buildingId : BuildingEnum.values()) {
			Building building = buildingId.getInstance();
			if ("".equals(building.getStage()))
				buildings.add(building);
		}
		return buildings;
	}
	
	private EnumMap<BuildingEnum, Building> generateBuildingsMap() {
		EnumMap<BuildingEnum, Building> map = 
				new EnumMap<BuildingEnum, Building>(BuildingEnum.class);

		map.put(LR1, (ClayMound)players[0].getLandscape().getTerrainAt(new Coordinate(4, 0)).getErection());
		map.put(LG1, (ClayMound)players[1].getLandscape().getTerrainAt(new Coordinate(4, 0)).getErection());
		map.put(LB1, (ClayMound)players[2].getLandscape().getTerrainAt(new Coordinate(4, 0)).getErection());
		map.put(LW1, (ClayMound)players[3].getLandscape().getTerrainAt(new Coordinate(4, 0)).getErection());
		map.put(LR2, (Farmyard)players[0].getLandscape().getTerrainAt(new Coordinate(2, 1)).getErection());
		map.put(LG2, (Farmyard)players[1].getLandscape().getTerrainAt(new Coordinate(2, 1)).getErection());
		map.put(LB2, (Farmyard)players[2].getLandscape().getTerrainAt(new Coordinate(2, 1)).getErection());
		map.put(LW2, (Farmyard)players[3].getLandscape().getTerrainAt(new Coordinate(2, 1)).getErection());
		map.put(LR3, (CloisterOffice)players[0].getLandscape().getTerrainAt(new Coordinate(4, 1)).getErection());
		map.put(LG3, (CloisterOffice)players[1].getLandscape().getTerrainAt(new Coordinate(4, 1)).getErection());
		map.put(LB3, (CloisterOffice)players[2].getLandscape().getTerrainAt(new Coordinate(4, 1)).getErection());
		map.put(LW3, (CloisterOffice)players[3].getLandscape().getTerrainAt(new Coordinate(4, 1)).getErection());
		
		for(Building building : unbuiltBuildings) {
			map.put(BuildingEnum.valueOf(building.getId()), building);
		}
		
		return map;
	}
	
	public Building findBuildingInstance(BuildingEnum buildingId) {
		return allBuildings.get(buildingId);
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

	public List<Building> getUnbuiltBuildings() {
		return unbuiltBuildings;
	}

	public int getCurrentMove() {
		return currentMove;
	}

	public void nextActivePlayer() {
		players[activePlayer].setActive(false);
		if (++activePlayer >= players.length)
			activePlayer = 0;
		players[activePlayer].setActive(true);
	}

	public void populateDetails(Game game) {
		players[0].populatePlayer(game.getPlayer1());
		players[1].populatePlayer(game.getPlayer2());
		players[2].populatePlayer(game.getPlayer3());
		players[3].populatePlayer(game.getPlayer4());
	}

	public void testValidity() throws WeblaboraException {
		for(Player player : players) {
			player.testValidity();
		}
	}

}
