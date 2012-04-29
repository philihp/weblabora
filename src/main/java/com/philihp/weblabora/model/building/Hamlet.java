package com.philihp.weblabora.model.building;

import static com.philihp.weblabora.model.SettlementRound.B;
import static com.philihp.weblabora.model.TerrainTypeEnum.COAST;
import static com.philihp.weblabora.model.TerrainTypeEnum.HILLSIDE;
import static com.philihp.weblabora.model.TerrainTypeEnum.PLAINS;

import java.util.EnumSet;
public class Hamlet extends Settlement {

	public Hamlet() {
		super("S06", B, "Hamlet", 5, 6, 4, 3, EnumSet
				.of(COAST, PLAINS, HILLSIDE));
	}

}
