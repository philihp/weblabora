package com.philihp.weblabora.model.building;

import java.util.Set;

import com.philihp.weblabora.model.Board;
import com.philihp.weblabora.model.BuildCost;
import com.philihp.weblabora.model.Terrain;
import com.philihp.weblabora.model.UsageParam;
import com.philihp.weblabora.model.WeblaboraException;

abstract public class AbstractBuilding extends Card {

	protected Board board;

	protected final String id;
	protected final String stage;
	protected final int players;
	protected final String name;
	protected final BuildCost buildCost;
	protected final int settlementValue;
	protected final int shieldValue;
	protected final Set<Terrain> terrains;
	protected final boolean cloister;

	public AbstractBuilding(String id, String stage, int players, String name, BuildCost buildCost, int settlementValue,
			int shieldValue, Set<Terrain> terrains, boolean cloister) {
		this.id = id;
		this.stage = stage;
		this.players = players;
		this.name = name;
		this.buildCost = buildCost;
		this.settlementValue = settlementValue;
		this.shieldValue = shieldValue;
		this.terrains = terrains;
		this.cloister = cloister;
	}

	public abstract void use(Board board, UsageParam input) throws WeblaboraException;

	public String getStage() {
		return stage;
	}
}
