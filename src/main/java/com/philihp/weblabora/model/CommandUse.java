package com.philihp.weblabora.model;

import static com.philihp.weblabora.model.TerrainTypeEnum.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import com.philihp.weblabora.model.building.*;

public class CommandUse implements MoveCommand {


	@Override
	public void execute(Board board, List<String> params)
			throws WeblaboraException {
		
		execute(board,
				BuildingEnum.valueOf(params.get(0)),
				new UsageParam(params.get(1))
				);
		
		System.out.println("Using "+params.get(0)+" ("+params.get(1)+")");
	}
	
	public static void execute(Board board, BuildingEnum buildingId, UsageParam param)
			throws WeblaboraException {
		AbstractBuilding building = board.findBuildingInstance(buildingId);
		building.use(board, param);
	}
}
