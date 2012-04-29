package com.philihp.weblabora.model.building;

import static com.philihp.weblabora.model.SettlementRound.A;
import static com.philihp.weblabora.model.TerrainTypeEnum.COAST;
import static com.philihp.weblabora.model.TerrainTypeEnum.HILLSIDE;
import static com.philihp.weblabora.model.TerrainTypeEnum.PLAINS;

import java.util.EnumSet;
public class ArtistsColony extends Settlement {

	public ArtistsColony() {
		super("S05", A, "Artist's Colony", 5, 1, 5, -1, EnumSet.of(COAST,
				PLAINS, HILLSIDE));
	}

}
