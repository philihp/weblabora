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
import com.philihp.weblabora.model.WeblaboraException;

public class Palace extends AbstractBuilding {

	public Palace() {
		super("F27", "C", 0, "Palace", BuildCost.is().coin(25), 8, 25, EnumSet.of(HILLSIDE), false);
	}

	@Override
	public void use(Board board, UsageParam input) throws WeblaboraException {
		Player player = board.getPlayer(board.getActivePlayer());
		player.setWine(player.getWine()-1);
		AbstractBuilding building = (AbstractBuilding)input.getCard();
		building.use(board, input);
	}
}
