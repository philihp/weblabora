package com.philihp.weblabora.model.building;

import static com.philihp.weblabora.model.TerrainTypeEnum.COAST;
import static com.philihp.weblabora.model.TerrainTypeEnum.HILLSIDE;
import static com.philihp.weblabora.model.TerrainTypeEnum.PLAINS;

import java.util.EnumSet;
import java.util.Set;

import com.philihp.weblabora.model.TerrainTypeEnum;

public class ArtistsColony extends Settlement {

	public static class R extends ArtistsColony {
		public R() {
			super("SR5");
		}
	}

	public static class G extends ArtistsColony {
		public G() {
			super("SG5");
		}
	}

	public static class B extends ArtistsColony {
		public B() {
			super("SB5");
		}
	}

	public static class W extends ArtistsColony {
		public W() {
			super("SW5");
		}
	}

	public ArtistsColony(String id) {
		super(id, "A", "Artist's Colony", 5, 1, 5, -1, EnumSet.of(COAST,
				PLAINS, HILLSIDE));
	}

}
