package com.philihp.weblabora.model;

import static com.philihp.weblabora.model.Wheel.Position.*;

public class Wheel {
	
	protected Board game;
	
	protected int[] armValues = {0, 2, 3, 4, 5, 6, 6, 7, 7, 8, 8, 9, 10};  
	
	public enum Position {
		A, B, C, D, E, F, G, H, I, J, K, L, M;
		
		public Position next() {
			int i = this.ordinal()+1;
			if(i == Position.values().length) i = 0;
			return Position.values()[i];
		}
	}
	

	protected Token grain = new Token(this);

	protected Token peat = new Token(this);
	
	protected Token sheep = new Token(this);
	
	protected Token clay = new Token(this);
	
	protected Token coin = new Token(this);
	
	protected Token wood = new Token(this);
	
	protected Token grape = new Token(this);

	protected Token stone = new Token(this);
	
	protected Token joker = new Token(this);

	protected Token arm = new Token(this);
	
	public Wheel(Board game) {
		this.game = game;
		this.arm.setPosition(M);
		this.grape.setPosition(H);
	}

	public Board getGame() {
		return game;
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
	
	public void pushArm(int round) {
		Position next = arm.getPosition().next();
		// this ensures that if something is at 10, it stays at 10
		if(grain.getPosition() == next) grain.setPosition(grain.getPosition().next());
		if(sheep.getPosition() == next) sheep.setPosition(sheep.getPosition().next());
		if(clay.getPosition() == next)  clay.setPosition( clay.getPosition().next());
		if(coin.getPosition() == next)  coin.setPosition( coin.getPosition().next());
		if(wood.getPosition() == next)  wood.setPosition( wood.getPosition().next());
		if(joker.getPosition() == next) joker.setPosition(joker.getPosition().next());
		if(peat.getPosition() == next)  peat.setPosition( peat.getPosition().next());
		if(grape.getPosition() == next && round > 8) grape.setPosition(grape.getPosition().next());
		if(stone.getPosition() == next && round > 13) stone.setPosition(stone.getPosition().next());
		arm.setPosition(next);
	}
	
}
