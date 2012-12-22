package com.philihp.weblabora.model.building;

import static com.philihp.weblabora.model.TerrainTypeEnum.COAST;
import static com.philihp.weblabora.model.TerrainTypeEnum.HILLSIDE;
import static com.philihp.weblabora.model.TerrainTypeEnum.PLAINS;

import java.util.EnumSet;

import com.philihp.weblabora.model.Board;
import com.philihp.weblabora.model.BuildCost;
import com.philihp.weblabora.model.Player;
import com.philihp.weblabora.model.SettlementRound;
import com.philihp.weblabora.model.UsageParamSingle;
import com.philihp.weblabora.model.WeblaboraException;

public class GrainStorage extends BuildingSingleUsage {

	public GrainStorage() {
		super("F03", SettlementRound.S, 4, "Grain Storage", BuildCost.is().wood(1).straw(1), 4, 3,
				EnumSet.of(COAST, PLAINS, HILLSIDE), false);
	}

	@Override
	public void use(Board board, UsageParamSingle input) throws WeblaboraException {
		Player player = board.getPlayer(board.getActivePlayer());
		player.subtractCoins(1);
		player.addGrain(6);
	}
}
