package com.philihp.weblabora.model.building;

import java.util.Set;

import com.philihp.weblabora.model.Board;
import com.philihp.weblabora.model.BuildCost;
import com.philihp.weblabora.model.SettlementRound;
import com.philihp.weblabora.model.TerrainTypeEnum;

abstract public class Settlement extends Erection {

	protected Board board;
	protected final SettlementRound round;
	protected final int foodCost;
	protected final int energyCost;
	
	public Settlement(String id, SettlementRound round, String name, int foodCost,
			int energyCost, int settlementValue, int shieldValue,
			Set<TerrainTypeEnum> terrains) {
		super(id, name, terrains, settlementValue, shieldValue);
		this.round = round;
		this.foodCost = foodCost;
		this.energyCost = energyCost;
	}

	public Board getBoard() {
		return board;
	}

	public SettlementRound getRound() {
		return round;
	}

	public int getFoodCost() {
		return foodCost;
	}

	public int getEnergyCost() {
		return energyCost;
	}

}
