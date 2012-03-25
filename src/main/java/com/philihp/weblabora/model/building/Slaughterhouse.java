package com.philihp.weblabora.model.building;

import static com.philihp.weblabora.model.TerrainTypeEnum.COAST;
import static com.philihp.weblabora.model.TerrainTypeEnum.HILLSIDE;
import static com.philihp.weblabora.model.TerrainTypeEnum.PLAINS;

import java.util.EnumSet;
import java.util.Set;

import com.philihp.weblabora.model.Board;
import com.philihp.weblabora.model.BuildCost;
import com.philihp.weblabora.model.Player;
import com.philihp.weblabora.model.TerrainTypeEnum;
import com.philihp.weblabora.model.UsageParam;

public class Slaughterhouse extends Building {

	public Slaughterhouse() {
		super("G19", "A", 0, "Slaughterhouse", BuildCost.is().wood(2).clay(2), -3, 8, EnumSet.of(COAST, PLAINS, HILLSIDE),
				false);
	}

	@Override
	public void use(Board board, UsageParam input) {
		Player player = board.getPlayer(board.getActivePlayer());
		
		player.subtractSheep(input.getSheep());
		player.subtractStraw(input.getStraw());
		player.subtractGrain(input.getGrain());
		player.addMeat(input.getSheep());
	}
}
