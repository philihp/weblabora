package com.philihp.weblabora.model.building;

import static com.philihp.weblabora.model.TerrainTypeEnum.COAST;
import static com.philihp.weblabora.model.TerrainTypeEnum.MOUNTAIN;
import static com.philihp.weblabora.model.TerrainTypeEnum.HILLSIDE;
import static com.philihp.weblabora.model.TerrainTypeEnum.PLAINS;
import static com.philihp.weblabora.model.TerrainTypeEnum.FOREST;

import java.util.EnumSet;
import java.util.Set;

import com.philihp.weblabora.model.Board;
import com.philihp.weblabora.model.BuildCost;
import com.philihp.weblabora.model.Coordinate;
import com.philihp.weblabora.model.Landscape;
import com.philihp.weblabora.model.Player;
import com.philihp.weblabora.model.SettlementRound;
import com.philihp.weblabora.model.Terrain;
import com.philihp.weblabora.model.TerrainTypeEnum;
import com.philihp.weblabora.model.UsageParam;
import com.philihp.weblabora.model.UsageParamCoordinates;
import com.philihp.weblabora.model.Wheel;
import com.philihp.weblabora.model.WeblaboraException;

public class ForestHut extends BuildingCoordinateUsage {

	public ForestHut() {
		super("I29", SettlementRound.C, 3, "Forest Hut",
				BuildCost.is().clay(1).straw(2), 5, 1, EnumSet.of(PLAINS,
						HILLSIDE, COAST), false);
	}

	@Override
	public void use(Board board, UsageParamCoordinates input) throws WeblaboraException {
		Player player = board.getPlayer(board.getActivePlayer());

                Terrain spot = activePlayer.getLandscape().getTerrainAt(input.getCoordinate());
                if(spot.getTerrainType() != TerrainTypeEnum.FOREST)
                        throw new WeblaboraException(getName()+" can't remove "+spot.getTerrainType()+" at "+input+", it can only remove FOREST");
                else
                        spot.setTerrainType(TerrainTypeEnum.PLAINS);

		player.addSheep(2);
		player.addWood(2);
		player.addStone(1);
	}
}
