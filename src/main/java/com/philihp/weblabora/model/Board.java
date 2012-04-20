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
import com.philihp.weblabora.model.building.Settlement;
import com.philihp.weblabora.model.building.SettlementEnum;

import static com.philihp.weblabora.model.building.BuildingEnum.*;

public class Board {
	
	public static final int[] PLOT_PURCHASE_PRICE = {3,4,4,5,5,5,6,6,7};
	
	public static final int[] DISTRICT_PURCHASE_PRICE = {2,3,4,4,5,5,6,7,8};

	protected final GamePlayers gamePlayers;

	protected final GameType gameType;

	protected Wheel wheel;

	protected Player[] players;

	private int activePlayer;

	private List<Building> unbuiltBuildings;
	
	private int plotsPurchased;
	
	private int districtsPurchased;
	
	private int startingPlayer;
	
	private StartingMarker startingMarker;
	
	private int round;

	private int moveInRound;

	private boolean settling;
	
	/**
	 * This makes lookups from {@link CommandUse CommandUse}
	 */
	private EnumMap<BuildingEnum, Building> allBuildings;

	private List<Wonder> unclaimedWonders;

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
		unclaimedWonders = gameStartWonders();

		activePlayer = 0;
		
		allBuildings = generateBuildingsMap();
		
		generateSettlements(players);
		
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


	private void generateSettlements(Player[] players) {
		for (Player player : players) {
			player.setUnbuiltSettlements(new ArrayList<Settlement>(8));
			for (SettlementEnum settlementId : SettlementEnum.values()) {
				Settlement settlement = settlementId.getInstance();
				if ("".equals(settlement.getStage()))
					player.getUnbuiltSettlements().add(settlement);
			}
		}
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
	
	public static boolean isRoundBeforeSettlement(int round) {
		return roundBeforeSettlement(round) != null;
	}
	
	public static String roundBeforeSettlement(int round) {
		switch (round) {
		case 6:
			return "A";
		case 9:
			return "B";
		case 15:
			return "C";
		case 18:
			return "D";
		case 24:
			return "E";
		default:
			return null;
		}
	}

	/**
	 * Called before every round.
	 */
	public void preRound() {

		//1 - reset clergymen
		for(Player player : getPlayers()) {
			if(player.isClergymenAllPlaced())
				player.resetClergymen();
		}
		
		//2 - push arm
		getWheel().pushArm();
		
		//3 - settlement
		
	}
	
	/**
	 * Called before every move.
	 */
	public void preMove() {
		if(!isSettling() && moveInRound == 1) {
			preRound();
		}
	}
	
	/**
	 * Called after every move.
	 */
	public void postMove() {
		nextActivePlayer();
		
		++moveInRound;
		
		if(isSettling() && moveInRound == 5) {
			System.out.println("------End Settlement------");
			//end of settlement round
			setSettling(false);
			round++;
		}
		else if(!isSettling() && moveInRound == 6) {
			System.out.println("======END OF ROUND "+round+"======");
			//end of normal round
			moveInRound = 1;
			//end of round
			if(isRoundBeforeSettlement(round)) {
				System.out.println("------Begin Settlement------");
				setSettling(true);
			}
			else {
				round++;
			}
			
			postRound();
		}
	}
	
	/**
	 * Called after every round.
	 */
	public void postRound() {
		//5 -- pass starting player
		if(++startingPlayer == players.length) startingPlayer = 0;
		startingMarker.setOwner(players[startingPlayer]);
	}

	public int getRound() {
		return round;
	}

	public String getMove() {
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

}
