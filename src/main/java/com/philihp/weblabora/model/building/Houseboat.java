package com.philihp.weblabora.model.building;

import static com.philihp.weblabora.model.TerrainTypeEnum.WATER;

import java.util.EnumSet;

import com.philihp.weblabora.model.Board;
import com.philihp.weblabora.model.BuildCost;
import com.philihp.weblabora.model.Player;
import com.philihp.weblabora.model.SettlementRound;
import com.philihp.weblabora.model.UsageParam;
import com.philihp.weblabora.model.WeblaboraException;

public class Houseboat extends Building {

	public Houseboat() {
		super("I11", SettlementRound.S, 1, "Houseboat", BuildCost.is().wood(1), 6, 4, EnumSet.of(WATER), false);
	}

	@Override
	public void use(Board board, UsageParam param) throws WeblaboraException {
		Player player = board.getPlayer(board.getActivePlayer());
		player.addWood(1);
		player.addHops(1);
		player.addPenny(1);
		player.addPeat(1);
	}
}
