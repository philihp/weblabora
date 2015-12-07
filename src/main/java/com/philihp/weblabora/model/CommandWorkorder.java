package com.philihp.weblabora.model;


import com.philihp.weblabora.model.building.Building;
import com.philihp.weblabora.model.building.BuildingEnum;

public class CommandWorkorder implements MoveCommand, InvalidDuringSettlement {


	@Override
	public void execute(Board board, CommandParameters params)
			throws WeblaboraException {

		BuildingEnum buildingId = BuildingEnum.valueOf(params.get(0));

		execute(board,
				buildingId,
				new UsageParam(params.get(1))
				);

		//System.out.println("Workorder "+params.get(0)+" with payment "+params.get(1));
	}

	public static void execute(Board board, BuildingEnum buildingId, UsageParam payment)
			throws WeblaboraException {
		Player activePlayer = board.getPlayer(board.getActivePlayer());
		Building building = board.findBuildingInstance(buildingId);
		Player orderedPlayer = building.findLocation().getLandscape().getPlayer();

		if(board.getMode().isNeutralPlayerUsed() && orderedPlayer.getColor().equals(Color.WHITE)) {
			activePlayer = board.getPlayer(0);
			orderedPlayer = board.getMode().getNeutralPlayer();
		}

		if(payment.isWithGift()) {
			activePlayer.subtractWhiskey(payment.getWhiskey());
			activePlayer.subtractWine(payment.getWine());
		}
		else {
			if(board.getStartingMarker().getCost() <= payment.getCoins()) {
				activePlayer.subtractCoins(board.getStartingMarker().getCost());
				orderedPlayer.addPenny(board.getStartingMarker().getCost());
			}
			else throw new WeblaboraException("Insufficient payment, work contracts cost "+board.getStartingMarker().getCost()+" coins, or a wine or whiskey.");
		}

		board.setWaitingForPlayer(orderedPlayer);
	}
}
