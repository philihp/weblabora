package com.philihp.weblabora.model;

import static com.philihp.weblabora.model.TerrainTypeEnum.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import com.philihp.weblabora.model.building.*;

public class CommandSettle implements MoveCommand {
	
	private Integer grokParam(String num, String name, String param) throws WeblaboraException {
		try {
			return new Integer(param);
		}
		catch(NumberFormatException e) {
			throw new WeblaboraException(num+" parameter, "+name+"=\""+param+"\" needs to be a number");
		}
	}

	@Override
	public void execute(Board board, CommandParameters params)
			throws WeblaboraException {
		
		execute(board,
				params.get(0),
				grokParam("Second", "x", params.get(1)),
				grokParam("Third", "y", params.get(2)),
				new UsageParam(params.get(3))
				);
		
		
		System.out.println("Settling "+params.get(0)+" at ("+params.get(1)+","+params.get(2)+")");
	}
	
	public static void execute(Board board, String settlementId, int x, int y, UsageParam payment)
			throws WeblaboraException {
		Player player = board.getPlayer(board.getActivePlayer());
		Terrain location = player.getLandscape().getTerrain().get(y, x);
		
		Settlement settlement = findSettlement(player.getUnbuiltSettlements(), settlementId);

		if(location.getErection() != null) {
			throw new WeblaboraException("There is already an erection at ("+x+","+y+"): "+location.getErection());
		}
		
		if(settlement.getTerrains().contains(location.getTerrainType()) == false) {
			throw new WeblaboraException("The location at ("+x+","+y+") has a terrain of "+location.getTerrainType()+", which is not appropriate for "+settlement.getName());
		}
		
		if(payment.getEnergy() < settlement.getEnergyCost()) {
			throw new WeblaboraException(settlement.getName()+" costs "+settlement.getEnergyCost()+" energy but player only supplied "+payment.getEnergy());
		}
		
		if(payment.getFood() < settlement.getFoodCost()) {
			throw new WeblaboraException(settlement.getName()+" costs "+settlement.getFoodCost()+" food but player only supplied "+payment.getFood());
		}
		
		player.subtractAll(payment);
		location.setErection(settlement);
	}

	private static Settlement findSettlement(
			List<Settlement> unbuiltSettlements, String settlementId) throws WeblaboraException {
		for(Settlement settlement : unbuiltSettlements) {
			if(settlement.getId().equals(settlementId)) {
				unbuiltSettlements.remove(settlement);
				return settlement;
			}
		}
		throw new WeblaboraException("Settlement "+settlementId +" was not be found in unsettled settlements");
	}
}
