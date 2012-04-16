package com.philihp.weblabora.model.building;

import java.util.Set;

import com.philihp.weblabora.model.Clergyman;
import com.philihp.weblabora.model.Landscape;
import com.philihp.weblabora.model.Player;
import com.philihp.weblabora.model.Terrain;
import com.philihp.weblabora.model.TerrainTypeEnum;
import com.philihp.weblabora.model.WeblaboraException;

public abstract class Erection {

	protected Terrain location;
	private Clergyman clergyman;

	private String id;
	private String name;
	private final Set<TerrainTypeEnum> terrains;

	public Erection(String id, String name, Set<TerrainTypeEnum> terrains) {
		//id, name, and terrains are the things common to both Settlement and Building
		this.id = id;
		this.name = name;
		this.terrains = terrains;
	}

	public Clergyman getClergyman() {
		return clergyman;
	}
	
	public void clearClergyman() {
		if(this.clergyman != null) {
			Clergyman oldClergyman = this.clergyman;
			this.clergyman = null;
			if(oldClergyman != null) oldClergyman.clearLocation();
		}
	}

	public void setClergyman(Clergyman clergyman) throws WeblaboraException {
		if (getClergyman() != null)
			throw new WeblaboraException("Tried to place a clergyman on "
					+ this + " when it already has a "
					+ getClergyman().getType());
		this.clergyman = clergyman;
	}

	public Terrain getLocation() {
		return location;
	}

	public void setLocation(Terrain location) {
		this.location = location;
		if (location.getErection() != this)
			location.setErection(this);
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	public Set<TerrainTypeEnum> getTerrains() {
		return terrains;
	}

}
