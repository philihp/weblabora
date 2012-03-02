package com.philihp.weblabora.model.building;

import java.util.EnumSet;
import java.util.Set;

import com.philihp.weblabora.model.Board;
import com.philihp.weblabora.model.BuildCost;
import com.philihp.weblabora.model.Player;
import com.philihp.weblabora.model.Terrain;
import com.philihp.weblabora.model.UsageParam;

import static com.philihp.weblabora.model.Terrain.*;

public class CloisterChapterHouse extends AbstractBuilding {

	public CloisterChapterHouse() {
		super("G16", "A", 3, "Cloister Chapter House", BuildCost.is().clay(3).straw(1), 5, 2, EnumSet.of(COAST, PLAINS,
				HILLSIDE), true);
	}

	@Override
	public void use(Board board, UsageParam input) {
		Player activePlayer = board.getPlayer(board.getActivePlayer());
		activePlayer.setClay(activePlayer.getClay() + 1);
		activePlayer.setWood(activePlayer.getWood() + 1);
		activePlayer.setPeat(activePlayer.getPeat() + 1);
		activePlayer.setSheep(activePlayer.getSheep() + 1);
		activePlayer.setGrain(activePlayer.getGrain() + 1);
		activePlayer.setPenny(activePlayer.getPenny() + 1);
	}

}
