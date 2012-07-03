package com.philihp.weblabora.model.building;

import static com.philihp.weblabora.model.TerrainTypeEnum.COAST;
import static com.philihp.weblabora.model.TerrainTypeEnum.HILLSIDE;
import static com.philihp.weblabora.model.TerrainTypeEnum.PLAINS;

import java.util.EnumSet;
import java.util.Set;

import com.philihp.weblabora.model.Board;
import com.philihp.weblabora.model.BuildCost;
import com.philihp.weblabora.model.Player;
import com.philihp.weblabora.model.SettlementRound;
import com.philihp.weblabora.model.TerrainTypeEnum;
import com.philihp.weblabora.model.UsageParam;
import com.philihp.weblabora.model.UsageParamDouble;
import com.philihp.weblabora.model.UsageParamSingle;
import com.philihp.weblabora.model.WeblaboraException;

public class SpinningMill extends Building {

	public SpinningMill() {
		super("I09", SettlementRound.S, 3, "Spinning Mill", BuildCost.is()
				.wood(1).straw(1), 3, 3, EnumSet.of(COAST, PLAINS, HILLSIDE),
				false);
	}

	@Override
	public void use(Board board, UsageParam input) throws WeblaboraException {
		Player player = board.getPlayer(board.getActivePlayer());

		if (player.getSheep() >= 9)
			player.addCoins(6);
		else if (player.getSheep() >= 5)
			player.addCoins(5);
		else if (player.getSheep() >= 1)
			player.addCoins(3);
		else
			throw new WeblaboraException(
					"Even though Spinning Mill doesn't consume them, it requires at least 1 sheep in inventory, but current player has "
							+ player.getSheep());
	}
}
