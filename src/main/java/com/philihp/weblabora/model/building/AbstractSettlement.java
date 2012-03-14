package com.philihp.weblabora.model.building;

import java.util.Set;

import com.philihp.weblabora.model.Board;
import com.philihp.weblabora.model.BuildCost;
import com.philihp.weblabora.model.TerrainTypeEnum;

abstract public class AbstractSettlement extends Erection {

	protected Board board;

	protected final String id;
	protected final String stage;
	protected final String name;
	protected final int foodCost;
	protected final int energyCost;
	protected final int settlementValue;
	protected final int shieldValue;
	protected final Set<TerrainTypeEnum> terrains;

	public AbstractSettlement(String id, String stage, String name,
			int foodCost, int energyCost, int settlementValue, int shieldValue,
			Set<TerrainTypeEnum> terrains) {
		this.id = id;
		this.stage = stage;
		this.name = name;
		this.foodCost = foodCost;
		this.energyCost = energyCost;
		this.settlementValue = settlementValue;
		this.shieldValue = shieldValue;
		this.terrains = terrains;
	}

}
