package com.philihp.weblabora.model;

import static com.philihp.weblabora.model.Wheel.Position.*;

public class Wheel {
	
	protected Board board;
	
	protected int[] armValues;  
	
	public enum Position {
		A, B, C, D, E, F, G, H, I, J, K, L, M;
		
		public Position next() {
			int i = this.ordinal()+1;
			if(i == Position.values().length) i = 0;
			return Position.values()[i];
		}
	}
	

	protected Token grain = new Token(this,-87);

	protected Token peat = new Token(this,-51);
	
	protected Token sheep = new Token(this,-96);
	
	protected Token clay = new Token(this,-60);
	
	protected Token coin = new Token(this,-42);
	
	protected Token wood = new Token(this,-69);
	
	protected Token grape = new Token(this,-105);

	protected Token stone = new Token(this,-114);
	
	protected Token joker = new Token(this,-78);

	protected Token arm = new Token(this,0);
	
	public Wheel(Board board, int[] armValues) {
		this.board = board;
		this.armValues = armValues;
		this.arm.setPosition(M);
		this.grape.setActive(false);
		this.stone.setActive(false);
	}

	public Board getBoard() {
		return board;
	}

	public Token getGrain() {
		return grain;
	}

	public Token getSheep() {
		return sheep;
	}

	public Token getClay() {
		return clay;
	}

	public Token getCoin() {
		return coin;
	}

	public Token getWood() {
		return wood;
	}

	public Token getGrape() {
		return grape;
	}

	public Token getStone() {
		return stone;
	}
	
	public Token getJoker() {
		return joker;
	}
	
	public Token getPeat() {
		return peat;
	}

	public Token getArm() {
		return arm;
	}
	
	public int[] getArmValues() {
		return armValues;
	}
	
	public void pushArm(int round) {
		Position next = arm.getPosition().next();
		// this ensures that if something is at 10, it stays at 10
		if(grain.getPosition() == next && grain.isActive()) grain.setPosition(grain.getPosition().next());
		if(sheep.getPosition() == next && sheep.isActive()) sheep.setPosition(sheep.getPosition().next());
		if(clay.getPosition() == next  && clay.isActive())  clay.setPosition( clay.getPosition().next());
		if(coin.getPosition() == next  && coin.isActive())  coin.setPosition( coin.getPosition().next());
		if(wood.getPosition() == next  && wood.isActive())  wood.setPosition( wood.getPosition().next());
		if(joker.getPosition() == next && joker.isActive()) joker.setPosition(joker.getPosition().next());
		if(peat.getPosition() == next  && peat.isActive())  peat.setPosition( peat.getPosition().next());
		if(grape.getPosition() == next && grape.isActive()) grape.setPosition(grape.getPosition().next());
		if(stone.getPosition() == next && stone.isActive()) stone.setPosition(stone.getPosition().next());
		arm.setPosition(next);
	}
	
}
