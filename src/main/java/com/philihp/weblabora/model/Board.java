package com.philihp.weblabora.model;

import java.lang.reflect.Constructor;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;

import com.philihp.weblabora.jpa.Game;
import com.philihp.weblabora.jpa.State;
import com.philihp.weblabora.model.building.Building;
import com.philihp.weblabora.model.building.BuildingEnum;
import com.philihp.weblabora.model.building.ClayMound;
import com.philihp.weblabora.model.building.CloisterOffice;
import com.philihp.weblabora.model.building.Farmyard;
import com.philihp.weblabora.model.building.Settlement;
import com.philihp.weblabora.model.building.SettlementEnum;

import static com.philihp.weblabora.model.building.BuildingEnum.*;

public class Board {
	
	public static final int[] PLOT_PURCHASE_PRICE = {3,4,4,5,5,5,6,6,7};
	
	public static final int[] DISTRICT_PURCHASE_PRICE = {2,3,4,4,5,5,6,7,8};

	protected final GamePlayers gamePlayers;

	protected final GameLength gameLength;
	
	protected final GameCountry gameCountry;

	protected Wheel wheel;

	protected Player[] players;

	private int activePlayer;

	private List<Building> unbuiltBuildings;
	
	private int plotsPurchased;
	
	private int districtsPurchased;
	
	private int startingPlayer;
	
	private StartingMarker startingMarker;
	
	private int round;
	
	private SettlementRound settlementRound;

	private int moveInRound;

	private boolean settling;
	
	private boolean extraRound;
	
	private boolean gameOver = false;
	
	private List<HistoryEntry> moveList = new ArrayList<HistoryEntry>();
	
	private State nextState;
	
	/**
	 * This makes lookups from {@link CommandUse CommandUse}
	 */
	private EnumMap<BuildingEnum, Building> allBuildings = 
			new EnumMap<BuildingEnum, Building>(BuildingEnum.class);;

	private List<Wonder> unclaimedWonders;

