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

public class CloisterChurch extends AbstractBuilding {

	public CloisterChurch() {
		super("F24", "B", 0, "Cloister Church", BuildCost.is().clay(5).stone(3), 9, 12, EnumSet.of(COAST, PLAINS, HILLSIDE), true);
	}

	@Override
	public void use(Board board, UsageParam input) {
		Player player = board.getPlayer(board.getActivePlayer());
		int bread, wine, exchanged;
		
		bread = input.getBread();
		wine = input.getWine();
		
		//make sure the player has that much bread/wine
		bread = Math.min(bread, player.getBread());
		wine = Math.min(wine, player.getWine());
		
		//in case bread ^= wine at this point, just take the lesser
		exchanged = Math.min(bread, wine);
		
		//max of 2 times
		exchanged = Math.min(exchanged, 2);
		
		player.setWine(player.getWine()-exchanged);
		player.setBread(player.getBread()-exchanged);
		player.setReliquary(player.getReliquary()+exchanged);
	}
}
