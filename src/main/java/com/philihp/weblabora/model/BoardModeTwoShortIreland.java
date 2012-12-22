package com.philihp.weblabora.model;

import static com.philihp.weblabora.model.Wheel.Position.E;
import static com.philihp.weblabora.model.Wheel.Position.K;

import java.util.ArrayList;
import java.util.List;

import com.philihp.weblabora.model.building.Building;
import com.philihp.weblabora.model.building.BuildingEnum;

public class BoardModeTwoShortIreland extends BoardMode {

	private static final GamePlayers PLAYERS = GamePlayers.TWO;
	private static final GameLength LENGTH = GameLength.SHORT;
	private static final GameCountry COUNTRY = GameCountry.IRELAND;

	protected BoardModeTwoShortIreland(Board board) {
		super(board);
	}

	@Override
	public int[] getWheelArmValues() {
		return new int[] { 0, 1, 2, 2, 3, 4, 4, 5, 6, 6, 7, 8, 10 };
	}

	@Override
	public List<Building> roundBuildings() {
		List<Building> buildings = new ArrayList<Building>();
		for (BuildingEnum buildingId : BuildingEnum.values()) {
			
			char c = buildingId.toString().charAt(0);
			if(c != 'G' && c != 'I') continue;
			
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
		// two player long game uses all buildings except C-grapevine, C-quarry
		// and Carpentry
		for (BuildingEnum buildingId : BuildingEnum.values()) {
			
			char c = buildingId.toString().charAt(0);
			if(c != 'G' && c != 'I') continue;
			
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
		case 6:
			return SettlementRound.A;
		case 13:
			return SettlementRound.B;
		case 20:
			return SettlementRound.C;
		case 27:
			return SettlementRound.D;
		default:
			return null;
		}
	}
	
	@Override
	public void postMove() {
		board.setMoveInRound(board.getMoveInRound() + 1);
		if(board.isSettling()) {
			board.nextActivePlayer();
			if(board.getMoveInRound() > 2) {
				board.postSettlement();
			}
		}
		else {
			if(board.getMoveInRound() > 2) {
				board.nextActivePlayer();  
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
		if (board.isExtraRound())
			return "extra";
		switch (board.getMoveInRound()) {
		case 1:
			return "first half of first";
		case 2:
			return "second half of first";
		case 3:
			return "second";
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
		return true;
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

}
