package com.philihp.weblabora.model.building;

import static com.philihp.weblabora.model.TerrainTypeEnum.COAST;
import static com.philihp.weblabora.model.TerrainTypeEnum.HILLSIDE;
import static com.philihp.weblabora.model.TerrainTypeEnum.PLAINS;

import java.util.EnumSet;

import com.philihp.weblabora.model.Board;
import com.philihp.weblabora.model.BuildCost;
import com.philihp.weblabora.model.Player;
import com.philihp.weblabora.model.SettlementRound;
import com.philihp.weblabora.model.UsageParam;

public class Dormitory extends Building {

	public Dormitory() {
		super("F37", SettlementRound.D, 1, "Dormitory", BuildCost.is().clay(3),
				4, 3, EnumSet.of(PLAINS, HILLSIDE, COAST), true);
	}

	@Override
	public void use(Board board, UsageParam input) {
		Player player = board.getPlayer(board.getActivePlayer());

		// get a pottery
		player.addPottery(1);

		// additionally, convert straw+wood into bibles
		int iterations = Math.min(input.getStraw(), input.getWood());
		player.subtractStraw(iterations);
		player.subtractWood(iterations);
		player.addBooks(iterations);
	}
}
