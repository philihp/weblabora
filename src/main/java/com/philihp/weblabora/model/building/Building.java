package com.philihp.weblabora.model.building;

import java.util.Set;

import com.philihp.weblabora.model.Board;
import com.philihp.weblabora.model.BuildCost;
import com.philihp.weblabora.model.Terrain;
import com.philihp.weblabora.model.TerrainTypeEnum;
import com.philihp.weblabora.model.UsageParam;
import com.philihp.weblabora.model.WeblaboraException;

abstract public class Building extends Erection {

	protected final String stage;
	protected final int players;
	protected final BuildCost buildCost;
	protected final int settlementValue;
	protected final int shieldValue;
	protected final boolean cloister;

	public Building(String id, String stage, int players, String name,
			BuildCost buildCost, int settlementValue, int shieldValue,
			Set<TerrainTypeEnum> terrains, boolean cloister) {
		super(id, name, terrains);
		this.stage = stage;
		this.players = players;
		this.buildCost = buildCost;
		this.settlementValue = settlementValue;
		this.shieldValue = shieldValue;
		this.cloister = cloister;
	}
	
	public void build(Board board) {
		//hook for winery, usually useless
	}

	public abstract void use(Board board, UsageParam input)
			throws WeblaboraException;

	public String getStage() {
		return stage;
	}

	public BuildCost getBuildCost() {
		return buildCost;
	}

	public int getSettlementValue() {
		return settlementValue;
	}

	public int getShieldValue() {
		return shieldValue;
	}

	public boolean isCloister() {
		return cloister;
	}
	
	public String toString() {
		return getName();
	}

}