	public Board(GamePlayers gamePlayers, GameLength gameLength, GameCountry gameCountry) {
		int i;
		this.gamePlayers = gamePlayers;
		this.gameLength = gameLength;
		this.gameCountry = gameCountry;
		
		settlementRound = SettlementRound.S;

		wheel = new Wheel(this);

		activePlayer = 0;
		players = new Player[gamePlayers.asNumber()];
		for(i = 0;i < players.length; i++) {
			players[i] = new Player(this, Color.toColor(i));
			players[i].gameStart();
		}

		unclaimedWonders = gameStartWonders();
		
		addLandscapeBuildings();
		unbuiltBuildings = roundBuildings(SettlementRound.S);
		for(Player player : players) {
			player.getUnbuiltSettlements().addAll(roundSettlements(SettlementRound.S));
		}
		
		round = 1;
		moveInRound = 1;
		startingPlayer = 0;
		startingMarker = new StartingMarker(players[0]);
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


	private List<Settlement> roundSettlements(SettlementRound round) {
		List<Settlement> settlements = new ArrayList<Settlement>(8);
		if(round == null) return settlements;
		for (SettlementEnum settlementId : SettlementEnum.values()) {
			Settlement settlement = settlementId.getInstance();
			if (round == settlement.getRound())
				settlements.add(settlement);
		}
		return settlements;
	}

	private List<Building> roundBuildings(SettlementRound round) {
		List<Building> buildings = new ArrayList<Building>();
		if(round == null) return buildings;
		
		//TODO: should change this later so buildings use Round enum
		String phase = round.toString();
		if(phase.equals("S")) phase = "";
		
		for (BuildingEnum buildingId : BuildingEnum.values()) {
			Building building = buildingId.getInstance();
			
			GamePlayers buildingPlayers = building.getPlayers();
			GamePlayers boardPlayers = gamePlayers;
			
			if (phase.equals(building.getStage()) && buildingPlayers.ordinal() <= boardPlayers.ordinal()) {
				buildings.add(building);
				allBuildings.put(BuildingEnum.valueOf(building.getId()), building);
			}
		}
		return buildings;
	}

	public List<Building> getFutureBuildings() {
		List<Building> buildings = new ArrayList<Building>();
		for(BuildingEnum buildingId : BuildingEnum.values()) {
			if(allBuildings.containsKey(buildingId) == false) {
				buildings.add(buildingId.getInstance());
			}
		}
		return buildings;
	}
	
	public List<Settlement> getFutureSettlements() {
		List<Settlement> settlements = new ArrayList<Settlement>();
		for(SettlementEnum settlementId : SettlementEnum.values()) {
			Settlement settlement = settlementId.getInstance();
			if(settlement.getRound().ordinal() > getSettlementRound().ordinal()) {
				settlements.add(settlementId.getInstance());
			}
		}
		return settlements;
	}
	
	private void addLandscapeBuildings() {
		if(players.length >= 1) {
			allBuildings.put(LR1, (ClayMound)players[0].getLandscape().getTerrainAt(new Coordinate(4, 0)).getErection());
			allBuildings.put(LR2, (Farmyard)players[0].getLandscape().getTerrainAt(new Coordinate(2, 1)).getErection());
			allBuildings.put(LR3, (CloisterOffice)players[0].getLandscape().getTerrainAt(new Coordinate(4, 1)).getErection());
		}
		if(players.length >= 2) {
			allBuildings.put(LG1, (ClayMound)players[1].getLandscape().getTerrainAt(new Coordinate(4, 0)).getErection());
			allBuildings.put(LG2, (Farmyard)players[1].getLandscape().getTerrainAt(new Coordinate(2, 1)).getErection());
			allBuildings.put(LG3, (CloisterOffice)players[1].getLandscape().getTerrainAt(new Coordinate(4, 1)).getErection());
		}
		if(players.length >= 3) {
			allBuildings.put(LB1, (ClayMound)players[2].getLandscape().getTerrainAt(new Coordinate(4, 0)).getErection());
			allBuildings.put(LB2, (Farmyard)players[2].getLandscape().getTerrainAt(new Coordinate(2, 1)).getErection());
			allBuildings.put(LB3, (CloisterOffice)players[2].getLandscape().getTerrainAt(new Coordinate(4, 1)).getErection());
		}
		if(players.length >= 4) {
			allBuildings.put(LW1, (ClayMound)players[3].getLandscape().getTerrainAt(new Coordinate(4, 0)).getErection());
			allBuildings.put(LW2, (Farmyard)players[3].getLandscape().getTerrainAt(new Coordinate(2, 1)).getErection());
			allBuildings.put(LW3, (CloisterOffice)players[3].getLandscape().getTerrainAt(new Coordinate(4, 1)).getErection());
		}
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

	public void nextActivePlayer() {
		players[activePlayer].setActive(false);
		if (++activePlayer >= players.length)
			activePlayer = 0;
		players[activePlayer].setActive(true);
	}

	public void populateDetails(Game game) {
		if(players.length >= 1)
			players[0].populatePlayer(game.getPlayer1());
		if(players.length >= 2)
			players[1].populatePlayer(game.getPlayer2());
		if(players.length >= 3)
			players[2].populatePlayer(game.getPlayer3());
		if(players.length >= 4)
			players[3].populatePlayer(game.getPlayer4());
	}

	public void testValidity() throws WeblaboraException {
		for(Player player : players) {
			player.testValidity();
		}
	}
	
	public int purchasePlot() {
		return PLOT_PURCHASE_PRICE[plotsPurchased++];
	}
	
	public int purchaseDistrict() {
		return DISTRICT_PURCHASE_PRICE[districtsPurchased++];
	}
	
	public StartingMarker getStartingMarker() {
		return startingMarker;
	}
	
	public boolean isSettling() {
		return settling;
	}

	public void setSettling(boolean settling) {
		this.settling = settling;
	}
	
	public float getArmOffset() {
		return isSettling()?27.692f:13.846f; 
	}

	public void setSettlementRound(SettlementRound settlementRound) {
		this.settlementRound = settlementRound;
	}

	public SettlementRound getSettlementRound() {
		return settlementRound;
	}
	
	public static boolean isRoundBeforeSettlement(int round) {
		return roundBeforeSettlement(round) != null;
	}
	
	public static boolean isExtraRound(int round) {
		return round >= 24;
	}
	
	public static SettlementRound roundBeforeSettlement(int round) {
		switch (round) {
		case 6:
			return SettlementRound.A;
		case 9:
			return SettlementRound.B;
		case 15:
			return SettlementRound.C;
		case 18:
			return SettlementRound.D;
		case 25:
			return SettlementRound.E;
		default:
			return null;
		}
	}

	/**
	 * Called before every round.
	 */
	public void preRound() {
		
		getMoveList().add(new HistoryEntry("Round "+round));

		//1 - reset clergymen
		for(Player player : getPlayers()) {
			if(player.isClergymenAllPlaced())
				player.resetClergymen();
		}
		
		//2 - push arm
		getWheel().pushArm(round);
		
		//3 - settlement
		
	}
	
	public void preSettling() {
		System.out.println("------Begin Settlement------");
		
		getMoveList().add(new HistoryEntry("Settlement ("+roundBeforeSettlement(round)+")"));
	}
	
	public void preExtraRound() {
		System.out.println("------FINAL ROUND--------");

		for(Player player : players) {
			player.getPrior().clearLocation();
		}
		
		setExtraRound(true);
		getMoveList().add(new HistoryEntry("Extra Round"));
	}
	
	/**
	 * Called before every move.
	 */
	public void preMove(State state) {
		if(isGameOver()) return;
		
		if(isExtraRound() && moveInRound == 1) {
			preExtraRound();
		}
		else if(isSettling() && moveInRound == 1) {
			preSettling();
		}
		else if(moveInRound == 1) {
			preRound();
		}
		getMoveList().add(new HistoryEntry(state, getPlayer(getActivePlayer()).getColor()));
	}
	
	/**
	 * Called after every move.
	 */
	public void postMove() {
		nextActivePlayer();
		
		++moveInRound;
		if(isExtraRound() && moveInRound == 5) {
			postExtraRound();
		}
		if(isSettling() && moveInRound == 5) {
			postSettlement();
		}
		else if(!isSettling() && moveInRound == 6) {
			postRound();
		}
	}
	
	/**
	 * Called after every round.
	 */
	public void postRound() {
		//end of normal round
		moveInRound = 1;
		//end of round
		if(isExtraRound(round)) {
			round++;
			setExtraRound(true);
		}
		else if(isRoundBeforeSettlement(round)) {
			setSettling(true);
		}
		else {
			round++;
		}
		
		//5 -- pass starting player
		if(++startingPlayer == players.length) startingPlayer = 0;
		startingMarker.setOwner(players[startingPlayer]);
	}
	
	public void postSettlement() {
		//end of settlement round
		setSettling(false);
		
		List<Building> newBuildings = roundBuildings(roundBeforeSettlement(round));
		unbuiltBuildings.addAll(newBuildings);
		for(Player player : players) {
			player.getUnbuiltSettlements().addAll(roundSettlements(roundBeforeSettlement(round)));
		}
		
		setSettlementRound(getSettlementRound().next());

		if(settlementRound == SettlementRound.E) {
			setGameOver(true);
			getMoveList().add(new HistoryEntry("Game Over"));
		}
		
		round++;
		moveInRound=1;
	}
	
	public boolean isGameOver() {
		return gameOver;
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}

	public void postExtraRound() {
		setExtraRound(false);
		setSettling(true);
		wheel.pushArm(round);
		moveInRound=1;
	}

	public int getRound() {
		return round;
	}

	public String getMove() {
		if(isExtraRound()) return "extra";
		switch (moveInRound) {
		case 1:
			return "first";
		case 2:
			return "second";
		case 3:
			return "third";
		case 4:
			return "fourth";
		case 5:
			return "last";
		default:
			throw new RuntimeException("Illegal Round Number " + moveInRound);
		}
	}
	
	public String getActivePlayerColor() {
		return getPlayer(getActivePlayer()).getColor().toString();
	}
	
	public int[] getPlotCosts() {
		return Arrays.copyOfRange(PLOT_PURCHASE_PRICE, plotsPurchased, PLOT_PURCHASE_PRICE.length); 
	}
	
	public int[] getDistrictCosts() {
		return Arrays.copyOfRange(DISTRICT_PURCHASE_PRICE, districtsPurchased, DISTRICT_PURCHASE_PRICE.length); 
	}

	public List<HistoryEntry> getMoveList() {
		return moveList;
	}
	
	public List<HistoryEntry> getMoveListReversed() {
		List<HistoryEntry> newList = new ArrayList<HistoryEntry>(getMoveList());
		Collections.reverse(newList);
		return newList;
	}

	public boolean isExtraRound() {
		return extraRound;
	}

	public void setExtraRound(boolean extraRound) {
		this.extraRound = extraRound;
	}
	
	public Scorecard getScorecard() {
		return new Scorecard(this);
	}

	public State getNextState() {
		return nextState;
	}

	public void setNextState(State nextState) {
		this.nextState = nextState;
	}

	public GamePlayers getGamePlayers() {
		return gamePlayers;
	}

	public GameLength getGameLength() {
		return gameLength;
	}

	public GameCountry getGameCountry() {
		return gameCountry;
	}
	
	
}
