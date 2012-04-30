package com.philihp.weblabora.model;

import com.google.common.collect.Table;
import com.philihp.weblabora.model.building.Erection;

public class Terrain {

	private TerrainTypeEnum terrainType;
	private Landscape landscape;
	private Erection erection;
	private Integer x = null;
	private Integer y = null;

	public Terrain(Landscape landscape, TerrainTypeEnum terrainType, Erection erection, int x, int y) {
		this(landscape,terrainType,erection);
		this.x = x;
		this.y = y;
	}

	public Terrain(Landscape landscape, TerrainTypeEnum terrainType, Erection erection) {
		this.terrainType = terrainType;
		this.landscape = landscape;
		this.erection = erection;
		
		if(erection != null)
			erection.setLocation(this);
	}

	public Erection getErection() {
		return erection;
	}

	public void setErection(Erection erection) {
		this.erection = erection;
		if(erection.getLocation() != this)
			erection.setLocation(this);
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
	
	public String getCoords() {
		return "("+x+","+y+")";
	}
	
	public Coordinate getCoordinate() {
		return new Coordinate(x, y);
	}
}
