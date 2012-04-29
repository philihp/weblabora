package com.philihp.weblabora.model.building;

import static com.philihp.weblabora.model.SettlementRound.C;
import static com.philihp.weblabora.model.TerrainTypeEnum.COAST;
import static com.philihp.weblabora.model.TerrainTypeEnum.HILLSIDE;
import static com.philihp.weblabora.model.TerrainTypeEnum.PLAINS;

import java.util.EnumSet;

public class Village extends Settlement {

	public Village() {
		super("S07", C, "Village", 15, 9, 6, 8, EnumSet.of(COAST, PLAINS,
				HILLSIDE));
	}

}
