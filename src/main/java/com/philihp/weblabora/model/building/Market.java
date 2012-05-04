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

public class Market extends Building {

	public Market() {
		super("F08", "", 2, "Market", BuildCost.is().stone(2), 8, 5, EnumSet.of(COAST,PLAINS,HILLSIDE), false);
	}

	@Override
	public void use(Board board, UsageParam param) throws WeblaboraException {
		Player player = board.getPlayer(board.getActivePlayer());
		if(param.differentSingularGoods() != 4)
			throw new WeblaboraException("Market requires 4 different goods, but was given "+param.differentSingularGoods());
		player.subtractAll(param);
		player.addBread(1);
		player.addNickel(1);
		player.addPenny(2);
	}
}
