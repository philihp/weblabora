package com.philihp.weblabora.model;

public class MoveHistory {
	private boolean previousUse = false;
	private boolean settling;
	private boolean previousBuild = false;
	private boolean nextPlotFree = false;
	private boolean nextDistrictFree = false;

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
		if(previousBuild) this.previousUse = false;
	}

	public boolean isPreviousUse() {
		return previousUse;
	}

	public void setPreviousUse(boolean isPreviousUse) {
		this.previousUse = this.previousUse || isPreviousUse;
	}

	public boolean isNextDistrictFree() {
		return nextDistrictFree;
	}
	public boolean isNextPlotFree() {
		return nextPlotFree;
	}
	public void setNextDistrictFree(boolean nextDistrictFree) {
		this.nextDistrictFree = nextDistrictFree;
	}
	public void setNextPlotFree(boolean nextPlotFree) {
		this.nextPlotFree = nextPlotFree;
	}
}
