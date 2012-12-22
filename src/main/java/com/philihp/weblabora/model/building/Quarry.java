package com.philihp.weblabora.model.building;

import static com.philihp.weblabora.model.TerrainTypeEnum.MOUNTAIN;

import java.util.EnumSet;

import com.philihp.weblabora.model.Board;
import com.philihp.weblabora.model.BuildCost;
import com.philihp.weblabora.model.Player;
import com.philihp.weblabora.model.SettlementRound;
import com.philihp.weblabora.model.Token;
import com.philihp.weblabora.model.UsageParam;
import com.philihp.weblabora.model.Wheel;

abstract class Quarry extends Building {

	protected Quarry(String id, SettlementRound stage, int players) {
		super(id, stage, players, "Quarry", BuildCost.is().coin(5), -4, 7, EnumSet
				.of(MOUNTAIN), false);
	}

	@Override
	public void use(Board board, UsageParam input) {
		Player activePlayer = board.getPlayer(board.getActivePlayer());
		Wheel wheel = board.getWheel();

		Token token = input.isWithJoker() ? wheel.getJoker() : wheel
				.getStone();

		activePlayer.addStone(token.take());
		board.distributeBonusProduction(UsageParam.is().stone(1));
	}
}
