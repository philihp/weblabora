package com.philihp.weblabora.model;

import static com.philihp.weblabora.model.Wheel.Position.E;
import static com.philihp.weblabora.model.Wheel.Position.K;
import static com.philihp.weblabora.model.building.BuildingEnum.LW1;
import static com.philihp.weblabora.model.building.BuildingEnum.LW2;
import static com.philihp.weblabora.model.building.BuildingEnum.LW3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.philihp.weblabora.model.Wheel.Position;
import com.philihp.weblabora.model.building.BuildersMarket;
import com.philihp.weblabora.model.building.Building;
import com.philihp.weblabora.model.building.BuildingEnum;
import com.philihp.weblabora.model.building.ClayMound;
import com.philihp.weblabora.model.building.CloisterOffice;
import com.philihp.weblabora.model.building.Farmyard;

public class BoardModeOneFrance extends BoardMode {

	private static final GamePlayers PLAYERS = GamePlayers.ONE;
	private static final GameLength LENGTH = GameLength.NULL;
	private static final GameCountry COUNTRY = GameCountry.FRANCE;
	
	private Player neutralPlayer = null;

	protected BoardModeOneFrance(Board board) {
		super(board);
	}

	@Override
	public int[] getWheelArmValues() {
		return new int[] { 0, 2, 3, 4, 5, 6, 6, 7, 7, 8, 8, 9, 10 };
	}

	@Override
	public List<Building> roundBuildings() {
		List<Building> buildings = new ArrayList<Building>();
		// solo game uses all buildings except either grapevine, C-quarry, and Carpentry
		for (BuildingEnum buildingId : BuildingEnum.values()) {
			if (buildingId == BuildingEnum.F10)
				continue;
			if (buildingId == BuildingEnum.F14)
				continue;
			if (buildingId == BuildingEnum.F31)
				continue;
			if (buildingId == BuildingEnum.F29)
				continue;
			
			char c = buildingId.toString().charAt(0);
			if(c != 'G' && c != 'F') continue;
			
			Building building = buildingId.getInstance();
			if (board.getSettlementRound().equals(building.getStage())) {
				buildings.add(building);
			}
		}
		return buildings;
	}

	@Override
	public List<Building> futureBuildings() {
		List<Building> buildings = new ArrayList<Building>();
		// solo game uses all buildings except either grapevine, C-quarry, and Carpentry
		// and Carpentry
		for (BuildingEnum buildingId : BuildingEnum.values()) {
			if (buildingId == BuildingEnum.F10)
				continue;
			if (buildingId == BuildingEnum.F14)
				continue;
			if (buildingId == BuildingEnum.F31)
				continue;
			if (buildingId == BuildingEnum.F29)
				continue;
			
			char c = buildingId.toString().charAt(0);
			if(c != 'G' && c != 'F') continue;
			
			Building building = buildingId.getInstance();
			if (board.getAllBuildings().containsKey(buildingId) == false
					&& building.getStage().equals("L") == false) {
				buildings.add(building);
				board.getAllBuildings().put(
						BuildingEnum.valueOf(building.getId()), building);
			}
		}
		return buildings;
	}

	@Override
	public boolean isExtraRound(int round) {
		// there is no extra round for TWO
		return false;
	}

	@Override
	public SettlementRound roundBeforeSettlement(int round) {
		switch (round) {
		case 11:
			return SettlementRound.A;
		case 15:
			return SettlementRound.B;
		case 21:
			return SettlementRound.C;
		case 25:
			return SettlementRound.D;
		default:
			return null;
		}
	}
	
	@Override
	public void postMove() {
		//clear any coins that may have been paid to neutral player
		getNeutralPlayer().setPenny(0);
		
		board.setMoveInRound(board.getMoveInRound() + 1);
		if(board.isSettling()) {
			board.nextActivePlayer();
			if(board.getMoveInRound() > 2) {
				board.postSettlement();
			}
		}
		else {
			if(board.getMoveInRound() > 2) {
				board.postRound();
			}
		}
	}

	@Override
	public void postRound() {
		board.setMoveInRound(1);

		if (isExtraRound(board.getRound())) {
			board.setRound(board.getRound() + 1);
			board.setExtraRound(true);
		} else if (board.isRoundBeforeSettlement(board.getRound())) {
			board.setSettling(true);
		} else {
			board.setRound(board.getRound() + 1);
		}

		// begin 2-player end-game detection.
		if (board.isSettling() == false
				&& board.getSettlementRound() == SettlementRound.D
				&& board.getUnbuiltBuildings().size() <= 3) {
			board.setGameOver(true);
			board.getMoveList().add(new HistoryEntry("Game Over"));
		}
		// end 2-player end-game detection.

		board.setStartingPlayer(board.getStartingPlayer() + 1);
		board.getStartingMarker().setOwner(
				board.players[board.getStartingPlayer()]);
	}

