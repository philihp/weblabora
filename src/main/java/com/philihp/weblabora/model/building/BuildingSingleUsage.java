package com.philihp.weblabora.model.building;

import java.util.Set;

import com.philihp.weblabora.model.Board;
import com.philihp.weblabora.model.BuildCost;
import com.philihp.weblabora.model.SettlementRound;
import com.philihp.weblabora.model.TerrainTypeEnum;
import com.philihp.weblabora.model.UsageParam;
import com.philihp.weblabora.model.UsageParamDouble;
import com.philihp.weblabora.model.UsageParamSingle;
import com.philihp.weblabora.model.WeblaboraException;

abstract class BuildingSingleUsage extends Building {

	public BuildingSingleUsage(String id, SettlementRound stage, int players,
			String name, BuildCost buildCost, int settlementValue,
			int shieldValue, Set<TerrainTypeEnum> terrains, boolean cloister) {
		super(id, stage, players, name, buildCost, settlementValue,
				shieldValue, terrains, cloister);
	}

	@Override
	public void use(Board board, UsageParam input) throws WeblaboraException {
		
		if(input instanceof UsageParamSingle) {
			use(board, (UsageParamSingle)input);
		}
		else {
			throw new WeblaboraException(getName()+" requires only one parameter, for example U(G34,BoPoOrRq)");
		}
		
	}
	
	abstract void use(Board board, UsageParamSingle input) throws WeblaboraException;
}
