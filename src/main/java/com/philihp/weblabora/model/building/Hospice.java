package com.philihp.weblabora.model.building;

import static com.philihp.weblabora.model.TerrainTypeEnum.COAST;
import static com.philihp.weblabora.model.TerrainTypeEnum.HILLSIDE;
import static com.philihp.weblabora.model.TerrainTypeEnum.PLAINS;

import java.util.EnumSet;

import com.philihp.weblabora.model.Board;
import com.philihp.weblabora.model.BuildCost;
import com.philihp.weblabora.model.SettlementRound;
import com.philihp.weblabora.model.UsageParam;

public class Hospice extends Building {

	public Hospice() {
		super("F40", SettlementRound.D, 3, "Hospice", BuildCost.is().wood(3)
				.straw(1), 5, 7, EnumSet.of(PLAINS, HILLSIDE, COAST), true);
	}

	@Override
	public void use(Board board, UsageParam input) {
		// use any building
	}
}
