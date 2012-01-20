package com.philihp.weblabora.model;

import static com.philihp.weblabora.model.Wheel.Position.*;

public class Wheel {
	
	protected Board game;
	
	protected int[] armValues = {0, 2, 3, 4, 5, 6, 6, 7, 7, 8, 8, 9, 10};  
	
	public enum Position {
		A, B, C, D, E, F, G, H, I, J, K, L, M;
		
		protected Position next() {
			int i = this.ordinal()+1;
			if(i == Position.values().length) i = 0;
			return Position.values()[i];
		}
	}
	
	public class Token {
		protected Position position;
		protected Wheel wheel;
		protected Token(Wheel wheel) {
			this.wheel = wheel;
			this.position = A;
		}
		protected int value() {
			return 0;
		}
		protected int take() {
			int i = wheel.arm.position.ordinal() - position.ordinal();
			if(i < 0) i += armValues.length;
			position = wheel.arm.position;
			return armValues[i];
		}
		public Position getPosition() {
			return position;
		}
	}

	protected Token grain = new Token(this);
	
	protected Token sheep = new Token(this);
	
	protected Token clay = new Token(this);
	
	protected Token coin = new Token(this);
	
	protected Token wood = new Token(this);
	
	protected Token grape = new Token(this);
	
	protected Token stone = new Token(this);

	protected Token arm = new Token(this);
	
	public Wheel(Board game) {
		this.game = game;
	}
	
	public void pushWheel() {
		arm.position = arm.position.next();
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

	public Token getArm() {
		return arm;
	}
	
}
