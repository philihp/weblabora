package com.philihp.weblabora.model;

import static com.philihp.weblabora.model.TerrainTypeEnum.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import com.philihp.weblabora.model.building.*;
import com.philihp.weblabora.util.IntegerUtil;

public class CommandUse implements MoveCommand, InvalidDuringSettlement {

	@Override
	public void execute(Board board, CommandParameters params)
			throws WeblaboraException {

		BuildingEnum buildingId = null;
		//TODO: this try/catch should be put into a method on BuildingEnum
		try {
			buildingId = BuildingEnum.valueOf(params.get(0));
		}
		catch(IllegalArgumentException e) {
			String suggestedId = params.get(0).substring(1);
			if(params.get(0).charAt(0) == 'F') suggestedId = 'G'+suggestedId;
			if(params.get(0).charAt(0) == 'G') suggestedId = 'F'+suggestedId;
			throw new WeblaboraException("Unknown building ID \""+params.get(0)+"\", did you mean \""+suggestedId+"\"?");
		}
		
		UsageParam usageParam = null;
		boolean usingPrior = params.getSuffix().contains("*") || params.isMustBePrior();

		if(params.size() == 1) {
			usageParam = new UsageParamSingle("");
		}
		else if(params.size() == 2) {
			usageParam = new UsageParamSingle(params.get(1));
		}
		else if(params.size() == 3 && IntegerUtil.isInteger(params.get(2)) == false) {
			usageParam = new UsageParamDouble(params.get(1));
			((UsageParamDouble)usageParam).setSecondary(new UsageParamSingle(params.get(2)));
		}
		else {
			usageParam = new UsageParamCoordinates("");
			Integer x = null;
			for (int i = 1; i < params.size(); i++) {
				if(x == null) {
					x = Integer.parseInt(params.get(i));
				}
				else {
					((UsageParamCoordinates)usageParam).pushCoordinate(
						x, Integer.parseInt(params.get(i)));
					x = null;
				}
			}
			if(x != null) {
				throw new WeblaboraException("Coordinate building usage parameters must come in pairs. Parsed "+x+" for the x, but no y number.");
			}
		}

		usageParam.setHistory(params.getHistory());
		
		execute(board, buildingId, usageParam, usingPrior, params.getPlaceClergyman());

		System.out.println("Using " + buildingId + " with " + usageParam);
	}

	private static void execute(Board board, BuildingEnum buildingId,
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
