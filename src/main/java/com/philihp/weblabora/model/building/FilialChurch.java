package com.philihp.weblabora.model.building;

import static com.philihp.weblabora.model.TerrainTypeEnum.COAST;
import static com.philihp.weblabora.model.TerrainTypeEnum.MOUNTAIN;
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
import com.philihp.weblabora.model.WeblaboraException;
import com.philihp.weblabora.model.Wheel;

public class FilialChurch extends Building {

	public FilialChurch() {
		super("I32", SettlementRound.C, 3, "Filial Church", BuildCost.is()
				.wood(3).clay(4), 7, 6, EnumSet.of(COAST, PLAINS, HILLSIDE),
				true);
	}

	@Override
	public void use(Board board, UsageParam input) throws WeblaboraException {
		Player player = board.getPlayer(board.getActivePlayer());

		if (input.getReliquary() == 1 && player.getReliquary() == 0)
			throw new WeblaboraException(
					"You don't have a relic to throw into the "
							+ getName()
							+ ". And even if you did, you would be throwing away 4 goods. Are you okay?");

		if (input.differentSingularGoods() == 5) {
			player.subtractAll(input);
			player.addReliquary(1);
		} else
			throw new WeblaboraException(
					getName()
							+ " converts 5 different goods into a relic, but was given "
							+ input.differentSingularGoods() + " instead.");
	}
}
