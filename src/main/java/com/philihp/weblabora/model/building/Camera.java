package com.philihp.weblabora.model.building;

import static com.philihp.weblabora.model.TerrainTypeEnum.COAST;
import static com.philihp.weblabora.model.TerrainTypeEnum.MOUNTAIN;
import static com.philihp.weblabora.model.TerrainTypeEnum.HILLSIDE;
import static com.philihp.weblabora.model.TerrainTypeEnum.PLAINS;

import java.util.EnumSet;
import java.util.Set;

import com.philihp.weblabora.model.Board;
import com.philihp.weblabora.model.BuildCost;
import com.philihp.weblabora.model.Coordinate;
import com.philihp.weblabora.model.Landscape;
import com.philihp.weblabora.model.Player;
import com.philihp.weblabora.model.SettlementRound;
import com.philihp.weblabora.model.TerrainTypeEnum;
import com.philihp.weblabora.model.UsageParam;
import com.philihp.weblabora.model.UsageParamDouble;
import com.philihp.weblabora.model.UsageParamSingle;
import com.philihp.weblabora.model.WeblaboraException;
import com.philihp.weblabora.model.Wheel;

public class Camera extends BuildingSingleUsage {

	public Camera() {
		super("I36", SettlementRound.D, 3, "Camera", BuildCost.is().clay(2), 3, 5,
				EnumSet.of(PLAINS, HILLSIDE, COAST), true);
	}

	@Override
	public void use(Board board, UsageParamSingle input) throws WeblaboraException {
		Player player = board.getPlayer(board.getActivePlayer());
		
		int book = input.getBook();
		int pottery = input.getPottery();

		if(book == 0 || pottery == 0 || book != pottery || book > 2 || pottery > 2)
			throw new WeblaboraException(getName()+" takes either 1 pair of book and pottery, or 2 pairs of book and pottery. It was given "+input.getBook()+" books and "+input.getPottery()+" pottery.");

		int iterations = book;

		player.subtractBook(iterations);
		player.subtractPottery(iterations);
		player.addCoins(iterations);
		player.addClay(iterations);
		player.addReliquary(iterations);
	}
}
