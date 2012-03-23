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
import com.philihp.weblabora.model.Wheel;

public class FinancedEstate extends AbstractBuilding {

	public FinancedEstate() {
		super("F15", "A", 4, "Financed Estate", BuildCost.is().clay(1).stone(1), 6, 4,
				EnumSet.of(HILLSIDE, COAST, PLAINS), false);
	}

	@Override
	public void use(Board board, UsageParam input) {
		Player player = board.getPlayer(board.getActivePlayer());
		player.subtractPenny(1);
		player.addBook(1);
		player.addBread(1);
		player.addGrapes(2);
		player.addFlour(2);
	}
}
