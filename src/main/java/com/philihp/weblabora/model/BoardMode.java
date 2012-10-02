package com.philihp.weblabora.model;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.NotImplementedException;

import com.philihp.weblabora.model.building.Building;
import com.philihp.weblabora.model.building.BuildingEnum;

import static com.philihp.weblabora.model.GameLength.*;
import static com.philihp.weblabora.model.GameCountry.*;
import static com.philihp.weblabora.model.GamePlayers.*;
import static com.philihp.weblabora.model.Wheel.Position.E;
import static com.philihp.weblabora.model.Wheel.Position.H;
import static com.philihp.weblabora.model.Wheel.Position.K;

public abstract class BoardMode {
	
	protected final Board board;

	private static final EnumMap<GamePlayers, EnumMap<GameLength, EnumMap<GameCountry, Class<? extends BoardMode>>>> map;
	
	//i'm not convinced this is the best way to do this... but whatever, it works.
	static {
		map = new EnumMap<GamePlayers, EnumMap<GameLength, EnumMap<GameCountry, Class<? extends BoardMode>>>>(
				GamePlayers.class);
		for (GamePlayers p : GamePlayers.values()) {
			EnumMap<GameLength, EnumMap<GameCountry, Class<? extends BoardMode>>> submap =
					new EnumMap<GameLength, EnumMap<GameCountry, Class<? extends BoardMode>>>(GameLength.class);
			map.put(p, submap);
			for (GameLength l : GameLength.values()) {
				EnumMap<GameCountry, Class<? extends BoardMode>> subsubmap =
						new EnumMap<GameCountry, Class<? extends BoardMode>>(GameCountry.class);
				submap.put(l, subsubmap);
			}
		}

		map.get(TWO).get(LONG).put(FRANCE, BoardModeTwoLongFrance.class);
		map.get(THREE).get(LONG).put(FRANCE, BoardModeThreeLongFrance.class);
		map.get(FOUR).get(LONG).put(FRANCE, BoardModeFourLongFrance.class);
		
		map.get(TWO).get(LONG).put(IRELAND, BoardModeTwoLongIreland.class);
		map.get(THREE).get(LONG).put(IRELAND, BoardModeThreeLongIreland.class);
		map.get(FOUR).get(LONG).put(IRELAND, BoardModeFourLongIreland.class);

		map.get(TWO).get(SHORT).put(FRANCE, BoardModeTwoShortFrance.class);
		map.get(THREE).get(SHORT).put(FRANCE, BoardModeThreeShortFrance.class);
		map.get(FOUR).get(SHORT).put(FRANCE, BoardModeFourShortFrance.class);
		
		map.get(TWO).get(SHORT).put(IRELAND, BoardModeTwoShortIreland.class);
		map.get(THREE).get(SHORT).put(IRELAND, BoardModeThreeShortIreland.class);
		map.get(FOUR).get(SHORT).put(IRELAND, BoardModeFourShortIreland.class);
}

	public static BoardMode getMode(Board board, GamePlayers gamePlayers,
			GameLength gameLength, GameCountry gameCountry) {
		try {
			EnumMap<GameLength, EnumMap<GameCountry, Class<? extends BoardMode>>> submap = map.get(gamePlayers);
			EnumMap<GameCountry, Class<? extends BoardMode>> subsubmap = submap.get(gameLength);
			Class<? extends BoardMode> clazz = subsubmap.get(gameCountry);
			Constructor<? extends BoardMode> constructor = clazz.getDeclaredConstructor(Board.class);
//			Constructor<? extends BoardMode> constructor = map.get(gamePlayers)
//					.get(gameLength).get(gameCountry)
//					.getDeclaredConstructor(Board.class);
			return (BoardMode) constructor.newInstance(board);
		} catch (NoSuchMethodException e) {
			throw new RuntimeException(e);
		} catch (InstantiationException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		} catch (IllegalArgumentException e) {
			throw new RuntimeException(e);
		} catch (InvocationTargetException e) {
			throw new RuntimeException(e);
		}
	}

	protected BoardMode(Board board) {
		this.board = board;
	}

	protected Board getBoard() {
		return this.board;
	}

	abstract public int[] getWheelArmValues();

	abstract public List<Building> roundBuildings();

	abstract public List<Building> futureBuildings();

	abstract public boolean isExtraRound(int round);

	abstract public SettlementRound roundBeforeSettlement(int round);

	abstract public void postMove();
	
	/**
	 * Hook for pre-round processing for Short 3/4 player games to dish out resources
	 */
	public void preRound() {
	}

	abstract public void postRound();

	abstract public String getMoveName();

	abstract public int grapeActiveOnRound();

	abstract public int stoneActiveOnRound();

	abstract public void setWheelTokens(Wheel wheel);

	abstract public GamePlayers getPlayers();

	abstract public GameCountry getCountry();

	abstract public GameLength getLength();

	/**
	 * Hook for Short 3/4 Player Short games to remove the first peat and forest tiles
	 */
	public void customizeLandscape(Landscape landscape) {
	}
	
	abstract public boolean isProductionBonusActive();
}
