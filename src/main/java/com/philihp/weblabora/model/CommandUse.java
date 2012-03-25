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
		
		String buildingId = params.get(0);
		UsageParam usageParam = null;
		if(params.get(2) != null) {
			usageParam = new UsageParam("");
			for(int i = 0; params.get(i*2+1) != null && params.get(i*2+2) != null; i++) {
				usageParam.pushCoordinate(Integer.parseInt(params.get(i*2+1)), Integer.parseInt(params.get(i*2+2)));
			}
		}
		else {
			usageParam = new UsageParam(params.get(1));
		}
		
		execute(board,
				BuildingEnum.valueOf(buildingId),
				usageParam
				);
		
		System.out.println("Using "+buildingId+" with "+usageParam);
	}
	
	public static void execute(Board board, BuildingEnum buildingId, UsageParam param)
			throws WeblaboraException {
		Building building = board.findBuildingInstance(buildingId);
		building.use(board, param);
	}
}
