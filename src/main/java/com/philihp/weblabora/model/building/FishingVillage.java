package com.philihp.weblabora.model.building;

import static com.philihp.weblabora.model.TerrainTypeEnum.COAST;
import static com.philihp.weblabora.model.TerrainTypeEnum.HILLSIDE;
import static com.philihp.weblabora.model.TerrainTypeEnum.PLAINS;

import java.util.EnumSet;
import java.util.Set;

import com.philihp.weblabora.model.TerrainTypeEnum;

public class FishingVillage extends Settlement {

	public static class R extends FishingVillage {
		public R() {
			super("SR4");
		}
	}

	public static class G extends FishingVillage {
		public G() {
			super("SG4");
		}
	}

	public static class B extends FishingVillage {
		public B() {
			super("SB4");
		}
	}

	public static class W extends FishingVillage {
		public W() {
			super("SW4");
		}
	}

	public FishingVillage(String id) {
		super(id, "", "Fishing Village", 8, 3, 6, 4,
				EnumSet.of(COAST));
	}

}
