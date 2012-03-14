package com.philihp.weblabora.model.building;

import com.philihp.weblabora.model.Landscape;
import com.philihp.weblabora.model.Player;

public abstract class Erection {

	private Landscape owner;

	public Landscape getOwner() {
		return owner;
	}

	public void setOwner(Landscape owner) {
		this.owner = owner;
	}
	
	

}
