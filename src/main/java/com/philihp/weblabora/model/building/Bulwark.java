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
import com.philihp.weblabora.model.Wheel;

public class Bulwark extends BuildingSingleUsage {

	public Bulwark() {
		super("I37", SettlementRound.D, 1, "Bulwark", BuildCost.is().clay(4),
				6, 8, EnumSet.of(PLAINS, HILLSIDE, COAST), false);
	}

	@Override
	public void use(Board board, UsageParam input) {
		Player player = board.getPlayer(board.getActivePlayer());

		if(input.getBooks() != 1)
			throw new WeblaboraException(getName()+" requires at least 1 book.");
		if(player.getBooks() < 1)
			throw new WeblaboraException(getName()+" requires that the player have at least 1 book.");

		player.subtractBooks(1);
		input.getHistory().setNextPlotFree(true);
		input.getHistory().setNextDistrictFree(true);
	}
}
