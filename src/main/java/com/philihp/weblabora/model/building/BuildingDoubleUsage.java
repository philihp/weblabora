package com.philihp.weblabora.model.building;

import java.util.Set;

import com.philihp.weblabora.model.Board;
import com.philihp.weblabora.model.BuildCost;
import com.philihp.weblabora.model.SettlementRound;
import com.philihp.weblabora.model.TerrainTypeEnum;
import com.philihp.weblabora.model.UsageParam;
import com.philihp.weblabora.model.UsageParamDouble;
import com.philihp.weblabora.model.WeblaboraException;

abstract class BuildingDoubleUsage extends Building {

	public BuildingDoubleUsage(String id, SettlementRound stage, int players,
			String name, BuildCost buildCost, int settlementValue,
			int shieldValue, Set<TerrainTypeEnum> terrains, boolean cloister) {
		super(id, stage, players, name, buildCost, settlementValue,
				shieldValue, terrains, cloister);
	}

	@Override
	public void use(Board board, UsageParam input) throws WeblaboraException {
		
		if(input instanceof UsageParamDouble) {
			use(board, (UsageParamDouble)input);
		}
		else {
			throw new WeblaboraException(getName()+" requires two parameters when used, even if one is an empty, for example U(F05,FlFlFlFlPt,BrBr) or U(F05,FlSw,)");
		}
		
	}
	
	abstract void use(Board board, UsageParamDouble input) throws WeblaboraException;
}
