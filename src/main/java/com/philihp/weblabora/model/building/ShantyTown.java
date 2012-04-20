package com.philihp.weblabora.model.building;

import static com.philihp.weblabora.model.TerrainTypeEnum.COAST;
import static com.philihp.weblabora.model.TerrainTypeEnum.HILLSIDE;
import static com.philihp.weblabora.model.TerrainTypeEnum.PLAINS;

import java.util.EnumSet;
import java.util.Set;

import com.philihp.weblabora.model.TerrainTypeEnum;

public class ShantyTown extends Settlement {
	
	public ShantyTown() {
		super("S01", "", "Shanty Town", 1, 1, -3, 0,
				EnumSet.of(COAST, PLAINS, HILLSIDE));
	}

}
