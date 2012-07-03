package com.philihp.weblabora.model.building;

import static com.philihp.weblabora.model.TerrainTypeEnum.COAST;
import static com.philihp.weblabora.model.TerrainTypeEnum.HILLSIDE;
import static com.philihp.weblabora.model.TerrainTypeEnum.PLAINS;

import java.util.EnumSet;
import java.util.Set;

import com.philihp.weblabora.model.Board;
import com.philihp.weblabora.model.BuildCost;
import com.philihp.weblabora.model.Player;
import com.philihp.weblabora.model.SettlementRound;
import com.philihp.weblabora.model.TerrainTypeEnum;
import com.philihp.weblabora.model.UsageParam;
import com.philihp.weblabora.model.UsageParamDouble;
import com.philihp.weblabora.model.WeblaboraException;

public class Brewery extends BuildingDoubleUsage {

	public Brewery() {
		super("I05", SettlementRound.S, 1, "Brewery", BuildCost.is().stone(2).straw(1), 7, 9, EnumSet.of(COAST, PLAINS, HILLSIDE), false);
	}

	@Override
	public void use(Board board, UsageParamDouble input) throws WeblaboraException {
		Player activePlayer = board.getPlayer(board.getActivePlayer());
		
		if(input.getHops() != input.getGrain())
			throw new WeblaboraException("Brewery must be given equal amounts of hops and grain, but was given "+input.getHops()+" hops and "+input.getGrain()+" grain");
		if(input.getBeer() != 0)
			throw new WeblaboraException("Brewery should be given beer in the second parameter");
		if(input.getSecondary().getBeer() > 1)
			throw new WeblaboraException("Brewery was given "+input.getSecondary().getBeer()+" beer to convert to coins. Just give it one, it can only do this once.");
			
		int iterations = input.getHops();
		activePlayer.subtractHops(iterations);
		activePlayer.subtractGrain(iterations);
		activePlayer.addBeer(iterations);
		
		activePlayer.subtractBeer(input.getSecondary().getBeer());
		activePlayer.addCoins(input.getSecondary().getBeer()*7);
	}
}
