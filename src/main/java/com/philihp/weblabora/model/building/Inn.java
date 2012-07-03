package com.philihp.weblabora.model.building;

import static com.philihp.weblabora.model.TerrainTypeEnum.COAST;
import static com.philihp.weblabora.model.TerrainTypeEnum.HILLSIDE;
import static com.philihp.weblabora.model.TerrainTypeEnum.PLAINS;

import java.util.EnumSet;
import java.util.Set;

import com.philihp.weblabora.model.Board;
import com.philihp.weblabora.model.BuildCost;
import com.philihp.weblabora.model.Player;
import com.philihp.weblabora.model.SettlementRound;
import com.philihp.weblabora.model.TerrainTypeEnum;
import com.philihp.weblabora.model.UsageParam;
import com.philihp.weblabora.model.WeblaboraException;

public class Inn extends Building {

	public Inn() {
		super("F20", SettlementRound.B, 3, "Inn", BuildCost.is().wood(2).straw(2), 6, 4,
				EnumSet.of(COAST, PLAINS, HILLSIDE), false);
	}

	@Override
	public void use(Board board, UsageParam param) throws WeblaboraException {
		Player player = board.getPlayer(board.getActivePlayer());
		
		int payout = 0;
		
		if(param.getWine() != 0) {
			param.subtractWine(1);
			player.subtractWine(1);
			payout += 6;
		}
		
		payout += Math.min((int)param.getFood(), 7);
		
		player.subtractAll(param);
		player.addNickel(payout / 5);
		player.addPenny(payout % 5);
		
	}
}
