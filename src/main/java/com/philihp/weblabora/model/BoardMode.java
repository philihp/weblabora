package com.philihp.weblabora.model;

import static com.philihp.weblabora.model.GameCountry.*;
import static com.philihp.weblabora.model.GameLength.*;
import static com.philihp.weblabora.model.GamePlayers.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;

import com.philihp.weblabora.model.building.Building;

public abstract class BoardMode {
	
	protected final Board board;

	protected final int GRAPE_INACTIVE_IN_IRELAND = 99999;
	
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
		
		map.get(ONE).get(NULL).put(FRANCE, BoardModeOneFrance.class);
		map.get(ONE).get(NULL).put(IRELAND, BoardModeOneIreland.class);
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
		if(isRoundStartBonusActive()) {
			for(Player player : getBoard().getPlayers()) {
				switch(getBoard().getRound()) {
				case 1:
					player.addSheep(1);
					player.addGrain(1);
					break;
				case 2:
					player.addClay(1);
					player.addGrain(1);
					break;
				case 3:
					player.addWood(1);
					player.addGrain(1);
					break;
				case 4:
					player.addStone(1);
					player.addGrain(1);
					break;
				case 5:
					player.addStone(1);
					player.addPeat(1);
					break;
				case 6:
					player.addStone(1);
					player.addClay(1);
					break;
				case 7:
					player.addStone(1);
					player.addWood(1);
					break;
				case 8:
					player.addStone(1);
					player.addNickel(1);
					break;
				case 9:
					player.addStone(1);
					player.addMeat(1);
					break;
				case 10:
					player.addBooks(1);
					player.addGrain(1);
					break;
				case 11:
					player.addPottery(1);
					player.addClay(1);
					break;
				case 12:
					player.addOrnament(1);
					player.addWood(1);
					break;
				case 13:
					break;
				default: 
					throw new RuntimeException("Should never reach round "+board.getRound()+" when dishing out bonus production at beginning of round.");
				}
			}
		}
	}

	abstract public void postRound();

	abstract public String getMoveName();

	abstract public int grapeActiveOnRound();

	abstract public int stoneActiveOnRound();
	
	public int jokerActiveOnRound() {
		return 0;
	}

	abstract public void setWheelTokens(Wheel wheel);

	abstract public GamePlayers getPlayers();

	abstract public GameCountry getCountry();

	abstract public GameLength getLength();

	abstract public int getMovesInRound();
	
	abstract public int getLastSettlementAfterRound();


	/**
	 * Hook for Short 3/4 Player Short games to remove the first peat and forest tiles
	 */
	public void customizeLandscape(Landscape landscape) {
	}
	
	abstract protected boolean isProductionBonusActive();
	
	abstract protected boolean isRoundStartBonusActive();
	
	public boolean isSecondLayBrotherUsed() {
		return true;
	}
	
	/**
	 * Distributes bonus production when the wheel is used for Short 3/4 games
	 */
	public void distributeBonusProduction(UsageParam items) {
		if(isProductionBonusActive()) {
			for(Player player : board.getPlayers()) {
				player.addAll(items);
			}
		}
	}

	public static final int[] PLOT_PURCHASE_PRICE = {3,4,4,5,5,5,6,6,7};
	
	public static final int[] DISTRICT_PURCHASE_PRICE = {2,3,4,4,5,5,6,7,8};
	
	private int plotsPurchased;
	
	private int districtsPurchased;
	
	public int[] getPlotCosts() {
		return Arrays.copyOfRange(PLOT_PURCHASE_PRICE, plotsPurchased, PLOT_PURCHASE_PRICE.length);
	}
	
	public int[] getDistrictCosts() {
		return Arrays.copyOfRange(DISTRICT_PURCHASE_PRICE, districtsPurchased, DISTRICT_PURCHASE_PRICE.length); 
	}
	
	public int purchasePlot() {
		return PLOT_PURCHASE_PRICE[districtsPurchased++];
	}
	
	public int purchaseDistrict() {
		return DISTRICT_PURCHASE_PRICE[districtsPurchased++];
	}
	
	/**
	 * Special mode-specific setup.
	 */
	public void setup() {
	}
	
	/**
	 * Netural player, for use in Solo game.
	 * @return
	 */
	public Player getNeutralPlayer() {
		return null;
	}
	
	public boolean isNeutralPlayerUsed() {
		return getNeutralPlayer() != null;
	}
}
