package com.philihp.weblabora.model.building;

import static com.philihp.weblabora.model.TerrainTypeEnum.COAST;
import static com.philihp.weblabora.model.TerrainTypeEnum.HILLSIDE;
import static com.philihp.weblabora.model.TerrainTypeEnum.PLAINS;

import java.util.EnumSet;

import com.philihp.weblabora.model.Board;
import com.philihp.weblabora.model.BuildCost;
import com.philihp.weblabora.model.Player;
import com.philihp.weblabora.model.SettlementRound;
import com.philihp.weblabora.model.Terrain;
import com.philihp.weblabora.model.TerrainTypeEnum;
import com.philihp.weblabora.model.TerrainUseEnum;
import com.philihp.weblabora.model.UsageParamCoordinates;
import com.philihp.weblabora.model.WeblaboraException;

public class Carpentry extends BuildingCoordinateUsage {

	public Carpentry() {
		super("F10", SettlementRound.S, 4, "Carpentry", BuildCost.is().wood(2).clay(1), 0, 7,
				EnumSet.of(COAST, PLAINS, HILLSIDE), false);
	}

	@Override
	public void use(Board board, UsageParamCoordinates input) throws WeblaboraException {
		Player activePlayer = board.getPlayer(board.getActivePlayer());
		Terrain spot = activePlayer.getLandscape().getTerrainAt(input.getCoordinate());
		if(spot.getTerrainUse() != TerrainUseEnum.FOREST)
			throw new WeblaboraException("Carpentry can't remove "+spot.getTerrainUse()+" at "+input+", it can only remove FOREST");
		else
			spot.setTerrainType(TerrainTypeEnum.PLAINS);
	}
}
