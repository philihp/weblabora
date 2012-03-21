package com.philihp.weblabora.model.building;

import static com.philihp.weblabora.model.TerrainTypeEnum.COAST;
import static com.philihp.weblabora.model.TerrainTypeEnum.HILLSIDE;
import static com.philihp.weblabora.model.TerrainTypeEnum.PLAINS;

import java.util.EnumSet;
import java.util.Set;

import com.philihp.weblabora.model.Board;
import com.philihp.weblabora.model.BuildCost;
import com.philihp.weblabora.model.Player;
import com.philihp.weblabora.model.Terrain;
import com.philihp.weblabora.model.TerrainTypeEnum;
import com.philihp.weblabora.model.UsageParam;
import com.philihp.weblabora.model.WeblaboraException;

public class Carpentry extends AbstractBuilding {

	public Carpentry() {
		super("F10", "", 4, "Carpentry", BuildCost.is().wood(2).clay(1), 0, 7,
				EnumSet.of(COAST, PLAINS, HILLSIDE), false);
	}

	@Override
	public void use(Board board, UsageParam input) throws WeblaboraException {
		Player activePlayer = board.getPlayer(board.getActivePlayer());
		Terrain spot = activePlayer.getLandscape().getTerrainAt(input.getX(), input.getY());
		if(spot.getTerrainType() != TerrainTypeEnum.FOREST)
			throw new WeblaboraException("Carpentry can't remove "+spot.getTerrainType()+" at "+input+", it can only remove FOREST");
		else
			spot.setTerrainType(TerrainTypeEnum.PLAINS);
	}
}
