package com.philihp.weblabora.model;

import static com.philihp.weblabora.model.Wheel.Position.*;

import com.philihp.weblabora.model.Wheel.Position;

public class Token {
	private Position position;
	private Wheel wheel;
	private boolean active = true;
	private int radius;
	protected Token(Wheel wheel, int radius) {
		this.wheel = wheel;
		this.position = M;
		this.radius = radius;
	}
	protected int value() {
		return 0;
	}
	public int take() {
		int i = wheel.arm.position.ordinal() - position.ordinal();
		if(i < 0) i += wheel.armValues.length;
		position = wheel.arm.position;
		return wheel.armValues[i];
	}
	public Position getPosition() {
		return position;
	}
	public void setPosition(Position position) {
		this.position = position;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public int getRadius() {
		if(isActive()) {
			return radius;
		}
		else {
			return -140;
		}
	}
}
