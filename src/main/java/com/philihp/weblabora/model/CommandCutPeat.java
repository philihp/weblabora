package com.philihp.weblabora.model;

public class CommandCutPeat {
	private CommandCutPeat() {
	}
	public static void execute(Board board, int x, int y) throws WeblaboraException  {
		System.out.println("Cutting peat at "+x+","+y);
	}
}
