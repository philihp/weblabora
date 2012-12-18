package com.philihp.weblabora.model.building;

import static com.philihp.weblabora.model.TerrainTypeEnum.COAST;
import static com.philihp.weblabora.model.TerrainTypeEnum.HILLSIDE;
import static com.philihp.weblabora.model.TerrainTypeEnum.PLAINS;

import java.util.EnumSet;

import com.philihp.weblabora.model.Board;
import com.philihp.weblabora.model.BuildCost;
import com.philihp.weblabora.model.Coordinate;
import com.philihp.weblabora.model.Landscape;
import com.philihp.weblabora.model.Player;
import com.philihp.weblabora.model.SettlementRound;
import com.philihp.weblabora.model.Terrain;
import com.philihp.weblabora.model.TerrainUseEnum;
import com.philihp.weblabora.model.UsageParamCoordinates;
import com.philihp.weblabora.model.WeblaboraException;

public class PrintingOffice extends BuildingCoordinateUsage {

	public PrintingOffice() {
		super("F38", SettlementRound.D, 1, "Printing Office",
				BuildCost.is().wood(1).stone(2), 5, 5, EnumSet.of(PLAINS,
						HILLSIDE, COAST), false);
	}

	@Override
	public void use(Board board, UsageParamCoordinates input) throws WeblaboraException {
		Player player = board.getPlayer(board.getActivePlayer());
		Landscape landscape = player.getLandscape();
		for(Coordinate coord : input.getCoordinates()) {
			Terrain terrain = landscape.getTerrainAt(coord);
			if(terrain == null) {
				throw new WeblaboraException(getName()+" is trying to clear forest at "+coord+" which doesn't belong to landscape");
			}
			else if(terrain.getTerrainUse() == TerrainUseEnum.FOREST) {
				player.addBooks(1);
				terrain.setTerrainType(PLAINS);
				terrain.setTerrainUse(TerrainUseEnum.EMPTY);
			}
			else
				throw new WeblaboraException(getName()+" is trying to clear forest at "+coord+" but found "+terrain.getTerrainUse());
		}
	}
}
