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
import com.philihp.weblabora.model.WeblaboraException;

public class FuelMerchant extends Building {

	public FuelMerchant() {
		super("G06", "", 3, "Fuel Merchant", BuildCost.is().clay(1).straw(1), 2, 5,
				EnumSet.of(COAST, PLAINS, HILLSIDE), false);
	}

	@Override
	public void use(Board board, UsageParam param) throws WeblaboraException {
		Player player = board.getPlayer(board.getActivePlayer());
		double energy = param.getEnergy();
		if(energy >= 9) {
			player.addNickel(2);
		}
		else if(energy >= 6) {
			player.addNickel(1);
			player.addPenny(3);
		}
		else if(energy >= 3) {
			player.addNickel(1);
		}
		else return;
		
		player.subtractEnergy(param);
	}
}
