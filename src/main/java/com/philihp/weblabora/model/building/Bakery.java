package com.philihp.weblabora.model.building;

import static com.philihp.weblabora.model.TerrainTypeEnum.COAST;
import static com.philihp.weblabora.model.TerrainTypeEnum.HILLSIDE;
import static com.philihp.weblabora.model.TerrainTypeEnum.PLAINS;

import java.util.EnumSet;
import java.util.Set;

import com.philihp.weblabora.model.Board;
import com.philihp.weblabora.model.BuildCost;
import com.philihp.weblabora.model.Player;
import com.philihp.weblabora.model.TerrainTypeEnum;
import com.philihp.weblabora.model.UsageParam;
import com.philihp.weblabora.model.UsageParamDouble;
import com.philihp.weblabora.model.WeblaboraException;

public class Bakery extends BuildingDoubleChit {

	public Bakery() {
		super("F05", "", 2, "Bakery", BuildCost.is().clay(2).straw(1), 5, 4, EnumSet.of(COAST, PLAINS, HILLSIDE), false);
	}

	@Override
	public void use(Board board, UsageParamDouble input) throws WeblaboraException {
		Player activePlayer = board.getPlayer(board.getActivePlayer());
		
		if(input.getFlour() > input.getEnergy()/0.5)
			throw new WeblaboraException("Bakery was given "+input.getFlour()+" flour, but it only had "+input.getEnergy()+" energy");
		
		int quantity = input.getFlour();
		
		activePlayer.subtractFlour(quantity);
		activePlayer.subtractEnergy(input);
		
		activePlayer.addBread(quantity);
		
		//convert up to two bread to money
		activePlayer.addCoins(Math.min(input.getSecondary().getBread(), 2) * 4);
		activePlayer.subtractBread(Math.min(input.getSecondary().getBread(), 2));
	}
}
