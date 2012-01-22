package com.philihp.weblabora.model.building;

import java.util.EnumSet;
import java.util.Set;

import com.philihp.weblabora.model.BuildCost;
import com.philihp.weblabora.model.Terrain;

import static com.philihp.weblabora.model.Terrain.*;

public class CloisterChapterHouse extends AbstractBuilding {

	public CloisterChapterHouse(String id, String stage, int players, String name, BuildCost buildCost,
			int settlementValue, int shieldValue, Set<Terrain> terrains) {
		super("G16", "A", 3, "Cloister Chapter House", BuildCost.is().clay(3).straw(1), 5, 2, EnumSet.of(COAST, PLAINS,
				HILLSIDE));
	}

}
