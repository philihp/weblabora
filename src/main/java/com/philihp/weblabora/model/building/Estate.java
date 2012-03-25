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
import com.philihp.weblabora.model.WeblaboraException;
import com.philihp.weblabora.model.Wheel;

abstract class Estate extends Building {

	public Estate(String id, String stage, int players) {
		super("G39", "D", 4, "Estate", BuildCost.is().wood(2).stone(2), 6, 5,
				EnumSet.of(PLAINS, HILLSIDE, COAST), false);
	}

	@Override
	public void use(Board board, UsageParam input) throws WeblaboraException {
		Player player = board.getPlayer(board.getActivePlayer());
		
		player.subtractAll(input);
		int energy = (int)input.getEnergy();
		int food = (int)input.getFood();
		
		int iterations = 0;
		while(iterations++ < 2 && (energy >= 6 || food >= 10)) {
			energy -= 6;
			food -= 10;
			player.addBooks(1);
			player.addOrnament(1);
		}
	}
}
