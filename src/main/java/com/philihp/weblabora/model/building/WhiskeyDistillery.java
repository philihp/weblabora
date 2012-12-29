package com.philihp.weblabora.model.building;

import static com.philihp.weblabora.model.TerrainTypeEnum.COAST;
import static com.philihp.weblabora.model.TerrainTypeEnum.HILLSIDE;
import static com.philihp.weblabora.model.TerrainTypeEnum.PLAINS;

import java.util.EnumSet;

import com.philihp.weblabora.model.Board;
import com.philihp.weblabora.model.BuildCost;
import com.philihp.weblabora.model.Player;
import com.philihp.weblabora.model.SettlementRound;
import com.philihp.weblabora.model.UsageParamSingle;
import com.philihp.weblabora.model.WeblaboraException;

public class WhiskeyDistillery extends BuildingSingleUsage {

	public WhiskeyDistillery() {
		super("I21", SettlementRound.B, 1, "Whiskey Distillery", BuildCost.is().clay(2).straw(2), 5, 6,
				EnumSet.of(COAST, PLAINS,HILLSIDE), false);
	}

	@Override
	public void use(Board board, UsageParamSingle param) throws WeblaboraException {
		Player player = board.getPlayer(board.getActivePlayer());
		
		int iterations = param.getHops();
		if(param.getWood() != iterations || param.getPeat() != iterations) 
			throw new WeblaboraException(getName() + " converts takes wood, peat, and hops in even amounts to make 2 whiskey. There were too many/too few of one of these.");
		
		player.subtractWood(iterations);
		player.subtractPeat(iterations);
		player.subtractHops(iterations);
		player.addWhiskey(iterations * 2);
		
	}

	@Override
	public void build(Board board) {
		super.build(board);
		board.getStartingMarker().setCost(2);
	}
}
