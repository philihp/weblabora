package com.philihp.weblabora.model;

public class MoveHistory {
	private boolean previousUse = false;
	private boolean settling;
	private boolean previousBuild = false;

	public MoveHistory(boolean settling) {
		this.settling = settling;
	}

	public boolean isSettling() {
		return settling;
	}

	public boolean isPreviousBuild() {
		return previousBuild;
	}

	public void setPreviousBuild(boolean previousBuild) {
		this.previousBuild = this.previousBuild || previousBuild;
	}

	public boolean isPreviousUse() {
		return previousUse;
	}

	public void setPreviousUse(boolean isPreviousUse) {
		this.previousUse = this.previousUse || isPreviousUse;
	}
}
