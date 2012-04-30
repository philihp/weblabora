package com.philihp.weblabora.model;

public class Coordinate {
	private int x;
	private int y;

	public Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public Coordinate north() {
		return new Coordinate(x, y-1);
	}
	
	public Coordinate south() {
		return new Coordinate(x, y+1);
	}
	
	public Coordinate east() {
		return new Coordinate(x+1, y);
	}
	
	public Coordinate west() {
		return new Coordinate(x-1, y);
	}
}