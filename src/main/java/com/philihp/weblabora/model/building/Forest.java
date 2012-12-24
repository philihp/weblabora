package com.philihp.weblabora.model.building;

import com.philihp.weblabora.model.TerrainUseEnum;

public class Forest extends Erection {
	public Forest() {
		super(null,"Forest",null,0,0);
	}

	/**
	 * Returns terrain use corresponding to this erection.
	 * 
	 * @return Terrain use corresponding to this erection.
	 * @return Always {@link TerrainUseEnum#FOREST}.
	 */
	public TerrainUseEnum getTerrainUse()
	{
		return TerrainUseEnum.FOREST;
	}
}
