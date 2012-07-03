package com.philihp.weblabora.model.building;

import static com.philihp.weblabora.model.TerrainTypeEnum.COAST;
import static com.philihp.weblabora.model.TerrainTypeEnum.MOUNTAIN;
import static com.philihp.weblabora.model.TerrainTypeEnum.HILLSIDE;
import static com.philihp.weblabora.model.TerrainTypeEnum.PLAINS;

import java.util.EnumSet;
import java.util.Set;

import com.philihp.weblabora.model.Board;
import com.philihp.weblabora.model.BuildCost;
import com.philihp.weblabora.model.GameLength;
import com.philihp.weblabora.model.GamePlayers;
import com.philihp.weblabora.model.Player;
import com.philihp.weblabora.model.SettlementRound;
import com.philihp.weblabora.model.TerrainTypeEnum;
import com.philihp.weblabora.model.UsageParam;
import com.philihp.weblabora.model.UsageParamDouble;
import com.philihp.weblabora.model.UsageParamSingle;
import com.philihp.weblabora.model.WeblaboraException;
import com.philihp.weblabora.model.Wheel;

public class HouseOfTheBrotherhood extends BuildingDoubleUsage {

	public HouseOfTheBrotherhood() {
		super("G41", SettlementRound.D, 1, "House of the Brotherhood", BuildCost.is().clay(1)
				.stone(1), 3, 3, EnumSet.of(PLAINS, HILLSIDE, COAST), true);
	}

	@Override
	public void use(Board board, UsageParamDouble input) throws WeblaboraException  {
		Player player = board.getPlayer(board.getActivePlayer());
		UsageParamSingle output = input.getSecondary();
		
		if(input.getCoins() < 5)
			throw new WeblaboraException(getName()+" requires 5 coins.");
		
		player.subtractAll(input);

		double possiblePoints = 0;
		int cloisters = 0;
		for (Building building : player.getLandscape().getBuildings()) {
			if (building.isCloister())
				cloisters++;
		}
		
		if(board.getGamePlayers() == GamePlayers.ONE) {
			possiblePoints = cloisters * 1;
		}
		else if(board.getGamePlayers() == GamePlayers.TWO && board.getGameLength() == GameLength.LONG) {
			possiblePoints = Math.floor(cloisters * 1.5);
		}
		else { 
			possiblePoints = cloisters * 2;
		}
		
		int requestedPoints = output.getBook()*2 + output.getPottery()*3 + output.getOrnament()*4 + output.getReliquary()*8;
		
		if(possiblePoints < requestedPoints)
			throw new WeblaboraException(getName()+" was asked to make "+requestedPoints+" points, but only "+possiblePoints+" is allowed.");
		
		player.addBooks(output.getBook());
		player.addPottery(output.getPottery());
		player.addOrnament(output.getOrnament());
		player.addReliquary(output.getReliquary());
		
	}
}
