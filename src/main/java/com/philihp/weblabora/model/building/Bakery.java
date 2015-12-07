package com.philihp.weblabora.model.building;

import static com.philihp.weblabora.model.TerrainTypeEnum.COAST;
import static com.philihp.weblabora.model.TerrainTypeEnum.HILLSIDE;
import static com.philihp.weblabora.model.TerrainTypeEnum.PLAINS;

import java.util.EnumSet;

import com.philihp.weblabora.model.*;

public class Bakery extends BuildingSingleUsage {

	public Bakery() {
		super("F05", SettlementRound.S, 1, "Bakery", BuildCost.is().clay(2).straw(1), 5, 4, EnumSet.of(COAST, PLAINS, HILLSIDE), false);
	}

	@Override
	public void use(Board board, UsageParamSingle input) throws WeblaboraException {
		Player activePlayer = board.getPlayer(board.getActivePlayer());
		
		if(input.getFlour() > input.getEnergy()/0.5)
			throw new WeblaboraException("Bakery was given "+input.getFlour()+" flour, but it only had "+input.getEnergy()+" energy");
		
		int quantity = input.getFlour();
		
		activePlayer.subtractFlour(quantity);
		activePlayer.subtractEnergy(input);
		
		activePlayer.addBread(quantity);
		
		//convert up to two bread to money
		activePlayer.addCoins(Math.min(input.getBread(), 2) * 4);
		activePlayer.subtractBread(Math.min(input.getBread(), 2));
	}
}
