package com.philihp.weblabora.model.building;

import java.util.Set;

import com.philihp.weblabora.model.Board;
import com.philihp.weblabora.model.BuildCost;
import com.philihp.weblabora.model.GameCountry;
import com.philihp.weblabora.model.GamePlayers;
import com.philihp.weblabora.model.SettlementRound;
import com.philihp.weblabora.model.Terrain;
import com.philihp.weblabora.model.TerrainTypeEnum;
import com.philihp.weblabora.model.UsageParam;
import com.philihp.weblabora.model.WeblaboraException;

abstract public class Building extends Erection {

	protected final SettlementRound stage;
	protected final int players;
	protected final BuildCost buildCost;
	protected final boolean cloister;

	public Building(String id, SettlementRound stage, int players, String name,
			BuildCost buildCost, int settlementValue, int shieldValue,
			Set<TerrainTypeEnum> terrains, boolean cloister) {
		super(id, name, terrains, settlementValue, shieldValue);
		this.stage = stage;
		this.players = players;
		this.buildCost = buildCost;
		this.cloister = cloister;
	}
	
	public void build(Board board) {
		//hook for winery, usually useless
	}

	public abstract void use(Board board, UsageParam input)
			throws WeblaboraException;

	public SettlementRound getStage() {
		return stage;
	}

	public BuildCost getBuildCost() {
		return buildCost;
	}

	public boolean isCloister() {
		return cloister;
	}
	
	public String toString() {
		return getName();
	}
	
	public GamePlayers getPlayers() {
		return GamePlayers.valueOf(players);
	}

}
