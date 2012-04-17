package com.philihp.weblabora.model.building;

import static com.philihp.weblabora.model.TerrainTypeEnum.COAST;
import static com.philihp.weblabora.model.TerrainTypeEnum.HILLSIDE;
import static com.philihp.weblabora.model.TerrainTypeEnum.PLAINS;

import java.util.EnumSet;
import java.util.Set;

import com.philihp.weblabora.model.TerrainTypeEnum;

public class Village extends Settlement {

	public static class R extends Village {
		public R() {
			super("SR7");
		}
	}

	public static class G extends Village {
		public G() {
			super("SG7");
		}
	}

	public static class B extends Village {
		public B() {
			super("SB7");
		}
	}

	public static class W extends Village {
		public W() {
			super("SW7");
		}
	}

	public Village(String id) {
		super(id, "C", "Village", 15, 9, 6, 8, EnumSet.of(COAST, PLAINS,
				HILLSIDE));
	}

}
