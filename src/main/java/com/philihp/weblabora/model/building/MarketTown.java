package com.philihp.weblabora.model.building;

import static com.philihp.weblabora.model.SettlementRound.S;
import static com.philihp.weblabora.model.TerrainTypeEnum.COAST;
import static com.philihp.weblabora.model.TerrainTypeEnum.HILLSIDE;
import static com.philihp.weblabora.model.TerrainTypeEnum.PLAINS;

import java.util.EnumSet;
public class MarketTown extends Settlement {

	public MarketTown() {
		super("S03", S, "Market Town", 7, 0, 2, 2, EnumSet.of(COAST, PLAINS,
				HILLSIDE));
	}

}
