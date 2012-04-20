package com.philihp.weblabora.model.building;

import static com.philihp.weblabora.model.TerrainTypeEnum.COAST;
import static com.philihp.weblabora.model.TerrainTypeEnum.HILLSIDE;
import static com.philihp.weblabora.model.TerrainTypeEnum.PLAINS;

import java.util.EnumSet;
import java.util.Set;

import com.philihp.weblabora.model.TerrainTypeEnum;

public class FishingVillage extends Settlement {
	
	public FishingVillage() {
		super("S04", "", "Fishing Village", 8, 3, 6, 4,
				EnumSet.of(COAST));
	}

}
