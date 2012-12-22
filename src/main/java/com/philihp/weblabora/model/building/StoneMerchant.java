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

public class StoneMerchant extends Building {

	public StoneMerchant() {
		super("G12", SettlementRound.S, 1, "Stone Merchant", BuildCost.is().wood(1), 1, 6,
				EnumSet.of(COAST, PLAINS, HILLSIDE), false);
	}

	@Override
	public void use(Board board, UsageParam param) throws WeblaboraException {
		Player player = board.getPlayer(board.getActivePlayer());
		double energy = param.getEnergy();
		double food = param.getFood();

		double iterations = Math.floor(Math.min(Math.min(energy, food / 2), 5));

		// it's possible to give the Stone Merchant more than it needs, but it
		// can never know what you want to keep, so it just eats it all.
		player.subtractAll(param);
		player.addStone((int) iterations);
	}
}
