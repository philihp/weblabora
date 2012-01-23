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

public class BuildersMarket extends AbstractBuilding {

	public BuildersMarket() {
		super("G13", "", 4, "Builders' Market", BuildCost.is().clay(2), 1, 6, EnumSet.of(COAST, PLAINS, HILLSIDE));
	}

	@Override
	public void use(Board board, BuildCost input) {
		Player activePlayer = board.getPlayer(board.getActivePlayer());
		
		activePlayer.setPenny(activePlayer.getPenny()-2);
		
		activePlayer.setWood(activePlayer.getWood()+2);
		activePlayer.setClay(activePlayer.getClay()+2);
		activePlayer.setStone(activePlayer.getStone()+1);
		activePlayer.setStraw(activePlayer.getStraw()+1);
	}
}
