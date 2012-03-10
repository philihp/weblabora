package com.philihp.weblabora.model;

import static com.philihp.weblabora.model.TerrainTypeEnum.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import com.philihp.weblabora.model.building.*;

public class CommandBuild implements MoveCommand {


	@Override
	public void execute(Board board, List<String> params)
			throws WeblaboraException {
		
		execute(board,
				BuildingEnum.getInstance(params.get(0)),
				Integer.parseInt(params.get(1)),
				Integer.parseInt(params.get(2)));
	}
	
	public static void execute(Board board, AbstractBuilding building, int x, int y)
			throws WeblaboraException {
		Player player = board.getPlayer(board.getActivePlayer());
		Terrain spot = player.getLandscape().getTerrain().get(y, x);

		if (spot.getTerrainType() != FOREST)
			throw new WeblaboraException("Tried to Fell Trees on "
					+ spot.getTerrainType() + " at (" + x + "," + y
					+ ") for player " + board.getActivePlayer()
					+ ", but it is not a forest");

		spot.setTerrainType(PLAINS);
		int woodTaken = board.getWheel().getWood().take();
		player.setWood(player.getWood() + woodTaken);
		// TODO could use joker

		System.out.println("Felling trees at " + x + "," + y + "; got wood: "
				+ woodTaken);
	}
}
