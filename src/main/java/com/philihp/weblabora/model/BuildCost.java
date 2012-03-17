package com.philihp.weblabora.model;

public class BuildCost {
	private int wood = 0;
	private int clay = 0;
	private int stone = 0;
	private int straw = 0;
	private int coin = 0;
	private int peat = 0;

	private BuildCost() {
	}

	public static BuildCost is() {
		return new BuildCost();
	}

	public BuildCost wood(int quantity) {
		wood += quantity;
		return this;
	}

	public BuildCost clay(int quantity) {
		clay += quantity;
		return this;
	}

	public BuildCost stone(int quantity) {
		stone += quantity;
		return this;
	}

	public BuildCost straw(int quantity) {
		straw += quantity;
		return this;
	}

	public BuildCost coin(int quantity) {
		coin += quantity;
		return this;
	}
	
	public BuildCost peat(int quantity) {
		peat += quantity;
		return this;
	}

	public int getWood() {
		return wood;
	}

	public int getClay() {
		return clay;
	}

	public int getStone() {
		return stone;
	}

	public int getStraw() {
		return straw;
	}

	public int getCoin() {
		return coin;
	}
	
	public int getPeat() {
		return peat;
	}

}
