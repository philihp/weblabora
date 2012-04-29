package com.philihp.weblabora.model.building;

import static com.philihp.weblabora.model.SettlementRound.S;
import static com.philihp.weblabora.model.TerrainTypeEnum.COAST;

import java.util.EnumSet;
public class FishingVillage extends Settlement {
	
	public FishingVillage() {
		super("S04", S, "Fishing Village", 8, 3, 6, 4,
				EnumSet.of(COAST));
	}

}
