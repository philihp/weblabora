package com.philihp.weblabora.model.building;

import com.philihp.weblabora.model.Clergyman;
import com.philihp.weblabora.model.Landscape;
import com.philihp.weblabora.model.Player;
import com.philihp.weblabora.model.Terrain;
import com.philihp.weblabora.model.WeblaboraException;

public abstract class Erection {

	protected Terrain location;
	private Clergyman clergyman;

	public Clergyman getClergyman() {
		return clergyman;
	}

	public void setClergyman(Clergyman clergyman) throws WeblaboraException{
		if(getClergyman() != null)
			throw new WeblaboraException("Tried to place a clergyman on "+this+" when it already has a "+getClergyman().getType()); 
		this.clergyman = clergyman;
	}

	public Terrain getLocation() {
		return location;
	}

	public void setLocation(Terrain location) {
		this.location = location;
		if(location.getErection() != this)
			location.setErection(this);
	}

	

}
