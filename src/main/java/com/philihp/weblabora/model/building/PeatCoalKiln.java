package com.philihp.weblabora.model.building;

import static com.philihp.weblabora.model.Terrain.COAST;
import static com.philihp.weblabora.model.Terrain.HILLSIDE;
import static com.philihp.weblabora.model.Terrain.PLAINS;

import java.util.EnumSet;
import java.util.Set;

import com.philihp.weblabora.model.Board;
import com.philihp.weblabora.model.BuildCost;
import com.philihp.weblabora.model.Player;
import com.philihp.weblabora.model.Terrain;

public class PeatCoalKiln extends AbstractBuilding {

	public PeatCoalKiln() {
		super("G07", "", 0, "Peat Coal Kiln", BuildCost.is().clay(1), -2, 4, EnumSet.of(COAST, PLAINS, HILLSIDE));
	}

	@Override
	public void use(Board board, BuildCost input) {
		Player activePlayer = board.getPlayer(board.getActivePlayer());
		activePlayer.setCoal(activePlayer.getCoal() + 1);
		activePlayer.setPenny(activePlayer.getPenny() + 1);

		// turn peat into coal
		activePlayer.setPeat(input.getPeat());
		activePlayer.setCoal(input.getPeat());
	}
}
