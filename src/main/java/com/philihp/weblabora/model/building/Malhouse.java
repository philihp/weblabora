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
import com.philihp.weblabora.model.UsageParamSingle;
import com.philihp.weblabora.model.WeblaboraException;

public class Malhouse extends BuildingSingleUsage {

	public Malhouse() {
		super("I04", SettlementRound.S, 1, "Malhouse", BuildCost.is().clay(2), 4, 5,
				EnumSet.of(COAST, PLAINS, HILLSIDE), false);
	}

	@Override
	public void use(Board board, UsageParamSingle param) throws WeblaboraException {
		Player player = board.getPlayer(board.getActivePlayer());
		
		int iterations = param.getGrain();
		
		player.subtractGrain(iterations);
		player.addHops(iterations);
		player.addStraw(iterations);
	}
}
