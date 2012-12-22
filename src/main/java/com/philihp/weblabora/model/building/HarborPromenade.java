package com.philihp.weblabora.model.building;

import static com.philihp.weblabora.model.TerrainTypeEnum.COAST;

import java.util.EnumSet;

import com.philihp.weblabora.model.Board;
import com.philihp.weblabora.model.BuildCost;
import com.philihp.weblabora.model.Player;
import com.philihp.weblabora.model.SettlementRound;
import com.philihp.weblabora.model.UsageParam;
import com.philihp.weblabora.model.WeblaboraException;

public class HarborPromenade extends Building {

	public HarborPromenade() {
		super("F11", SettlementRound.S, 1, "Harbor Promenade", BuildCost.is().wood(1).stone(1), 7, 1, EnumSet.of(COAST), false);
	}

	@Override
	public void use(Board board, UsageParam param) throws WeblaboraException {
		Player player = board.getPlayer(board.getActivePlayer());
		player.addWood(1);
		player.addWine(1);
		player.addPenny(1);
		player.addPottery(1);
	}
}
