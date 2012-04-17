package com.philihp.weblabora.model;

import static com.philihp.weblabora.model.TerrainTypeEnum.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import com.philihp.weblabora.model.building.*;

public class CommandSettle implements MoveCommand {

	@Override
	public void execute(Board board, CommandParameters params)
			throws WeblaboraException {
		
		execute(board,
				params.get(0),
				Integer.parseInt(params.get(1)),
				Integer.parseInt(params.get(2)));
		
		System.out.println("Settling "+params.get(0)+" at ("+params.get(1)+","+params.get(2)+")");
	}
	
	public static void execute(Board board, String buildingId, int x, int y)
			throws WeblaboraException {
	}
}
