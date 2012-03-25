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

public class CloisterLibrary extends Building {

	public CloisterLibrary() {
		super("F17", "A", 2, "Cloister Library", BuildCost.is().stone(2).straw(1), 7, 7,
				EnumSet.of(COAST, PLAINS, HILLSIDE), true);
	}

	@Override
	public void use(Board board, UsageParam param) throws WeblaboraException {
		Player player = board.getPlayer(board.getActivePlayer());
		
		player.addBooks(param.getPenny());
		player.subtractCoins(param.getPenny());
		
		if(param.getBook() == 1) {
			player.subtractBook(1);
			player.addMeat(1);
			player.addWine(1);
		}
	}
}
