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

public class Locutory extends Building {

	public Locutory() {
		super("I23", SettlementRound.B, 4, "Locutory", BuildCost.is().wood(3).clay(2), 6, 2,
				EnumSet.of(COAST, PLAINS, HILLSIDE), true);
	}

	@Override
	public void use(Board board, UsageParam param) throws WeblaboraException {
		Player player = board.getPlayer(board.getActivePlayer());

		if(param.getPenny() != 2)
			throw new WeblaboraException(getName()+" consumes two pennies, but was given "+param.getPenny()+" instead.");
		player.subtractPenny(2);
		player.resetPrior();
	}
}
