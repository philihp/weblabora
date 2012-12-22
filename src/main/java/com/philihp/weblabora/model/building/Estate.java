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

public class Estate extends Building {

	public Estate() {
		super("G39", SettlementRound.D, 4, "Estate", BuildCost.is().wood(2).stone(2), 6, 5,
				EnumSet.of(PLAINS, HILLSIDE, COAST), false);
	}

	@Override
	public void use(Board board, UsageParam input) throws WeblaboraException {
		Player player = board.getPlayer(board.getActivePlayer());

		player.subtractAll(input);
		double energy = input.getEnergy();
		int food = (int) input.getFood();

		for (int iterations = 0; iterations < 2; iterations++) {
			if (energy >= 6)
				energy -= 6;
			else if (food >= 10)
				food -= 10;
			else
				break;
			player.addBooks(1);
			player.addOrnament(1);
		}
	}
}
