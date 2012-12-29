package com.philihp.weblabora.model;

public class BuildCost {
	private int wood = 0;
	private int clay = 0;
	private int stone = 0;
	private int straw = 0;
	private int coin = 0;

	private BuildCost() {
	}
	
	public String toString() {
		StringBuilder s = new StringBuilder();
		if(getWood() > 0) s.append(", "+getWood()+" wood");
		if(getClay() > 0) s.append(", "+getClay()+" clay");
		if(getStone() > 0) s.append(", "+getStone()+" stone");
		if(getStraw() > 0) s.append(", "+getStraw()+" straw");
		if(getCoin() > 0) s.append(", "+getCoin()+" coins");
		if("".equals(s.toString())) return "";
		return s.toString().substring(2);
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

}
