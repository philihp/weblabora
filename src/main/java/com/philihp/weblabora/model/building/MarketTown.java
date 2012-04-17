package com.philihp.weblabora.model.building;

import static com.philihp.weblabora.model.TerrainTypeEnum.COAST;
import static com.philihp.weblabora.model.TerrainTypeEnum.HILLSIDE;
import static com.philihp.weblabora.model.TerrainTypeEnum.PLAINS;

import java.util.EnumSet;
import java.util.Set;

import com.philihp.weblabora.model.TerrainTypeEnum;

public class MarketTown extends Settlement {

	public static class R extends MarketTown {
		public R() {
			super("SR3");
		}
	}

	public static class G extends MarketTown {
		public G() {
			super("SG3");
		}
	}

	public static class B extends MarketTown {
		public B() {
			super("SB3");
		}
	}

	public static class W extends MarketTown {
		public W() {
			super("SW3");
		}
	}

	public MarketTown(String id) {
		super(id, "", "Market Town", 7, 0, 2, 2, EnumSet.of(COAST, PLAINS,
				HILLSIDE));
	}

}
