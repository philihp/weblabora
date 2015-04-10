package com.philihp.weblabora.model;

import java.util.*;
import static com.philihp.weblabora.model.Wheel.Position.*;

public class Wheel {

	private Board board;

	protected int[] armValues;

	protected enum Position {
		A, B, C, D, E, F, G, H, I, J, K, L, M;

		public Position next() {
			int i = this.ordinal()+1;
			if(i == Position.values().length) i = 0;
			return Position.values()[i];
		}
		public Position prev() {
			int i = this.ordinal()-1;
			if(i < 0) i = Position.values().length - 1;
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

	private Position[] getPositionsFromArm() {
		Position[] positionsFromArm = new Position[Position.values().length];
		Position p = arm.getPosition();
		for(int i=0; i<Position.values().length; i++) {
			positionsFromArm[i] = p;
			p = p.prev();
		}
		return positionsFromArm;
	}

	public String[][] getTable() {
		Token[] tokens = { grain, peat, sheep, clay, coin, wood, grape, stone, joker };
		String[] names = { "grain", "peat", "sheep", "clay", "coin", "wood", "grape", "stone", "joker" };
		String[][] table = new String[Position.values().length][];
		Position[] positionsFromArm = getPositionsFromArm();
		for(int i=0; i<positionsFromArm.length; i++) {
			ArrayList<String> tokensInColumn = new ArrayList<>();
			for(int j=0; j<tokens.length; j++) {
				if(tokens[j].getPosition() == positionsFromArm[i]) {
					tokensInColumn.add(names[j]);
				}
			}
			table[i] = tokensInColumn.toArray(new String[tokensInColumn.size()]);
		}
		return table;
	}

	public void pushArm(int round) {
		Position next = arm.getPosition().next();
		// this ensures that if something is at 10, it stays at 10, unless we don't want it to.
		if(board.getMode().getPlayers() == GamePlayers.ONE) {
			if(grain.getPosition() == next && grain.isActive()) grain.setActive(false);
			if(sheep.getPosition() == next && sheep.isActive()) sheep.setActive(false);
			if(clay.getPosition() == next  && clay.isActive())  clay.setActive(false);
			if(coin.getPosition() == next  && coin.isActive())  coin.setActive(false);
			if(wood.getPosition() == next  && wood.isActive())  wood.setActive(false);
			if(joker.getPosition() == next && joker.isActive()) joker.setActive(false);
			if(peat.getPosition() == next  && peat.isActive())  peat.setActive(false);
			if(grape.getPosition() == next && grape.isActive()) grape.setActive(false);
			if(stone.getPosition() == next && stone.isActive()) stone.setActive(false);
		}
		else {
			if(grain.getPosition() == next && grain.isActive()) grain.setPosition(grain.getPosition().next());
			if(sheep.getPosition() == next && sheep.isActive()) sheep.setPosition(sheep.getPosition().next());
			if(clay.getPosition() == next  && clay.isActive())  clay.setPosition( clay.getPosition().next());
			if(coin.getPosition() == next  && coin.isActive())  coin.setPosition( coin.getPosition().next());
			if(wood.getPosition() == next  && wood.isActive())  wood.setPosition( wood.getPosition().next());
			if(joker.getPosition() == next && joker.isActive()) joker.setPosition(joker.getPosition().next());
			if(peat.getPosition() == next  && peat.isActive())  peat.setPosition( peat.getPosition().next());
			if(grape.getPosition() == next && grape.isActive()) grape.setPosition(grape.getPosition().next());
			if(stone.getPosition() == next && stone.isActive()) stone.setPosition(stone.getPosition().next());
		}
		arm.setPosition(next);
	}

}
