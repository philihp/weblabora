package com.philihp.weblabora.model.building;

import static com.philihp.weblabora.model.TerrainTypeEnum.COAST;

import java.util.EnumSet;

import com.philihp.weblabora.model.Board;
import com.philihp.weblabora.model.BuildCost;
import com.philihp.weblabora.model.Player;
import com.philihp.weblabora.model.SettlementRound;
import com.philihp.weblabora.model.UsageParamSingle;
import com.philihp.weblabora.model.WeblaboraException;

public class FalseLighthouse extends BuildingSingleUsage {

	public FalseLighthouse() {
		super("I08", SettlementRound.S, 1, "False Lighthouse", BuildCost.is().wood(2).clay(1), 5, 5, EnumSet.of(COAST), false);
	}

	@Override
	public void use(Board board, UsageParamSingle param) throws WeblaboraException {
		Player player = board.getPlayer(board.getActivePlayer());
		if(param.getWhiskey() == 0 && param.getBeer() == 0)
			throw new WeblaboraException("False Lighthouse needs to know if it should give beer or whiskey. Specify either as the first parameter.");
		if(param.getWhiskey() != 0 && param.getBeer() != 0)
			throw new WeblaboraException("False Lighthouse was given both beer and whiskey in its parameter. It can only do one of these.");
		
		if(param.getWhiskey() != 0) player.addWhiskey(1);
		if(param.getBeer() != 0) player.addBeer(1);
		
		player.addPenny(3);
	}
}
