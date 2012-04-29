package com.philihp.weblabora.model;

import static com.philihp.weblabora.model.TerrainTypeEnum.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import com.philihp.weblabora.model.building.*;

public class CommandUse implements MoveCommand, InvalidDuringSettlement {

	@Override
	public void execute(Board board, CommandParameters params)
			throws WeblaboraException {

		String buildingId = params.get(0);
		UsageParam usageParam = null;
		boolean usingPrior = params.getSuffix().contains("*");

		switch (params.size()) {
		case 1:
			usageParam = new UsageParam("");
			break;
		case 2:
			usageParam = new UsageParam(params.get(1));
			break;
		default:
			usageParam = new UsageParam("");
			Integer x = null;
			for (int i = 1; i < params.size(); i++) {
				if(x == null) {
					x = Integer.parseInt(params.get(i));
				}
				else {
					usageParam.pushCoordinate(
						x, Integer.parseInt(params.get(i)));
					x = null;
				}
			}
			if(x != null) {
				throw new WeblaboraException("Coordinate building usage parameters must come in pairs. Parsed "+x+" for the x, but no y number.");
			}
			break;
		}

		execute(board, BuildingEnum.valueOf(buildingId), usageParam, usingPrior, params.getPlaceClergyman());

		System.out.println("Using " + buildingId + " with " + usageParam);
	}

	public static void execute(Board board, BuildingEnum buildingId,
			UsageParam param, boolean usingPrior, boolean placeClergyman) throws WeblaboraException {
		Building building = board.findBuildingInstance(buildingId);
		Terrain location = building.getLocation();

		if (placeClergyman) {
			if(building.getLocation() == null)
				throw new WeblaboraException("Can't place a clergyman on "+building+" because it hasn't been built yet.");
			Player buildingOwner = building.getLocation().getLandscape().getPlayer();
			if (usingPrior) {
				buildingOwner.placePrior(location);
			} else {
				buildingOwner.placeClergyman(location);
			}
		}

		building.use(board, param);
	}
}
