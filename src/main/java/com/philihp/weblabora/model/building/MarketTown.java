package com.philihp.weblabora.model.building;

import static com.philihp.weblabora.model.TerrainTypeEnum.COAST;
import static com.philihp.weblabora.model.TerrainTypeEnum.HILLSIDE;
import static com.philihp.weblabora.model.TerrainTypeEnum.PLAINS;

import java.util.EnumSet;
import java.util.Set;

import com.philihp.weblabora.model.TerrainTypeEnum;

public class MarketTown extends Settlement {

	public MarketTown() {
		super("S03", "", "Market Town", 7, 0, 2, 2, EnumSet.of(COAST, PLAINS,
				HILLSIDE));
	}

}
