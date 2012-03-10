package com.philihp.weblabora.model;

import static com.philihp.weblabora.model.TerrainTypeEnum.*;

import java.util.List;

public class CommandCutPeat implements MoveCommand {

	@Override
	public void execute(Board board, List<String> params)
			throws WeblaboraException {
		execute(board,
				Integer.parseInt(params.get(0)),
				Integer.parseInt(params.get(1)));
	}

	public static void execute(Board board, int x, int y)
			throws WeblaboraException {
		Player player = board.getPlayer(board.getActivePlayer());
		Terrain spot = player.getLandscape().getTerrain().get(y, x);

		if (spot.getTerrainType() != MOOR)
			throw new WeblaboraException("Tried to Cut Peat on "
					+ spot.getTerrainType() + " at (" + x + "," + y
					+ ") for player " + board.getActivePlayer()
					+ ", but it is not moor");

		spot.setTerrainType(PLAINS);
		int peatTaken = board.getWheel().getPeat().take();
		player.setPeat(player.getPeat() + peatTaken);
		// TODO could use joker

		System.out.println("Cutting peat at " + x + "," + y + "; got peat: "
				+ peatTaken);
	}
}
