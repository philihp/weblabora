package com.philihp.weblabora.model.building;

import static com.philihp.weblabora.model.Terrain.COAST;

import java.util.EnumSet;

import com.philihp.weblabora.model.Board;
import com.philihp.weblabora.model.BuildCost;
import com.philihp.weblabora.model.Player;
import com.philihp.weblabora.model.UsageParam;

public class Shipyard extends AbstractBuilding {

	public Shipyard() {
		super("G26", "B", 0, "Shipyard", BuildCost.is().clay(4).stone(1), -2, 15, EnumSet.of(COAST), false);
	}

	@Override
	public void use(Board board, UsageParam input) {
		Player player = board.getPlayer(board.getActivePlayer());
		player.setWood(player.getWood()-2);
		player.setOrnament(player.getOrnament()+1);
		player.setNickel(player.getNickel()+1);
	}
}
