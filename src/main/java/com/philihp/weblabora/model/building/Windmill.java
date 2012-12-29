package com.philihp.weblabora.model.building;

import static com.philihp.weblabora.model.TerrainTypeEnum.COAST;
import static com.philihp.weblabora.model.TerrainTypeEnum.HILLSIDE;

import java.util.EnumSet;

import com.philihp.weblabora.model.Board;
import com.philihp.weblabora.model.BuildCost;
import com.philihp.weblabora.model.Player;
import com.philihp.weblabora.model.SettlementRound;
import com.philihp.weblabora.model.UsageParam;
import com.philihp.weblabora.model.WeblaboraException;

public class Windmill extends Building {

	public Windmill() {
		super("F04", SettlementRound.S, 1, "Windmill", BuildCost.is().wood(3).clay(2), 6, 10,
				EnumSet.of(COAST, HILLSIDE), false);
	}

	@Override
	public void use(Board board, UsageParam param) throws WeblaboraException {
		Player player = board.getPlayer(board.getActivePlayer());
		
		int iterations = Math.min(param.getGrain(), 7);
		
		player.subtractGrain(iterations);
		player.addFlour(iterations);
		player.addStraw(iterations);
	}
}
