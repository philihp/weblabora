package com.philihp.weblabora.model;

import static com.philihp.weblabora.model.TerrainTypeEnum.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import com.philihp.weblabora.model.building.*;

public class CommandConvert implements MoveCommand {

	@Override
	public void execute(Board board, CommandParameters params)
			throws WeblaboraException {

		UsageParam usageParam = new UsageParam(params.get(0));

		execute(board, usageParam);

		System.out.println("Converting " + usageParam);
	}

	public static void execute(Board board, UsageParam param) throws WeblaboraException {
		
		if(param.getPenny() % 5 != 0) {
			throw new WeblaboraException("Can only convert pennies in multiples of 5. Are you sure you meant to convert "+param.getPenny()+"?");
		}

		Player player = board.getPlayer(board.getActivePlayer());
		
		player.subtractGrain(param.getGrain());
		player.addStraw(param.getGrain());
		
		player.subtractWine(param.getWine());
		player.addPenny(param.getWine());
		
		player.subtractNickel(param.getNickel());
		player.addPenny(param.getNickel()*5);

		player.subtractWhiskey(param.getWhiskey());
		player.addPenny(param.getWhiskey()*2);
		
		player.subtractCoins(param.getPenny());
		player.addNickel(param.getPenny()/5);
		player.addPenny(param.getPenny()%5);
	}
}
