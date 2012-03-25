package com.philihp.weblabora.model.building;

import static com.philihp.weblabora.model.TerrainTypeEnum.COAST;
import static com.philihp.weblabora.model.TerrainTypeEnum.MOUNTAIN;
import static com.philihp.weblabora.model.TerrainTypeEnum.HILLSIDE;
import static com.philihp.weblabora.model.TerrainTypeEnum.PLAINS;

import java.util.EnumSet;
import java.util.Set;

import com.philihp.weblabora.model.Board;
import com.philihp.weblabora.model.BuildCost;
import com.philihp.weblabora.model.Player;
import com.philihp.weblabora.model.TerrainTypeEnum;
import com.philihp.weblabora.model.UsageParam;
import com.philihp.weblabora.model.Wheel;

public class HouseOfTheBrotherhood extends Building {

	public HouseOfTheBrotherhood() {
		super("G41", "D", 0, "House of the Brotherhood", BuildCost.is().clay(1)
				.stone(1), 3, 3, EnumSet.of(PLAINS, HILLSIDE, COAST), true);
	}

	@Override
	public void use(Board board, UsageParam input) {
		Player player = board.getPlayer(board.getActivePlayer());
		player.subtractCoins(5);

		int cloisters = 0;
		for (Building building : player.getLandscape().getBuildings()) {
			if (building.isCloister())
				cloisters++;
		}
		player.addBonusPoints(2*cloisters);
	}
}
