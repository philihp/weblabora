package com.philihp.weblabora.model.building;

import static com.philihp.weblabora.model.TerrainTypeEnum.HILLSIDE;

import java.util.EnumSet;

import com.philihp.weblabora.model.Board;
import com.philihp.weblabora.model.BuildCost;
import com.philihp.weblabora.model.Player;
import com.philihp.weblabora.model.SettlementRound;
import com.philihp.weblabora.model.UsageParamDouble;
import com.philihp.weblabora.model.WeblaboraException;

public class DruidsHouse extends BuildingDoubleUsage {

	public DruidsHouse() {
		super("I15", SettlementRound.A, 4, "Druid's House", BuildCost.is().clay(1).stone(1), 6, 6,
				EnumSet.of(HILLSIDE), false);
	}

	@Override
	public void use(Board board, UsageParamDouble param) throws WeblaboraException {
		Player player = board.getPlayer(board.getActivePlayer());
		
		if(player.getBook() <= 0)
			throw new WeblaboraException(getName()+" requires that the current player have at least 1 book.");
		player.subtractBook(1);
		
		if(param.getWood() != 0) {
			player.addWood(5);
			if(param.getSecondary().getWood() != 0)
				throw new WeblaboraException(getName()+" makes 5 of one basic good and 3 of another. It cannot make 8 wood.");
		}
		else if(param.getClay() != 0) {
			player.addClay(5);
			if(param.getSecondary().getClay() != 0)
				throw new WeblaboraException(getName()+" makes 5 of one basic good and 3 of another. It cannot make 8 clay.");
		}
		else if(param.getGrain() != 0) {
			player.addGrain(5);
			if(param.getSecondary().getGrain() != 0)
				throw new WeblaboraException(getName()+" makes 5 of one basic good and 3 of another. It cannot make 8 grain.");
		}
		else if(param.getPenny() != 0) {
			player.addPenny(5);
			if(param.getSecondary().getPenny() != 0)
				throw new WeblaboraException(getName()+" makes 5 of one basic good and 3 of another. It cannot make 8 penny.");
		}
		else if(param.getSheep() != 0) {
			player.addSheep(5);
			if(param.getSecondary().getSheep() != 0)
				throw new WeblaboraException(getName()+" makes 5 of one basic good and 3 of another. It cannot make 8 sheep.");
		}
		else if(param.getPeat() != 0) {
			player.addPeat(5);
			if(param.getSecondary().getPeat() != 0)
				throw new WeblaboraException(getName()+" makes 5 of one basic good and 3 of another. It cannot make 8 peat.");
		}
		else {
			throw new WeblaboraException(getName()+" can only make basic goods, but no basic good was specified in the first parameter. Basic goods are Wood, Clay, Grain, Sheep, Penny, and Peat.");
		}
		
		if(param.getSecondary().getWood() != 0)
			player.addWood(3);
		else if(param.getSecondary().getClay() != 0)
			player.addClay(3);
		else if(param.getSecondary().getGrain() != 0)
			player.addGrain(3);
		else if(param.getSecondary().getPenny() != 0)
			player.addPenny(3);
		else if(param.getSecondary().getSheep() != 0)
			player.addSheep(3);
		else if(param.getSecondary().getPeat() != 0)
			player.addPeat(3);
		else throw new WeblaboraException(getName()+" can only make basic goods, but couldn't find any in the second parameter. Basic goods are Wood, Clay, Grain, Sheep, Penny, and Peat.");
	}
}