	@Override
	public String getMoveName() {
		switch (board.getMoveInRound()) {
		case 1:
			return "first half of";
		case 2:
			return "second half of";
		default:
			throw new RuntimeException("Illegal Move Number "
					+ board.getMoveInRound());
		}
	}

	@Override
	public int grapeActiveOnRound() {
		return 11;
	}

	@Override
	public int stoneActiveOnRound() {
		return 18;
	}
	
	@Override
	public int jokerActiveOnRound() {
		return 12;
	}

	@Override
	public void setWheelTokens(Wheel wheel) {
		wheel.grape.setPosition(K);
		wheel.stone.setPosition(E);
	}

	@Override
	public GamePlayers getPlayers() {
		return PLAYERS;
	}

	@Override
	public GameCountry getCountry() {
		return COUNTRY;
	}

	@Override
	public GameLength getLength() {
		return LENGTH;
	}

	@Override
	public boolean isProductionBonusActive() {
		return false;
	}

	@Override
	public int getMovesInRound() {
		return 1;
	}
	
	@Override
	public int getLastSettlementAfterRound() {
		return 28;
	}

	@Override
	protected boolean isRoundStartBonusActive() {
		return false;
	}

	public static final int[] PLOT_PURCHASE_PRICE = {7,6,6,5,5,5,4,4,3};
	
	public static final int[] DISTRICT_PURCHASE_PRICE = {8,7,6,5,5,4,4,3,2};
	
	private int plotsPurchased;
	
	private int districtsPurchased;
	
	@Override
	public int[] getPlotCosts() {
		return Arrays.copyOfRange(PLOT_PURCHASE_PRICE, plotsPurchased, PLOT_PURCHASE_PRICE.length);
	}

	@Override
	public int[] getDistrictCosts() {
		return Arrays.copyOfRange(DISTRICT_PURCHASE_PRICE, districtsPurchased, DISTRICT_PURCHASE_PRICE.length); 
	}

	@Override
	public int purchasePlot() {
		return PLOT_PURCHASE_PRICE[districtsPurchased++];
	}

	@Override
	public int purchaseDistrict() {
		return DISTRICT_PURCHASE_PRICE[districtsPurchased++];
	}
	
	@Override
	public void setup() {
		Player player = board.getPlayer(0);
		player.setWood(0);
		player.setClay(0);
		player.setPeat(0);
		player.setPenny(0);
		player.setGrain(0);
		player.setSheep(0);
		
		neutralPlayer = new Player(board,Color.WHITE);
		Player[] players = new Player[2];
		players[0] = board.getPlayer(0);
		players[1] = neutralPlayer;
		board.setPlayers(players);

		neutralPlayer.getLandscape().getTerrainAt(new Coordinate(0,0)).setTerrainUse(TerrainUseEnum.EMPTY);
		neutralPlayer.getLandscape().getTerrainAt(new Coordinate(1,0)).setTerrainUse(TerrainUseEnum.EMPTY);
		neutralPlayer.getLandscape().getTerrainAt(new Coordinate(2,0)).setTerrainUse(TerrainUseEnum.EMPTY);
		neutralPlayer.getLandscape().getTerrainAt(new Coordinate(0,1)).setTerrainUse(TerrainUseEnum.EMPTY);
		neutralPlayer.getLandscape().getTerrainAt(new Coordinate(1,1)).setTerrainUse(TerrainUseEnum.EMPTY);

		Building buildersMarket = null;
		//TODO probably an easier way to do this
		for(Building possibleBuilding : board.getUnbuiltBuildings()) {
			if(possibleBuilding.getId().equals(BuildersMarket.ID)) {
				buildersMarket = possibleBuilding;
				board.getUnbuiltBuildings().remove(buildersMarket);
				break;
			}
		}
		
		neutralPlayer.getLandscape().getTerrainAt(new Coordinate(0,0)).setErection(buildersMarket);

		board.getAllBuildings().put(LW1, (ClayMound)neutralPlayer.getLandscape().getTerrainAt(new Coordinate(4, 0)).getErection());
		board.getAllBuildings().put(LW2, (Farmyard)neutralPlayer.getLandscape().getTerrainAt(new Coordinate(2, 1)).getErection());
		board.getAllBuildings().put(LW3, (CloisterOffice)neutralPlayer.getLandscape().getTerrainAt(new Coordinate(4, 1)).getErection());
	}
	
	@Override
	public Player getNeutralPlayer() {
		return neutralPlayer;
	}
	
	@Override
	public boolean isGrapesUsed() {
		return false;
	}
}
