package com.philihp.weblabora.model;

import static com.philihp.weblabora.model.TerrainTypeEnum.*;

import java.util.List;

public class CommandFellTrees implements MoveCommand, InvalidDuringSettlement {

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

		if(spot == null) throw new WeblaboraException("Could not fell trees at ("+x+","+y+") because it does not exist.");
		
		if (spot.getTerrainType() != FOREST)
			throw new WeblaboraException("Tried to Fell Trees on "
					+ spot.getTerrainType() + " at (" + x + "," + y
					+ ") for player " + board.getActivePlayerColor()
					+ ", but it is not a forest");

		spot.setTerrainType(PLAINS);
		Token token = joker?board.getWheel().getJoker():board.getWheel().getWood();
		int woodTaken = token.take();
		player.setWood(player.getWood() + woodTaken);

		System.out.println("Felling trees at " + x + "," + y + "; got wood: "
				+ woodTaken);
	}
}
