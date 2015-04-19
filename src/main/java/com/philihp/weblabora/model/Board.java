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
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.philihp.weblabora.model.building.Building;
import com.philihp.weblabora.model.building.BuildingEnum;
import com.philihp.weblabora.model.building.ClayMound;
import com.philihp.weblabora.model.building.CloisterOffice;
import com.philihp.weblabora.model.building.Farmyard;
import com.philihp.weblabora.model.building.Settlement;
import com.philihp.weblabora.model.building.SettlementEnum;

public class Board {

	private BoardMode mode;

	protected Wheel wheel;

	protected Player[] players;

	private int activePlayer;

	private List<Building> unbuiltBuildings;

	private int startingPlayer;

	private StartingMarker startingMarker;

	private int round;

	private SettlementRound settlementRound;

	private int moveInRound;

	private boolean settling;

	private boolean extraRound;

	private boolean started = false;

	private boolean gameOver = false;

	private String nextState;

	private List<String> moveList = new ArrayList<String>();

	/**
	 * This makes lookups from {@link CommandUse CommandUse}
	 */
	private EnumMap<BuildingEnum, Building> allBuildings =
			new EnumMap<BuildingEnum, Building>(BuildingEnum.class);

	private List<Wonder> unclaimedWonders;

	private GamePlayers gamePlayers;
	private GameLength gameLength = GameLength.NULL;
	private GameCountry gameCountry;

	public Board() {
	}

	public void setPlayers(GamePlayers gamePlayers) {
		this.gamePlayers = gamePlayers;
	}

	public void setLength(GameLength gameLength) {
		this.gameLength = gameLength;
	}

	public void setCountry(GameCountry gameCountry) {
		this.gameCountry = gameCountry;
	}

	public void start() throws WeblaboraException {
		this.mode = BoardMode.getMode(this, gamePlayers, gameLength, gameCountry);
		this.started = true;

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

		mode.setup();
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

	protected void setPlayers(Player[] players) {
		this.players = players;
	}

	public int getActivePlayer() {
		//this makes it so any builds/uses during solo neutral building use player 0's inventory
		if(mode.isNeutralPlayerUsed()) return 0;
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
			mode.customizeLandscape(players[0].getLandscape());
		}
		if(players.length >= 2) {
			allBuildings.put(LG1, (ClayMound)players[1].getLandscape().getTerrainAt(new Coordinate(4, 0)).getErection());
			allBuildings.put(LG2, (Farmyard)players[1].getLandscape().getTerrainAt(new Coordinate(2, 1)).getErection());
			allBuildings.put(LG3, (CloisterOffice)players[1].getLandscape().getTerrainAt(new Coordinate(4, 1)).getErection());
			mode.customizeLandscape(players[1].getLandscape());
		}
		if(players.length >= 3) {
			allBuildings.put(LB1, (ClayMound)players[2].getLandscape().getTerrainAt(new Coordinate(4, 0)).getErection());
			allBuildings.put(LB2, (Farmyard)players[2].getLandscape().getTerrainAt(new Coordinate(2, 1)).getErection());
			allBuildings.put(LB3, (CloisterOffice)players[2].getLandscape().getTerrainAt(new Coordinate(4, 1)).getErection());
			mode.customizeLandscape(players[2].getLandscape());
		}
		if(players.length >= 4) {
			allBuildings.put(LW1, (ClayMound)players[3].getLandscape().getTerrainAt(new Coordinate(4, 0)).getErection());
			allBuildings.put(LW2, (Farmyard)players[3].getLandscape().getTerrainAt(new Coordinate(2, 1)).getErection());
			allBuildings.put(LW3, (CloisterOffice)players[3].getLandscape().getTerrainAt(new Coordinate(4, 1)).getErection());
			mode.customizeLandscape(players[3].getLandscape());
		}
	}

	public Building findBuildingInstance(BuildingEnum buildingId) {
		return allBuildings.get(buildingId);
	}

	private List<Wonder> gameStartWonders() {
		List<Wonder> wonders = new ArrayList<Wonder>(8);
		wonders.add(new Wonder("A"));
		wonders.add(new Wonder("B"));
		wonders.add(new Wonder("C"));
		wonders.add(new Wonder("D"));
		wonders.add(new Wonder("E"));
		wonders.add(new Wonder("F"));
		wonders.add(new Wonder("G"));
		wonders.add(new Wonder("H"));
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

	public void testValidity() throws WeblaboraException {
		for(Player player : players) {
			player.testValidity();
		}
	}

	/**
	 * @deprecated Moved to BoardMode.purchasePlot();
	 */
	public int purchasePlot() {
		return mode.purchasePlot();
	}

	/**
	 * @deprecated Moved to BoardMode.purchaseDistrict();
	 */
	public int purchaseDistrict() {
		return mode.purchaseDistrict();
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

	public String getNextState() {
		return nextState;
	}

	public void setNextState(String nextState) {
		this.nextState = nextState;
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

		getMoveList().add("Round " + round);

		//1 - reset clergymen
		for(Player player : getPlayers()) {
			if(player.isClergymenAllPlaced())
				player.resetClergymen();
		}

		//2 - push arm
		getWheel().pushArm(round);
		mode.preRound();

		//3 - check to see if grapes/stone should become active
		if(round == mode.grapeActiveOnRound()) getWheel().getGrape().setActive(true);
		if(round == mode.stoneActiveOnRound()) getWheel().getStone().setActive(true);
		if(round == mode.jokerActiveOnRound()) getWheel().getJoker().setActive(true);
	}

	public void preSettling() {
		setSettlementRound(getSettlementRound().next());
		getMoveList().add("Settlement ("+getSettlementRound()+")");
	}

	public void preExtraRound() {
		if(getMode().isPriorSpecialInExtraRound()) {
			for(Player player : players) {
				player.getPrior().clearLocation();
			}
		}
		else {
			for(Player player : getPlayers()) {
				if(player.isClergymenAllPlaced())
					player.resetClergymen();
			}
		}

		setExtraRound(true);
		getMoveList().add("Extra Round");
	}

	/**
	 * Called before every move.
	 */
	public void preMove(String state) {
		if(!isGameStarted()) return;
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

		getMoveList().add( getPlayer(getActivePlayer()).getColor() + ":" +state);

		if(!isGameOver()) {
			for (int i = 0; i < players.length; i++) {
				players[i].setActionsBeforeSettlement(actionsBeforeSettlement(i));
			}
		}

	}

	/**
	 * Called after every move.
	 */
	public void postMove() throws WeblaboraException {
		if(isGameStarted()) {
			mode.postMove();
		}
		else {
			start();
		}
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
			getMoveList().add("Game Over");
		}

		round++;
		moveInRound=1;
	}

	public boolean isGameOver() {
		return gameOver;
	}

	@JsonIgnore
	public boolean isGameStarted() { return started; }

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

	/**
	 * @deprecated Moved to BoardMode.getPlotCosts();
	 */
	public int[] getPlotCosts() {
		return mode.getPlotCosts();
	}

	/**
	 * @deprecated Moved to BoardMode.getDistrictCosts();
	 */
	public int[] getDistrictCosts() {
		return mode.getDistrictCosts();
	}

	public List<String> getMoveList() {
		return moveList;
	}

	public List<String> getMoveListReversed() {
		List<String> newList = new ArrayList<String>(getMoveList());
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

public void distributeBonusProduction(UsageParam item) {
  getMode().distributeBonusProduction(item);
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

			if(round > 100) {
				// escape for when we don't actually find the end of the game?
				return 0;
			}

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
