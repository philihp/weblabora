package com.philihp.weblabora.model.building;

import static com.philihp.weblabora.model.TerrainTypeEnum.COAST;
import static com.philihp.weblabora.model.TerrainTypeEnum.HILLSIDE;
import static com.philihp.weblabora.model.TerrainTypeEnum.PLAINS;

import java.util.EnumSet;
import java.util.Set;

import com.philihp.weblabora.model.TerrainTypeEnum;

public class HilltopVillage extends Settlement {

	public HilltopVillage() {
		super("S08", "D", "Hilltop Village", 30, 3, 8, 10, EnumSet.of(HILLSIDE));
	}

}
