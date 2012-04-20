package com.philihp.weblabora.model.building;

import static com.philihp.weblabora.model.TerrainTypeEnum.COAST;
import static com.philihp.weblabora.model.TerrainTypeEnum.HILLSIDE;
import static com.philihp.weblabora.model.TerrainTypeEnum.PLAINS;

import java.util.EnumSet;
import java.util.Set;

import com.philihp.weblabora.model.TerrainTypeEnum;

public class FarmingVillage extends Settlement {
	public FarmingVillage() {
		super("S02", "", "Farming Village", 3, 3, 1, 1,
				EnumSet.of(COAST, PLAINS, HILLSIDE));
	}

}
