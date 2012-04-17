package com.philihp.weblabora.model.building;

import static com.philihp.weblabora.model.TerrainTypeEnum.COAST;
import static com.philihp.weblabora.model.TerrainTypeEnum.HILLSIDE;
import static com.philihp.weblabora.model.TerrainTypeEnum.PLAINS;

import java.util.EnumSet;
import java.util.Set;

import com.philihp.weblabora.model.TerrainTypeEnum;

public class HilltopVillage extends Settlement {

	public static class R extends HilltopVillage {
		public R() {
			super("SR8");
		}
	}
	public static class G extends HilltopVillage {
		public G() {
			super("SG8");
		}
	}
	public static class B extends HilltopVillage {
		public B() {
			super("SB8");
		}
	}
	public static class W extends HilltopVillage {
		public W() {
			super("SW8");
		}
	}

	public HilltopVillage(String id) {
		super(id, "D", "Hilltop Village", 30, 3, 8, 10, EnumSet.of(HILLSIDE));
	}

}
