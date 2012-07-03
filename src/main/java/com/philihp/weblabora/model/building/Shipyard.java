package com.philihp.weblabora.model.building;

import static com.philihp.weblabora.model.TerrainTypeEnum.COAST;

import java.util.EnumSet;

import com.philihp.weblabora.model.Board;
import com.philihp.weblabora.model.BuildCost;
import com.philihp.weblabora.model.Player;
import com.philihp.weblabora.model.SettlementRound;
import com.philihp.weblabora.model.UsageParam;

public class Shipyard extends Building {

	public Shipyard() {
		super("G26", SettlementRound.B, 1, "Shipyard", BuildCost.is().clay(4).stone(1), -2, 15, EnumSet.of(COAST), false);
	}

	@Override
	public void use(Board board, UsageParam input) {
		Player player = board.getPlayer(board.getActivePlayer());
		player.subtractWood(2);
		player.addOrnament(1);
		player.addNickel(1);
	}
}
