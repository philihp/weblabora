package com.philihp.weblabora.model.building;

import static com.philihp.weblabora.model.TerrainTypeEnum.COAST;
import static com.philihp.weblabora.model.TerrainTypeEnum.MOUNTAIN;
import static com.philihp.weblabora.model.TerrainTypeEnum.HILLSIDE;
import static com.philihp.weblabora.model.TerrainTypeEnum.PLAINS;

import java.util.EnumSet;
import java.util.Set;

import com.philihp.weblabora.model.Board;
import com.philihp.weblabora.model.BuildCost;
import com.philihp.weblabora.model.Coordinate;
import com.philihp.weblabora.model.Landscape;
import com.philihp.weblabora.model.Player;
import com.philihp.weblabora.model.TerrainTypeEnum;
import com.philihp.weblabora.model.UsageParam;
import com.philihp.weblabora.model.UsageParamCoordinates;
import com.philihp.weblabora.model.Wheel;

public class PrintingOffice extends BuildingCoordinateUsage {

	public PrintingOffice() {
		super("F38", "D", 0, "Printing Office",
				BuildCost.is().wood(1).stone(2), 5, 5, EnumSet.of(PLAINS,
						HILLSIDE, COAST), false);
	}

	@Override
	public void use(Board board, UsageParamCoordinates input) {
		Player player = board.getPlayer(board.getActivePlayer());
		Landscape landscape = player.getLandscape();
		for(Coordinate coord : input.getCoordinates()) {
			landscape.getTerrainAt(coord);
			player.addBooks(1);
		}
	}
}
