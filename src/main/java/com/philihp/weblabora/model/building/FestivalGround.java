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

public class FestivalGround extends BuildingSingleUsage {

	public FestivalGround() {
		super("I38", SettlementRound.D, 1, "Festival Ground", BuildCost.is().coin(10),
				7, 3, EnumSet.of(PLAINS, HILLSIDE, COAST), false);
	}

	@Override
	public void use(Board board, UsageParamSingle output) throws WeblaboraException  {
		Player player = board.getPlayer(board.getActivePlayer());
		
		if(player.getBeer() < 1)
			throw new WeblaboraException(getName()+" requires beer to function. Can you blame it?");
		
		player.subtractBeer(1);		

		int possiblePoints = player.getLandscape().getNumberOfForests();
		
		int requestedPoints = output.getBook()*2 + output.getPottery()*3 + output.getOrnament()*4 + output.getReliquary()*8;
		
		if(possiblePoints < requestedPoints)
			throw new WeblaboraException(getName()+" was asked to make "+requestedPoints+" points, but only "+possiblePoints+" is allowed.");
		
		player.addBooks(output.getBook());
		player.addPottery(output.getPottery());
		player.addOrnament(output.getOrnament());
		player.addReliquary(output.getReliquary());
		
	}
}
