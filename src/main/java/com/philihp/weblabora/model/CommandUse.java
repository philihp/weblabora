package com.philihp.weblabora.model;

import static com.philihp.weblabora.model.TerrainTypeEnum.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import com.philihp.weblabora.model.building.*;

public class CommandUse implements MoveCommand {

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
			for (int i = 0; params.get(i * 2 + 1) != null
					&& params.get(i * 2 + 2) != null; i++) {
				usageParam.pushCoordinate(
						Integer.parseInt(params.get(i * 2 + 1)),
						Integer.parseInt(params.get(i * 2 + 2)));
			}
			break;
		}

		execute(board, BuildingEnum.valueOf(buildingId), usageParam, usingPrior, params.getPlaceClergyman());

		System.out.println("Using " + buildingId + " with " + usageParam);
	}

	public static void execute(Board board, BuildingEnum buildingId,
			UsageParam param, boolean usingPrior, boolean placeClergyman) throws WeblaboraException {
		Building building = board.findBuildingInstance(buildingId);
		Player buildingOwner = building.getLocation().getLandscape().getPlayer();
		Terrain location = building.getLocation();

		if (placeClergyman) {
			if (usingPrior) {
				buildingOwner.placePrior(location);
			} else {
				buildingOwner.placeClergyman(location);
			}
		}

		building.use(board, param);
	}
}
