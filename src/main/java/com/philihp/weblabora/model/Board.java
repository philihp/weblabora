package com.philihp.weblabora.model;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
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

	public Board() {
		gamePlayers = GamePlayers.FOUR;
		gameType = GameType.LONG;
		wheel = new Wheel(this);
		players = new Player[4];
		players[0] = new Player(this, Color.RED);
		players[1] = new Player(this, Color.GREEN);
		players[2] = new Player(this, Color.BLUE);
		players[3] = new Player(this, Color.WHITE);

		players[0].gameStart();
		players[1].gameStart();
		players[2].gameStart();
		players[3].gameStart();

		unbuiltBuildings = gameStartBuildings();

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
		for (BuildingEnum id : BuildingEnum.values()) {
			try {
				Constructor<? extends AbstractBuilding> constructor = id.clazz.getDeclaredConstructor();
				AbstractBuilding building = constructor.newInstance();
				buildings.add(building);
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		return buildings;
	}

	public List<AbstractBuilding> getUnbuiltBuildings() {
		return unbuiltBuildings;
	}

}
