package com.philihp.weblabora.model.building;

import static com.philihp.weblabora.model.TerrainTypeEnum.COAST;
import static com.philihp.weblabora.model.TerrainTypeEnum.HILLSIDE;
import static com.philihp.weblabora.model.TerrainTypeEnum.PLAINS;

import java.util.EnumSet;

import com.philihp.weblabora.model.Board;
import com.philihp.weblabora.model.BuildCost;
import com.philihp.weblabora.model.Player;
import com.philihp.weblabora.model.SettlementRound;
import com.philihp.weblabora.model.UsageParam;

public class CloisterChapterHouse extends Building {

	public CloisterChapterHouse() {
		super("G16", SettlementRound.A, 3, "Cloister Chapter House", BuildCost.is().clay(3).straw(1), 5, 2, EnumSet.of(COAST, PLAINS,
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
