package com.philihp.weblabora.model;

public class MoveHistory {
	private boolean previousUse = false;
	private boolean settling;

	public MoveHistory(boolean settling) {
		this.settling = settling;
	}

	public boolean isSettling() {
		return settling;
	}

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
