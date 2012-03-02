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
import com.philihp.weblabora.model.UsageParam;

public class Slaughterhouse extends AbstractBuilding {

	public Slaughterhouse() {
		super("G19", "A", 0, "Slaughterhouse", BuildCost.is().wood(2).clay(2), -3, 8, EnumSet.of(COAST, PLAINS, HILLSIDE),
				false);
	}

	@Override
	public void use(Board board, UsageParam input) {
		Player player = board.getPlayer(board.getActivePlayer());
		player.setSheep(player.getSheep() - input.getSheep());
		player.setStraw(player.getStraw() - input.getStraw());
		player.setGrain(player.getGrain() - input.getGrain());
		player.setMeat(player.getMeat() + input.getSheep());
	}
}
