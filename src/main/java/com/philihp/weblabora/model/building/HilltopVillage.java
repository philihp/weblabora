package com.philihp.weblabora.model.building;

import static com.philihp.weblabora.model.SettlementRound.D;
import static com.philihp.weblabora.model.TerrainTypeEnum.HILLSIDE;

import java.util.EnumSet;
public class HilltopVillage extends Settlement {

	public HilltopVillage() {
		super("S08", D, "Hilltop Village", 30, 3, 8, 10, EnumSet.of(HILLSIDE));
	}

}
