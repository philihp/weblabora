package com.philihp.weblabora.model.building;

import static com.philihp.weblabora.model.TerrainTypeEnum.COAST;
import static com.philihp.weblabora.model.TerrainTypeEnum.HILLSIDE;
import static com.philihp.weblabora.model.TerrainTypeEnum.PLAINS;

import java.util.EnumSet;
import java.util.Set;

import com.philihp.weblabora.model.TerrainTypeEnum;

public class Hamlet extends Settlement {

	public static class R extends Hamlet {
		public R() {
			super("SR6");
		}
	}

	public static class G extends Hamlet {
		public G() {
			super("SG6");
		}
	}

	public static class B extends Hamlet {
		public B() {
			super("SB6");
		}
	}

	public static class W extends Hamlet {
		public W() {
			super("SW6");
		}
	}

	public Hamlet(String id) {
		super(id, "B", "Hamlet", 5, 6, 4, 3, EnumSet
				.of(COAST, PLAINS, HILLSIDE));
	}

}
