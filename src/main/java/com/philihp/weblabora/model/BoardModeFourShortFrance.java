package com.philihp.weblabora.model;

import static com.philihp.weblabora.model.Wheel.Position.D;
import static com.philihp.weblabora.model.Wheel.Position.F;

import java.util.ArrayList;
import java.util.List;

import com.philihp.weblabora.model.building.Building;
import com.philihp.weblabora.model.building.BuildingEnum;

public class BoardModeFourShortFrance extends BoardMode {

	private static final GamePlayers PLAYERS = GamePlayers.FOUR;
	private static final GameLength LENGTH = GameLength.SHORT;
	private static final GameCountry COUNTRY = GameCountry.FRANCE;

	protected BoardModeFourShortFrance(Board board) {
		super(board);
	}

	@Override
	protected int[] getWheelArmValues() {
		return new int[] { 0, 2, 3, 4, 5, 6, 6, 7, 7, 8, 8, 9, 10 };
	}

	@Override
	public List<Building> roundBuildings() {
		List<Building> buildings = new ArrayList<Building>();
		for (BuildingEnum buildingId : BuildingEnum.values()) {

			char c = buildingId.toString().charAt(0);
			if(c != 'G' && c != 'F') continue;

			Building building = buildingId.getInstance();
			if (board.getSettlementRound().equals(building.getStage())
					// less than, not less than or equal to...
					&& building.getPlayers().ordinal() < PLAYERS.ordinal()) {
				buildings.add(building);
			}
		}
		return buildings;
	}

	@Override
	public List<Building> futureBuildings() {
		List<Building> buildings = new ArrayList<Building>();
		for(BuildingEnum buildingId : BuildingEnum.values()) {

			char c = buildingId.toString().charAt(0);
			if(c != 'G' && c != 'F') continue;

			Building building = buildingId.getInstance();
			if(board.getAllBuildings().containsKey(buildingId) == false
					&& building.getPlayers().ordinal() <= PLAYERS.ordinal()) {
				buildings.add(building);
			}
		}
		return buildings;
	}

	@Override
	public boolean isExtraRound(int round) {
		return round >= 24;
	}

	@Override
	public SettlementRound roundBeforeSettlement(int round) {
		switch (round) {
		case 2:
			return SettlementRound.A;
		case 4:
			return SettlementRound.B;
		case 6:
			return SettlementRound.C;
		case 8:
			return SettlementRound.D;
		case 12:
			return SettlementRound.E;
		default:
			return null;
		}
	}

	@Override
	public void postMove() {
		board.nextActivePlayer();
		board.setMoveInRound(board.getMoveInRound()+1);
		if(board.isExtraRound() && board.getMoveInRound() == board.players.length+1) {
			board.postExtraRound();
		}
		if(board.isSettling() && board.getMoveInRound() == board.players.length+1) {
			board.postSettlement();
		}
		else if(!board.isSettling() && board.getMoveInRound() == board.players.length+2) {
			board.postRound();
		}
	}

	@Override
	public void postRound() {
		board.setMoveInRound(1);

		if(isExtraRound(board.getRound())) {
			board.setRound(board.getRound()+1);
			board.setExtraRound(true);
		}
		else if(board.isRoundBeforeSettlement(board.getRound())) {
			board.setSettling(true);
		}
		else {
			board.setRound(board.getRound()+1);
		}

		//5 -- pass starting player
		board.setStartingPlayer(board.getStartingPlayer() + 1);
		board.getStartingMarker().setOwner(board.players[board.getStartingPlayer()]);
	}

	@Override
	public String getMoveName() {
		if(board.isExtraRound()) return "extra";
		switch (board.getMoveInRound()) {
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
			throw new RuntimeException("Illegal Move Number " + board.getMoveInRound());
		}
	}

	@Override
	public int grapeActiveOnRound() {
		return 4;
	}

	@Override
	public int stoneActiveOnRound() {
		return 6;
	}

	@Override
	public void setWheelTokens(Wheel wheel) {
		wheel.grape.setPosition(D);
		wheel.stone.setPosition(F);
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
	public void customizeLandscape(Landscape landscape) {
		landscape.getTerrainAt(new Coordinate(0,0)).setTerrainUse(TerrainUseEnum.EMPTY);
		landscape.getTerrainAt(new Coordinate(1,0)).setTerrainUse(TerrainUseEnum.EMPTY);
		super.customizeLandscape(landscape);
	}

	@Override
	public boolean isProductionBonusActive() {
		return true;
	}

	@Override
	public boolean isSecondLayBrotherUsed() {
		return false;
	}

	@Override
	public int getMovesInRound() {
		return 5;
	}

	@Override
	public int getLastSettlementAfterRound() {
		return 13;
	}

	@Override
	protected boolean isRoundStartBonusActive() {
		return true;
	}

	@Override
	public boolean isGrapesUsed() {
		return true;
	}

	@Override
	boolean isNeutralBuildingPhase() {
		return false;
	}

	@Override
	public boolean isStoneUsed() {
		return true;
	}

}
