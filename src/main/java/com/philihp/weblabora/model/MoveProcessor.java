package com.philihp.weblabora.model;

public final class MoveProcessor {
	private MoveProcessor() {
	}

	public static void processMove(Board board, String move) {
		board.currentMove++;

		
		
		
		
		
		if (board.currentMove % 5 == 1) {
			board.getWheel().pushArm();
		}
	}
}
