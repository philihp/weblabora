package com.philihp.weblabora.model.building;

import com.philihp.weblabora.model.TerrainUseEnum;

public class Moor extends Erection {
	public Moor() {
		super(null,"Moore",null,0,0);
	}

	/**
	 * Returns terrain use corresponding to this erection.
	 * 
	 * @return Terrain use corresponding to this erection.
	 * @return Always {@link TerrainUseEnum#MOOR}.
	 */
	public TerrainUseEnum getTerrainUse()
	{
		return TerrainUseEnum.MOOR;
	}
}
