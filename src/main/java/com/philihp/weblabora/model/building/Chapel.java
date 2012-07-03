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
import com.philihp.weblabora.model.WeblaboraException;

public class Chapel extends Building {

	public Chapel() {
		super("I24", SettlementRound.B, 1, "Chapel", BuildCost.is().clay(3).stone(3), 9, 10, EnumSet.of(COAST, PLAINS, HILLSIDE), true);
	}

	@Override
	public void use(Board board, UsageParam input) throws WeblaboraException {
		Player player = board.getPlayer(board.getActivePlayer());

		if(input.getPenny() == 1) {
			player.addBooks(1);
			player.subtractPenny(1);
		}

		int beer = input.getBeer();
		int whiskey = input.getWhiskey();
		
		if(beer != whiskey || beer > 3 || whiskey > 3) 
			throw new WeblaboraException(getName()+" converts 1 beer and 1 whiskey to a relic up to 3 times. Beer and whiskey must be provided in equal amounts, and no more than 3 of each.");
		
		int iterations = Math.min(beer, whiskey);
		
		player.subtractBeer(iterations);
		player.subtractWhiskey(iterations);
		player.addReliquary(iterations);
	}
}
