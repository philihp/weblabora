package com.philihp.weblabora.model.building;

import com.philihp.weblabora.model.Player;

public abstract class Card {

	protected int x;
	protected int y;
	protected Player owner;

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Player getOwner() {
		return owner;
	}

	public void setOwner(Player owner) {
		this.owner = owner;
	}

}
