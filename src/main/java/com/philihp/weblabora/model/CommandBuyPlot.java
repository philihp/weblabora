package com.philihp.weblabora.model;

import static com.philihp.weblabora.model.TerrainTypeEnum.*;

import java.util.List;

public class CommandBuyPlot implements MoveCommand {
	
	public static enum Side {
		COAST, MOUNTAIN
	};

	@Override
	public void execute(Board board, CommandParameters params)
			throws WeblaboraException {
		
		execute(board,
				Integer.parseInt(params.get(0)),
				Side.valueOf(params.get(1))
				);
	}

	public static void execute(Board board, int y, Side side)
			throws WeblaboraException {
		Player player = board.getPlayer(board.getActivePlayer());
	}
}
