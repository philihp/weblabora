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
import com.philihp.weblabora.model.TerrainTypeEnum;
import com.philihp.weblabora.model.UsageParam;
import com.philihp.weblabora.model.Wheel;

public class CloisterOffice extends AbstractBuilding {

	public CloisterOffice() {
		super("LX3", "L", 0, "Cloister Office", BuildCost.is(), 2, 0, EnumSet.of(PLAINS), true);
	}

	@Override
	public void use(Board board, UsageParam input) {
		Player player = board.getPlayer(board.getActivePlayer());
		Wheel wheel = board.getWheel();
		Wheel.Token token = input.isWithJoker()?wheel.getJoker():wheel.getCoin();
		player.addPenny(token.take());
	}
}
