package com.philihp.weblabora.model;

import static com.philihp.weblabora.model.TerrainTypeEnum.*;

public class CommandFellTrees {
	private CommandFellTrees() {
	}

	public static void execute(Board board, int x, int y) throws WeblaboraException {
		Player player = board.getPlayer(board.getActivePlayer());
		Terrain spot = player.getLandscape().getTerrain().get(x, y);

		if (spot.getTerrainType() != FOREST)
			throw new WeblaboraException("Tried to Fell Trees on " + spot.getTerrainType() + " at (" + x + "," + y
					+ ") for player " + board.getActivePlayer() + ", but it is not a forest");

		spot.setTerrainType(PLAINS);
		int woodTaken = board.getWheel().getWood().take();
		player.setWood(player.getWood() + woodTaken);
		// TODO could use joker

		System.out.println("Felling trees at " + x + "," + y + "; got wood: " + woodTaken);
	}
}
