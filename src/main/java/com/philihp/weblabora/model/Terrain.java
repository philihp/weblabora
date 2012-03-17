package com.philihp.weblabora.model;

import com.philihp.weblabora.model.building.Erection;

public class Terrain {

	private TerrainTypeEnum terrainType;
	private Landscape landscape;
	private Erection erection;

	public Terrain(Landscape landscape, TerrainTypeEnum terrainType, Erection erection) {
		this.terrainType = terrainType;
		this.landscape = landscape;
		this.erection = erection;
	}

	public Erection getErection() {
		return erection;
	}

	public void setErection(Erection erection) {
		this.erection = erection;
	}

	public Landscape getLandscape() {
		return landscape;
	}

	public void setLandscape(Landscape landscape) {
		this.landscape = landscape;
	}

	public TerrainTypeEnum getTerrainType() {
		return terrainType;
	}

	public void setTerrainType(TerrainTypeEnum terrainType) {
		this.terrainType = terrainType;
	}

}
