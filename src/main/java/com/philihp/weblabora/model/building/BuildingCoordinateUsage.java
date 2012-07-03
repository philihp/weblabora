package com.philihp.weblabora.model.building;

import java.util.Set;

import com.philihp.weblabora.model.Board;
import com.philihp.weblabora.model.BuildCost;
import com.philihp.weblabora.model.SettlementRound;
import com.philihp.weblabora.model.TerrainTypeEnum;
import com.philihp.weblabora.model.UsageParam;
import com.philihp.weblabora.model.UsageParamCoordinates;
import com.philihp.weblabora.model.UsageParamDouble;
import com.philihp.weblabora.model.WeblaboraException;

abstract class BuildingCoordinateUsage extends Building {

	public BuildingCoordinateUsage(String id, SettlementRound stage, int players,
			String name, BuildCost buildCost, int settlementValue,
			int shieldValue, Set<TerrainTypeEnum> terrains, boolean cloister) {
		super(id, stage, players, name, buildCost, settlementValue,
				shieldValue, terrains, cloister);
	}

	@Override
	public void use(Board board, UsageParam input) throws WeblaboraException {
		
		if(input instanceof UsageParamCoordinates) {
			use(board, (UsageParamCoordinates)input);
		}
		else {
			throw new WeblaboraException(getName()+" pairs of coordinates, for example U("+getId()+",3,0) or U(F38,1,0,2,0,1,1,2,1)");
		}
		
	}
	
	abstract void use(Board board, UsageParamCoordinates input) throws WeblaboraException;
}
