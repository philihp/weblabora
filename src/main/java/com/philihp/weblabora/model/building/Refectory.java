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

public class Refectory extends Building {

	public Refectory() {
		super("I30", SettlementRound.C, 1, "Refectory", BuildCost.is().clay(2).wood(1), 5, 4, EnumSet.of(COAST, PLAINS, HILLSIDE), true);
	}

	@Override
	public void use(Board board, UsageParam input) throws WeblaboraException {
		Player player = board.getPlayer(board.getActivePlayer());
		player.addBeer(1);
		player.addMeat(1);

		// turn peat into coal
		int quantity = input.getMeat();

		if(player.getMeat() < quantity)
			throw new WeblaboraException(getName()+" was told to consume "+quantity+" meat, but player only has "+player.getMeat()+" meat available");

		player.subtractMeat(quantity);
		player.addPottery(quantity);
	}
}
