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

public class GrandManor extends Building {

	public GrandManor() {
		super("I27", SettlementRound.C, 1, "Grand Manor", BuildCost.is().coin(20), 7, 18, EnumSet.of(HILLSIDE, PLAINS, COAST), false);
	}

	@Override
	public void use(Board board, UsageParam input) throws WeblaboraException {
		Player player = board.getPlayer(board.getActivePlayer());
		if(player.getWhiskey() < 1)
			throw new WeblaboraException(getName()+" requires 1 whiskey.");
		player.subtractWhiskey(1);
	}
}
