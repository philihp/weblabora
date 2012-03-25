package com.philihp.weblabora.model.building;

import static com.philihp.weblabora.model.TerrainTypeEnum.COAST;
import static com.philihp.weblabora.model.TerrainTypeEnum.MOUNTAIN;
import static com.philihp.weblabora.model.TerrainTypeEnum.HILLSIDE;
import static com.philihp.weblabora.model.TerrainTypeEnum.PLAINS;

import java.util.EnumSet;
import java.util.Set;

import com.philihp.weblabora.model.Board;
import com.philihp.weblabora.model.BuildCost;
import com.philihp.weblabora.model.Coordinate;
import com.philihp.weblabora.model.Landscape;
import com.philihp.weblabora.model.Player;
import com.philihp.weblabora.model.TerrainTypeEnum;
import com.philihp.weblabora.model.UsageParam;
import com.philihp.weblabora.model.WeblaboraException;
import com.philihp.weblabora.model.Wheel;

abstract class PilgrimageSite extends Building {

	public PilgrimageSite(String id, String stage, int players) {
		super("F36", "D", 3, "Pilgrimage Site", BuildCost.is().coin(6), 6, 2,
				EnumSet.of(PLAINS, HILLSIDE, COAST), false);
	}

	@Override
	public void use(Board board, UsageParam input) throws WeblaboraException {
		Player player = board.getPlayer(board.getActivePlayer());
		
		if(input.getBook() + input.getPottery() + input.getOrnament() > 2)
			throw new WeblaboraException("Pilgrimmage Site can only convert twice.");
		
		player.subtractBook(input.getBook());
		player.addPottery(input.getBook());
		
		player.subtractPottery(input.getPottery());
		player.addOrnament(input.getPottery());
		
		player.subtractOrnament(input.getOrnament());
		player.addReliquary(input.getOrnament());
	}
}
