package com.philihp.weblabora.model.building;

import static com.philihp.weblabora.model.TerrainTypeEnum.COAST;
import static com.philihp.weblabora.model.TerrainTypeEnum.MOUNTAIN;
import static com.philihp.weblabora.model.TerrainTypeEnum.HILLSIDE;
import static com.philihp.weblabora.model.TerrainTypeEnum.PLAINS;

import java.util.EnumSet;
import java.util.Set;

import com.philihp.weblabora.model.Board;
import com.philihp.weblabora.model.BuildCost;
import com.philihp.weblabora.model.Color;
import com.philihp.weblabora.model.Player;
import com.philihp.weblabora.model.TerrainTypeEnum;
import com.philihp.weblabora.model.Token;
import com.philihp.weblabora.model.UsageParam;
import com.philihp.weblabora.model.Wheel;

public class ClayMound extends Building {

	protected ClayMound(String id, String stage, int players) {
		super(id, stage, players, "Clay Mound", BuildCost.is(), 3, 0, EnumSet.of(HILLSIDE), false);
	}

	@Override
	public void use(Board board, UsageParam input) {
		Player player = board.getPlayer(board.getActivePlayer());
		Wheel wheel = board.getWheel();
		Token token = input.isWithJoker()?wheel.getJoker():wheel.getClay();
		player.addClay(token.take());
	}

	public static ClayMound make(Color color) {
		switch (color) {
		case RED:
			return new ClayMoundR();
		case GREEN:
			return new ClayMoundG();
		case BLUE:
			return new ClayMoundB();
		case WHITE:
			return new ClayMoundW();
		default:
			throw new RuntimeException("Unknown color " + color);
		}
	}
}
