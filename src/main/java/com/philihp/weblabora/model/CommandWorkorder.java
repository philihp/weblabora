package com.philihp.weblabora.model;

import static com.philihp.weblabora.model.TerrainTypeEnum.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import com.philihp.weblabora.model.building.*;

public class CommandWorkorder implements MoveCommand {


	@Override
	public void execute(Board board, CommandParameters params)
			throws WeblaboraException {
		
		execute(board,
				Color.toColor(params.get(0).charAt(0)),
				new UsageParam(params.get(1))
				);
		
		System.out.println("Workorder "+params.get(0)+" with payment "+params.get(1));
	}
	
	public static void execute(Board board, Color orderedPlayerColor, UsageParam payment)
			throws WeblaboraException {
		Player activePlayer = board.getPlayer(board.getActivePlayer());
		Player orderedPlayer = board.getPlayer(orderedPlayerColor.ordinal());

		if(payment.isWithGift()) {
			activePlayer.setWhiskey(activePlayer.getWhiskey() - payment.getWhiskey());
			activePlayer.setWine(activePlayer.getWine() - payment.getWine());
		}
		else {
			activePlayer.setPenny(activePlayer.getPenny() - payment.getPenny());
			orderedPlayer.setPenny(orderedPlayer.getPenny() + payment.getPenny());
		}
	}
}
