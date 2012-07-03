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

public class Portico extends BuildingSingleUsage {

	public Portico() {
		super("I25", SettlementRound.B, 4, "Portico", BuildCost.is().clay(2), 6, 2,
				EnumSet.of(COAST, PLAINS, HILLSIDE), true);
	}

	@Override
	public void use(Board board, UsageParamSingle input) {
		Player player = board.getPlayer(board.getActivePlayer());
		if(input.getRelic() != 1)
			throw new WeblaboraException(getName() + " requires 1 relic as input, but was given " + input.getRelic());
		player.subtractRelic(1);
		player.addPenny(2);
		player.addClay(2);
		player.addGrain(2);
		player.addWood(2);
		player.addPeat(2);
		player.addSheep(2);
		player.addStone(2);
	}
}
