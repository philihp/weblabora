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
import com.philihp.weblabora.model.WeblaboraException;

public class ForgersWorkshop extends Building {

	public ForgersWorkshop() {
		super("F35", SettlementRound.D, 1, "Forger's Workshop", BuildCost.is()
				.clay(2).straw(1), 2, 4, EnumSet.of(PLAINS, HILLSIDE, COAST),
				false);
	}

	@Override
	public void use(Board board, UsageParam input) throws WeblaboraException {
		Player player = board.getPlayer(board.getActivePlayer());
		if (input.getCoins() >= 5) {
			player.addReliquary(1);
			player.subtractCoins(5);
		}
		if (input.getCoins() >= 15) {
			player.addReliquary(1);
			player.subtractCoins(10);
		}
	}
}
