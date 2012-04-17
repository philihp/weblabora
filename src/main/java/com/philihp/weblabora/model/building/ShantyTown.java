package com.philihp.weblabora.model.building;

import static com.philihp.weblabora.model.TerrainTypeEnum.COAST;
import static com.philihp.weblabora.model.TerrainTypeEnum.HILLSIDE;
import static com.philihp.weblabora.model.TerrainTypeEnum.PLAINS;

import java.util.EnumSet;
import java.util.Set;

import com.philihp.weblabora.model.TerrainTypeEnum;

public class ShantyTown extends Settlement {

	public static class R extends ShantyTown {
		public R() {
			super("SR1");
		}
	}

	public static class G extends ShantyTown {
		public G() {
			super("SG1");
		}
	}

	public static class B extends ShantyTown {
		public B() {
			super("SB1");
		}
	}

	public static class W extends ShantyTown {
		public W() {
			super("SW1");
		}
	}
	
	public ShantyTown(String id) {
		super(id, "", "Shanty Town", 1, 1, -3, 0,
				EnumSet.of(COAST, PLAINS, HILLSIDE));
	}

}
