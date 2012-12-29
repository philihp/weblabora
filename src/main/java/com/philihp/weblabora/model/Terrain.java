package com.philihp.weblabora.model;

import java.util.HashSet;

import com.philihp.weblabora.model.building.Erection;

public class Terrain {

	private TerrainTypeEnum terrainType;
	private TerrainUseEnum terrainUse;
	private Landscape landscape;
	private Erection erection;
	private Integer x = null;
	private Integer y = null;

	public Terrain(Landscape landscape, TerrainTypeEnum terrainType, TerrainUseEnum terrainUse, Erection erection, int x, int y) {
		this(landscape,terrainType,terrainUse,erection);
		this.x = x;
		this.y = y;
	}

	public Terrain(Landscape landscape, TerrainTypeEnum terrainType, TerrainUseEnum terrainUse, Erection erection) {
		this.terrainType = terrainType;
		this.terrainUse = terrainUse;
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
		this.terrainUse = erection.getTerrainUse();
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

	public TerrainUseEnum getTerrainUse() {
		return terrainUse;
	}

	/**
	 * Determines whether this terrain is a neighbor to a terrain containing a
	 * cloister erection.
	 * 
	 * @return {@code true} if this terrain is a neighbor to a terrain
	 *         containing a cloister erection. {@code false} otherwise.
	 */
	public boolean isCloisterLinked() {
		Coordinate thisCoord = new Coordinate(x, y);
		Coordinate masterCoord = terrainType.getMasterCoordinateFrom(thisCoord);
		Coordinate slaveCoord = terrainType.getSlaveCoordinateFrom(masterCoord);

		// Form a set of coordinates of neighbors for both cells.
		// Note that if masterCoord and slaveCoord are same (the cell is not
		// merged) then duplicates will be added. However HashSet ignored
		// duplicates.
		HashSet<Coordinate> neighbors = new HashSet<Coordinate>();
		// Master...
		neighbors.add(masterCoord.north());
		neighbors.add(masterCoord.south());
		neighbors.add(masterCoord.west());
		neighbors.add(masterCoord.east());
		// Slave...
		neighbors.add(slaveCoord.north());
		neighbors.add(slaveCoord.south());
		neighbors.add(slaveCoord.west());
		neighbors.add(slaveCoord.east());
		// Finally remove both master and slave. We added them since they are
		// neighbors for each other. While they should not be counted.
		// Again if masterCoord and slaveCoord are same (the cell is not merged)
		// neither of them will be added so removing them changes nothing.
		neighbors.remove(masterCoord);
		neighbors.remove(slaveCoord);

		// Finally check all the neighbors.
		for(Coordinate coord : neighbors) {
			if (landscape.isCloisterAt(coord)) {
				return true;
			}
		}

		return false;
	}

	public void setTerrainUse(TerrainUseEnum terrainUse) {
		this.terrainUse = terrainUse;
	}

	public String getCoords() {
		return "("+x+","+y+")";
	}
	
	public Coordinate getCoordinate() {
		return new Coordinate(x, y);
	}
}
