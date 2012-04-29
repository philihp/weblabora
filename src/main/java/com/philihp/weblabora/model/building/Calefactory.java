package com.philihp.weblabora.model.building;

import static com.philihp.weblabora.model.TerrainTypeEnum.COAST;
import static com.philihp.weblabora.model.TerrainTypeEnum.MOUNTAIN;
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

public class Calefactory extends Building {

	public Calefactory() {
		super("F32", "C", 3, "Calefactory", BuildCost.is().stone(1), 5, 2,
				EnumSet.of(COAST, PLAINS, HILLSIDE), true);
	}

	@Override
	public void use(Board board, UsageParam input) throws WeblaboraException {
		Player player = board.getPlayer(board.getActivePlayer());
		if(input.getCoins() == 1)
			player.subtractAll(input);
		else
			throw new WeblaboraException("Calefactory requires 1 coin for input");
	}
}
