package com.philihp.weblabora.model;

public class MoveHistory {
	private boolean previousUse = false;

	public String toString() {
		return "{previousUse=" + previousUse + "}";
	}

	public boolean isPreviousUse() {
		return previousUse;
	}

	public void setPreviousUse(boolean isPreviousUse) {
		this.previousUse = isPreviousUse;
	}
}
