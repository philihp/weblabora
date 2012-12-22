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
import com.philihp.weblabora.model.TerrainUseEnum;
import com.philihp.weblabora.model.UsageParamCoordinates;
import com.philihp.weblabora.model.WeblaboraException;

public class ForestHut extends BuildingCoordinateUsage {

	public ForestHut() {
		super("I29", SettlementRound.C, 3, "Forest Hut", BuildCost.is().clay(1)
				.straw(1), 5, 1, EnumSet.of(PLAINS, HILLSIDE, COAST), false);
	}

	@Override
	public void use(Board board, UsageParamCoordinates input)
			throws WeblaboraException {
		Player player = board.getPlayer(board.getActivePlayer());

		Terrain spot = player.getLandscape()
				.getTerrainAt(input.getCoordinate());
		if (spot.getTerrainUse() != TerrainUseEnum.FOREST)
			throw new WeblaboraException(getName() + " can't remove "
					+ spot.getTerrainUse() + " at " + input
					+ ", it can only remove FOREST");
		else
			spot.setTerrainUse(TerrainUseEnum.EMPTY);

		player.addSheep(2);
		player.addWood(2);
		player.addStone(1);
	}
}
