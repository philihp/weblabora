package com.philihp.weblabora.model;

public class Clergyman {
	
	public enum Type {
		LAYBROTHER, PRIOR;
	}
	
	private Player owner;
	
	private Type type;
	
	protected Terrain location;
	
	public Clergyman(Player owner, Type type) {
		this.owner = owner;
		this.type = type;
	}

	public Player getOwner() {
		return owner;
	}

	public Type getType() {
		return type;
	}

	public Terrain getLocation() {
		return location;
	}
	
	public void clearLocation() {
		if(this.location != null) {
			Terrain oldLocation = this.location;
			this.location = null;
			oldLocation.getErection().clearClergyman();
		}
	}

	public void setLocation(Terrain location) throws WeblaboraException {
		if (location.getErection().getClergyman() != null)
			throw new WeblaboraException("The building "
					+ location.getErection() + " already has a clergyman "
					+ location.getErection().getClergyman().getType()); 
		
		this.location = location;
		location.getErection().setClergyman(this);
	}
	
	public static String format(String s) {
		if("LAYBROTHER".equals(s)) return "building-laybrother";
		else if("PRIOR".equals(s)) return "building-prior";
		else return null;
	}

}
