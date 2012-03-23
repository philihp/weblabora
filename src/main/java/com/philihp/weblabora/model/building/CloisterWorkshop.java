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
import com.philihp.weblabora.model.WeblaboraException;

public class CloisterWorkshop extends AbstractBuilding {

	public CloisterWorkshop() {
		super("G18", "A", 2, "Cloister Workshop", BuildCost.is().wood(3), 2, 7,
				EnumSet.of(COAST, PLAINS, HILLSIDE), true);
	}

	@Override
	public void use(Board board, UsageParam param) throws WeblaboraException {
		Player player = board.getPlayer(board.getActivePlayer());
		
		//just assume they give us enough energy...
		player.subtractEnergy(param);
		
		int stone = Math.min(param.getStone(),1);
		int clay = Math.min(param.getClay(),3);
		
		player.subtractStone(stone);
		player.addOrnament(stone);
		
		player.subtractClay(clay);
		player.addPottery(clay);
	}
}
