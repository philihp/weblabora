package com.philihp.weblabora.model.building;

import java.util.Set;

import com.philihp.weblabora.model.Board;
import com.philihp.weblabora.model.BuildCost;
import com.philihp.weblabora.model.TerrainTypeEnum;

abstract public class Settlement extends Erection {

	protected Board board;

	protected final String stage;
	protected final int foodCost;
	protected final int energyCost;
	protected final int settlementValue;
	protected final int shieldValue;

	public Settlement(String id, String stage, String name,
			int foodCost, int energyCost, int settlementValue, int shieldValue,
			Set<TerrainTypeEnum> terrains) {
		super(id, name, terrains);
		this.stage = stage;
		this.foodCost = foodCost;
		this.energyCost = energyCost;
		this.settlementValue = settlementValue;
		this.shieldValue = shieldValue;
	}

}
