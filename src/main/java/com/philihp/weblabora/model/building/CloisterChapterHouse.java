package com.philihp.weblabora.model.building;

import java.util.EnumSet;
import java.util.Set;

import com.philihp.weblabora.model.Board;
import com.philihp.weblabora.model.BuildCost;
import com.philihp.weblabora.model.Player;
import com.philihp.weblabora.model.TerrainTypeEnum;
import com.philihp.weblabora.model.UsageParam;

import static com.philihp.weblabora.model.TerrainTypeEnum.*;

public class CloisterChapterHouse extends AbstractBuilding {

	public CloisterChapterHouse() {
		super("G16", "A", 3, "Cloister Chapter House", BuildCost.is().clay(3).straw(1), 5, 2, EnumSet.of(COAST, PLAINS,
				HILLSIDE), true);
	}

	@Override
	public void use(Board board, UsageParam input) {
		Player player = board.getPlayer(board.getActivePlayer());
		
		player.addClay(1);
		player.addWood(1);
		player.addPeat(1);
		player.addSheep(1);
		player.addGrain(1);
		player.addPenny(1);
	}

}
