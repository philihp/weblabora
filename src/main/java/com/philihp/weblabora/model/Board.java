package com.philihp.weblabora.model;

import static com.philihp.weblabora.model.building.BuildingEnum.LB1;
import static com.philihp.weblabora.model.building.BuildingEnum.LB2;
import static com.philihp.weblabora.model.building.BuildingEnum.LB3;
import static com.philihp.weblabora.model.building.BuildingEnum.LG1;
import static com.philihp.weblabora.model.building.BuildingEnum.LG2;
import static com.philihp.weblabora.model.building.BuildingEnum.LG3;
import static com.philihp.weblabora.model.building.BuildingEnum.LR1;
import static com.philihp.weblabora.model.building.BuildingEnum.LR2;
import static com.philihp.weblabora.model.building.BuildingEnum.LR3;
import static com.philihp.weblabora.model.building.BuildingEnum.LW1;
import static com.philihp.weblabora.model.building.BuildingEnum.LW2;
import static com.philihp.weblabora.model.building.BuildingEnum.LW3;

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

public class Board {
	
	private BoardMode mode;
	
	public static final int[] PLOT_PURCHASE_PRICE = {3,4,4,5,5,5,6,6,7};
	
	public static final int[] DISTRICT_PURCHASE_PRICE = {2,3,4,4,5,5,6,7,8};

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
			new EnumMap<BuildingEnum, Building>(BuildingEnum.class);

	private List<Wonder> unclaimedWonders;

	public Board(GamePlayers gamePlayers, GameLength gameLength, GameCountry gameCountry) {
		this.mode = BoardMode.getMode(this, gamePlayers, gameLength, gameCountry);
		
		int i;
		
		settlementRound = SettlementRound.S;

		wheel = new Wheel(this,mode.getWheelArmValues());
		mode.setWheelTokens(wheel);


		activePlayer = 0;
		players = new Player[gamePlayers.getAsNumber()];
		for(i = 0;i < players.length; i++) {
			players[i] = new Player(this, Color.toColor(i));
			players[i].gameStart();
		}
		players[0].setActive(true);

		unclaimedWonders = gameStartWonders();
		
		addLandscapeBuildings();
		unbuiltBuildings = roundBuildings();
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

	private List<Building> roundBuildings() {
		List<Building> buildings = mode.roundBuildings();
		for(Building building : buildings) {
			allBuildings.put(BuildingEnum.valueOf(building.getId()), building);
		}
		return buildings;
	}
	
	protected EnumMap<BuildingEnum, Building> getAllBuildings() {
		return allBuildings;
	}

	public List<Building> getFutureBuildings() {
		List<Building> buildings = mode.futureBuildings();
		
		return buildings;
	}
	
	public List<Settlement> getFutureSettlements() {
		List<Settlement> settlements = new ArrayList<Settlement>();
		for(SettlementEnum settlementId : SettlementEnum.values()) {
			Settlement settlement = settlementId.getInstance();
			if(settlement.getRound().ordinal() > getSettlementRound().ordinal() ||
					isSettling() && settlement.getRound().ordinal() == getSettlementRound().ordinal()) {
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

	public Wonder claimWonder() throws WeblaboraException {
		if(unclaimedWonders.size() == 0)
			throw new WeblaboraException("No more wonders to claim.");
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
		if(isSettling()) return 27.692f;
		else if(isExtraRound()) return 20.769f;
		else return 13.846f;
	}

	public void setSettlementRound(SettlementRound settlementRound) {
		this.settlementRound = settlementRound;
	}

	public SettlementRound getSettlementRound() {
		return settlementRound;
	}
	
	public boolean isRoundBeforeSettlement(int round) {
		return roundBeforeSettlement(round) != null;
	}
	
	public boolean isExtraRound(int round) {
		return mode.isExtraRound(round);
	}
	
	public SettlementRound roundBeforeSettlement(int round) {
		return mode.roundBeforeSettlement(round);
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
		
		//3 - check to see if grapes/stone should become active
		if(round == grapeActiveOnRound()) getWheel().getGrape().setActive(true);
		if(round == stoneActiveOnRound()) getWheel().getStone().setActive(true);
	}
	
	public void preSettling() {
		setSettlementRound(getSettlementRound().next());
		getMoveList().add(new HistoryEntry("Settlement ("+getSettlementRound()+")"));
	}
	
	public void preExtraRound() {
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
	
		if(!isGameOver()) {
			for (int i = 0; i < players.length; i++) {
				players[i].setActionsBeforeSettlement(actionsBeforeSettlement(i));
			}
		}
		
	}
	
	/**
	 * Called after every move.
	 */
	public void postMove() {
		mode.postMove();
	}
	
	public void postRound() {
		mode.postRound();
	}
	
	public void postSettlement() {
		//end of settlement round
		setSettling(false);
		
		List<Building> newBuildings = roundBuildings();
		unbuiltBuildings.addAll(newBuildings);
		for(Player player : players) {
			player.getUnbuiltSettlements().addAll(roundSettlements(settlementRound));
		}

		if(settlementRound == SettlementRound.E) {
			setGameOver(true);
			wheel.pushArm(round);
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
		moveInRound=1;
	}

	public int getRound() {
		return round;
	}

	public String getMove() {
		return mode.getMoveName();
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

	private int grapeActiveOnRound() {
		return mode.grapeActiveOnRound();
	}

	private int stoneActiveOnRound() {
		return mode.stoneActiveOnRound();
	}
	
	public int getMoveInRound() {
		return this.moveInRound;
	}
	protected void setMoveInRound(int moveInRound) {
		this.moveInRound = moveInRound;
	}

	protected void setRound(int round) {
		this.round = round;
	}

	public int getStartingPlayer() {
		return startingPlayer;
	}

	public void setStartingPlayer(int startingPlayer) {
		this.startingPlayer = startingPlayer % players.length;
	}
	
	public BoardMode getMode() {
		return this.mode;
	}
	
	public int actionsBeforeSettlement(int player) {
		
		int currentActivePlayer = getActivePlayer();
		int round = getRound();
		
		int actions = 0;
		
		
		
		// process current round
		if (isSettling()) {
			return 0;
		}
		
		if ((getMode() instanceof BoardModeTwoLongFrance || getMode() instanceof BoardModeTwoLongIreland)) {
			if (getRound() > getMode().getLastSettlementAfterRound() ) {
				return -1;
			}
			
		}
		
		for (int i = getMoveInRound(); i <= getMode().getMovesInRound() &&
				(i <= getPlayers().length || !mode.isExtraRound(round - 1)); i++) {
			
			if (player == currentActivePlayer) {
				actions++;
			}
			if (i == 2 || !(getMode() instanceof BoardModeTwoLongFrance || getMode()
					instanceof BoardModeTwoLongIreland)) {
				if (++currentActivePlayer >= players.length) {
					currentActivePlayer = 0;
				}
			}
		}
		
		// process other rounds until settlement
		for (round++; getMode().roundBeforeSettlement(round - 1) == null; round++) {
			
			if (mode.isExtraRound(round - 1)) {
				actions++;
			}
			else {
				for (int j = 1; j <= getMode().getMovesInRound(); j++) {
					if (player == currentActivePlayer) {
						actions++;
					}
					if (j == 2 || !(getMode() instanceof BoardModeTwoLongFrance || getMode()
							instanceof BoardModeTwoLongIreland)) {
						if (++currentActivePlayer >= players.length) {
							currentActivePlayer = 0;
						}
					}
				}
			}
			
		}
		
		return actions;
	}
}
