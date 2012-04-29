package com.philihp.weblabora.model;

public enum SettlementRound {
	S,A,B,C,D,E;

	public SettlementRound next() {
		if(this.ordinal() < SettlementRound.values().length) {
			return SettlementRound.values()[this.ordinal()+1];
		}
		else return null;
	}
}
