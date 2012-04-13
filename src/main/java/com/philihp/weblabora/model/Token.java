package com.philihp.weblabora.model;

import static com.philihp.weblabora.model.Wheel.Position.*;

import com.philihp.weblabora.model.Wheel.Position;

public class Token {
	private Position position;
	private Wheel wheel;
	protected Token(Wheel wheel) {
		this.wheel = wheel;
		this.position = M;
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
}
