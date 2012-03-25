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
import com.philihp.weblabora.model.TerrainTypeEnum;
import com.philihp.weblabora.model.UsageParam;
import com.philihp.weblabora.model.Wheel;

public class ShippingCompany extends Building {

	public ShippingCompany() {
		super("F33", "C", 0, "Shipping Company", BuildCost.is().wood(3).clay(3), 4, 8,
				EnumSet.of(COAST), false);
	}

	@Override
	public void use(Board board, UsageParam input) {
		Player player = board.getPlayer(board.getActivePlayer());
		int amount = board.getWheel().getJoker().take();
		if(input.getMeat() != 0) {
			player.addMeat(amount);
		}
		else if(input.getBread() != 0) {
			player.addBread(amount);
		}
		else if(input.getWine() != 0) {
			player.addWine(amount);
		}
	}
}
