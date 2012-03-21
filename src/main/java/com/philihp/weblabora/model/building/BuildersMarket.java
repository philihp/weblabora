package com.philihp.weblabora.model.building;

import static com.philihp.weblabora.model.TerrainTypeEnum.COAST;
import static com.philihp.weblabora.model.TerrainTypeEnum.HILLSIDE;
import static com.philihp.weblabora.model.TerrainTypeEnum.PLAINS;

import java.util.EnumSet;
import java.util.Set;

import com.philihp.weblabora.model.Board;
import com.philihp.weblabora.model.BuildCost;
import com.philihp.weblabora.model.Player;
import com.philihp.weblabora.model.TerrainTypeEnum;
import com.philihp.weblabora.model.UsageParam;

public class BuildersMarket extends AbstractBuilding {

	public BuildersMarket() {
		super("G13", "", 4, "Builders' Market", BuildCost.is().clay(2), 1, 6, EnumSet.of(COAST, PLAINS, HILLSIDE), false);
	}

	@Override
	public void use(Board board, UsageParam input) {
		Player activePlayer = board.getPlayer(board.getActivePlayer());
		
		activePlayer.subtractPenny(2);
		
		activePlayer.addWood(2);
		activePlayer.addClay(2);
		activePlayer.addStone(1);
		activePlayer.addStraw(1);
	}
}
