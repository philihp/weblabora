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
import com.philihp.weblabora.model.Terrain;
import com.philihp.weblabora.model.TerrainTypeEnum;
import com.philihp.weblabora.model.UsageParam;
import com.philihp.weblabora.model.UsageParamSingle;
import com.philihp.weblabora.model.WeblaboraException;

public class Scriptorium extends BuildingSingleUsage {

	public Scriptorium() {
		super("I17", SettlementRound.A, 1, "Scriptorium", BuildCost.is().wood(1).straw(1), 5, 3,
				EnumSet.of(COAST, PLAINS, HILLSIDE), true);
	}

	@Override
	public void use(Board board, UsageParamSingle input) throws WeblaboraException {
		Player player = board.getPlayer(board.getActivePlayer());
		
		if(input.getPenny() != 1)
			throw new WeblaboraException(getName()+" requires exactly 1 penny.");
		
		player.subtractPenny(1);
		player.addBooks(1);
		player.addMeat(1);
		player.addWhiskey(1);
	}
}
