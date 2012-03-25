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

abstract class Dormitory extends Building {

	public Dormitory(String id, String stage, int players) {
		super("F37","D",0, "Dormitory", BuildCost.is().clay(3), 4, 3, EnumSet
				.of(PLAINS, HILLSIDE, COAST), true);
	}

	@Override
	public void use(Board board, UsageParam input) {
		Player player = board.getPlayer(board.getActivePlayer());
		
		//get a pottery
		player.addPottery(1);
		
		//additionally, convert straw+wood into bibles
		int iterations = Math.min(input.getStraw(), input.getWood());
		player.subtractStraw(iterations);
		player.subtractWood(iterations);
		player.addBooks(iterations);
	}
}
