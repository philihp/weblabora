package com.philihp.weblabora.model.building;

import static com.philihp.weblabora.model.TerrainTypeEnum.COAST;
import static com.philihp.weblabora.model.TerrainTypeEnum.HILLSIDE;
import static com.philihp.weblabora.model.TerrainTypeEnum.PLAINS;

import java.util.EnumSet;
import java.util.Set;

import com.philihp.weblabora.model.TerrainTypeEnum;

public class FarmingVillage extends Settlement {

	public static class R extends FarmingVillage {
		public R() {
			super("SR2");
		}
	}

	public static class G extends FarmingVillage {
		public G() {
			super("SG2");
		}
	}

	public static class B extends FarmingVillage {
		public B() {
			super("SB2");
		}
	}

	public static class W extends FarmingVillage {
		public W() {
			super("SW2");
		}
	}
	public FarmingVillage(String id) {
		super(id, "", "Farming Village", 3, 3, 1, 1,
				EnumSet.of(COAST, PLAINS, HILLSIDE));
	}

}
