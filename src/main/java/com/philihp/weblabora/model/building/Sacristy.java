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

public class Sacristy extends AbstractBuilding {

	public Sacristy() {
		super("G34", "D", 0, "Sacristy", BuildCost.is().stone(3).straw(2), 7, 10, EnumSet.of(COAST, PLAINS, HILLSIDE), true);
	}

	@Override
	public void use(Board board, UsageParam input) {
		Player player = board.getPlayer(board.getActivePlayer());
		player.setBook(player.getBook()-1);
		player.setPottery(player.getPottery()-1);
		player.setOrnament(player.getOrnament()-1);
		player.setReliquary(player.getReliquary()-1);
		player.claimWonder(board.claimWonder());
	}
}
