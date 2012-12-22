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

public class Alehouse extends Building {

	public Alehouse() {
		super("I20", SettlementRound.B, 3, "Alehouse", BuildCost.is().wood(1)
				.stone(1), 6, 3, EnumSet.of(COAST, PLAINS, HILLSIDE), false);
	}

	@Override
	public void use(Board board, UsageParam input) throws WeblaboraException {
		Player player = board.getPlayer(board.getActivePlayer());

		int penniesToAdd = 0;
		if (input.getBeer() == 1) {
			player.subtractBeer(1);
			penniesToAdd += 8;
		} else if (input.getBeer() > 1)
			throw new WeblaboraException(getName()
					+ " can only convert at most 1 beer, but was given "
					+ input.getBeer());

		if (input.getWhiskey() == 1) {
			player.subtractWhiskey(1);
			penniesToAdd += 7;
		} else if (input.getWhiskey() > 1)
			throw new WeblaboraException(getName()
					+ " can only convert at most 1 whiskey, but was given "
					+ input.getWhiskey());

		player.addCoins(penniesToAdd);
	}
}
