package com.philihp.weblabora.model;

public class StartingMarker {
	
	private Player owner;
	private int cost;

	public StartingMarker(Player owner) {
		setCost(1);
		setOwner(owner);
	}

	protected Player getOwner() {
		return owner;
	}

	protected void setOwner(Player newOwner) {
		if(this.owner != null && this.owner != newOwner) //if marker changes hands
			this.owner.setStartingMarker(null);
		
		this.owner = newOwner;
		
		if(newOwner.getStartingMarker() == null)
			newOwner.setStartingMarker(this);
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}
	
}
