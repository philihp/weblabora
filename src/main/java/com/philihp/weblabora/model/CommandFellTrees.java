package com.philihp.weblabora.model;

public class CommandFellTrees {
	private CommandFellTrees() {
	}
	public static void execute(Board board, int x, int y) throws WeblaboraException {
		Player player = board.getPlayer(board.getActivePlayer());
		System.out.println("Felling trees at "+x+","+y);
	}
}
