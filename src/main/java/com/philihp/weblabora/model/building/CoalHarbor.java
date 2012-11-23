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
import com.philihp.weblabora.model.UsageParamSingle;
import com.philihp.weblabora.model.WeblaboraException;

public class CoalHarbor extends BuildingSingleUsage {

	public CoalHarbor() {
		super("I31", SettlementRound.C, 4, "Coal Harbor", BuildCost.is().clay(1).stone(2), 0, 12,
				EnumSet.of(COAST), false);
	}

	@Override
	public void use(Board board, UsageParamSingle param) throws WeblaboraException {
		Player player = board.getPlayer(board.getActivePlayer());
	
		int iterations = param.getCoal();

		if(iterations == 0) throw new WeblaboraException(getName()+" was not supplied any coal.");
		if(iterations > 3) throw new WeblaboraException(getName()+" was supplied "+iterations+" coal, but can only consume a maximum of 3.");		
		player.subtractCoal(iterations);
		player.addCoins(3*iterations);
		player.addWhiskey(iterations);	
	}
}
