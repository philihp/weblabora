package com.philihp.weblabora.model.building;

import static com.philihp.weblabora.model.TerrainTypeEnum.HILLSIDE;

import java.util.EnumSet;

import com.philihp.weblabora.model.Board;
import com.philihp.weblabora.model.BuildCost;
import com.philihp.weblabora.model.Player;
import com.philihp.weblabora.model.SettlementRound;
import com.philihp.weblabora.model.Token;
import com.philihp.weblabora.model.UsageParam;
import com.philihp.weblabora.model.Wheel;

public class Grapevine extends Building {

	protected Grapevine(String id, SettlementRound stage, int players) {
		super(id, stage, players, "Grapevine", BuildCost.is().wood(1), 6, 3,
				EnumSet.of(HILLSIDE), false);
	}

	@Override
	public void use(Board board, UsageParam input) {
		Player activePlayer = board.getPlayer(board.getActivePlayer());
		Wheel wheel = board.getWheel();
		Token token = input.isWithJoker()?wheel.getJoker():wheel.getGrape();
		activePlayer.addGrapes(token.take());
		board.distributeBonusProduction(UsageParam.is().grapes(1));
	}
}
