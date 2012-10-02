package com.philihp.weblabora.model.building;

import static com.philihp.weblabora.model.TerrainTypeEnum.COAST;
import static com.philihp.weblabora.model.TerrainTypeEnum.MOUNTAIN;
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
import com.philihp.weblabora.model.UsageParamSingle;
import com.philihp.weblabora.model.WeblaboraException;
import com.philihp.weblabora.model.Wheel;

public class ShippingCompany extends BuildingDoubleUsage {

	public ShippingCompany() {
		super("F33", SettlementRound.C, 1, "Shipping Company", BuildCost.is().wood(3).clay(3), 4, 8,
				EnumSet.of(COAST), false);
	}

	@Override
	public void use(Board board, UsageParamDouble input) throws WeblaboraException {
		Player player = board.getPlayer(board.getActivePlayer());
		
		if(input.getEnergy() < 3) {
			throw new WeblaboraException("Not enough energy. "+getName()+" needs 3 energy, but was only given "+input.getEnergy());
		}
		player.subtractEnergy(input);
		
		UsageParamSingle output = input.getSecondary();
		
		int amount = board.getWheel().getJoker().take();
		if(output.getMeat() == 1) {
			player.addMeat(amount);
		}
		else if(output.getBread() == 1) {
			player.addBread(amount);
		}
		else if(output.getWine() == 1) {
			player.addWine(amount);
		}
		else throw new WeblaboraException(getName()+" needs to know if it should give meat, bread, or wine. Its second parameter needs to be 1 meat, 1 bread, or 1 wine.");
	}
}
