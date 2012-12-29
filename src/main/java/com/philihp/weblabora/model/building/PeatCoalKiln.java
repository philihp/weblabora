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

public class PeatCoalKiln extends Building {

	public PeatCoalKiln() {
		super("G07", SettlementRound.S, 1, "Peat Coal Kiln", BuildCost.is().clay(1), -2, 4, EnumSet.of(COAST, PLAINS, HILLSIDE), false);
	}

	@Override
	public void use(Board board, UsageParam input) {
		Player player = board.getPlayer(board.getActivePlayer());
		player.addCoal(1);
		player.addPenny(1);

		// turn peat into coal
		int quantity = input.getPeat();
		player.subtractPeat(quantity);
		player.addCoal(quantity);
	}
}
