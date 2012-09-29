package com.philihp.weblabora.model;

import static com.philihp.weblabora.model.TerrainTypeEnum.*;

import java.util.List;

public class CommandCutPeat implements MoveCommand, InvalidDuringSettlement {

	@Override
	public void execute(Board board, CommandParameters params)
			throws WeblaboraException {

		boolean joker = false;
		if(params.size() > 2) {
			String third = params.get(2);
			joker = third.equals("Jo");
		}
		
		execute(board,
				Integer.parseInt(params.get(0)),
				Integer.parseInt(params.get(1)),
				joker);
	}

	public static void execute(Board board, int x, int y, boolean joker)
			throws WeblaboraException {
		Player player = board.getPlayer(board.getActivePlayer());
		Terrain spot = player.getLandscape().getTerrain().get(y, x);

		if(spot == null) {
			throw new WeblaboraException("Could not cut peat at ("+x+","+y+") because it does not exist.");
		}
		
		
		if (spot.getTerrainType() != MOOR)
			throw new WeblaboraException("Tried to Cut Peat on "
					+ spot.getTerrainType() + " at (" + x + "," + y
					+ ") for player " + board.getActivePlayerColor()
					+ ", but it is not moor");

		spot.setTerrainType(PLAINS);
		Token token = joker?board.getWheel().getJoker():board.getWheel().getPeat();
		int peatTaken = token.take();
		player.setPeat(player.getPeat() + peatTaken);

		System.out.println("Cutting peat at " + x + "," + y + "; got peat: "
				+ peatTaken);
	}
}
