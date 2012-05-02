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
import com.philihp.weblabora.model.UsageParamDouble;
import com.philihp.weblabora.model.WeblaboraException;

public class Winery extends BuildingDoubleUsage {

	public Winery() {
		super("F21", "B", 2, "Winery", BuildCost.is().clay(2).straw(2), 5, 4,
				EnumSet.of(COAST, PLAINS,HILLSIDE), false);
	}

	@Override
	public void use(Board board, UsageParamDouble param) throws WeblaboraException {
		Player player = board.getPlayer(board.getActivePlayer());
		
		player.subtractGrapes(param.getGrapes());
		player.addWine(param.getGrapes());
		
		if(param.getSecondary().getWine() != 0) {
			player.subtractWine(param.getSecondary().getWine());
			player.addNickel(1);
			player.addPenny(2);
		}
	}

	@Override
	public void build(Board board) {
		super.build(board);
		board.getStartingMarker().setCost(2);
	}
}
