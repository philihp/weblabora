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

public class Sacristy extends Building {

	public Sacristy() {
		super("G34", "D", 1, "Sacristy", BuildCost.is().stone(3).straw(2), 7, 10, EnumSet.of(COAST, PLAINS, HILLSIDE), true);
	}

	@Override
	public void use(Board board, UsageParam input) {
		Player player = board.getPlayer(board.getActivePlayer());
		player.subtractBook(1);
		player.subtractPottery(1);
		player.subtractOrnament(1);
		player.subtractReliquary(1);
		player.claimWonder(board.claimWonder());
		
		
	}
}
