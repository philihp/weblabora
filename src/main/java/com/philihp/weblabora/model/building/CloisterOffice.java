package com.philihp.weblabora.model.building;

import static com.philihp.weblabora.model.TerrainTypeEnum.PLAINS;

import java.util.EnumSet;

import com.philihp.weblabora.model.Board;
import com.philihp.weblabora.model.BuildCost;
import com.philihp.weblabora.model.Color;
import com.philihp.weblabora.model.Player;
import com.philihp.weblabora.model.SettlementRound;
import com.philihp.weblabora.model.Token;
import com.philihp.weblabora.model.UsageParam;
import com.philihp.weblabora.model.Wheel;

public class CloisterOffice extends Building {

	public CloisterOffice(String id, SettlementRound stage, int players) {
		super(id, stage, players, "Cloister Office", BuildCost.is(), 2, 0, EnumSet.of(PLAINS), true);
	}

	@Override
	public void use(Board board, UsageParam input) {
		Player activePlayer = board.getPlayer(board.getActivePlayer());
		Wheel wheel = board.getWheel();
		Token token = input.isWithJoker()?wheel.getJoker():wheel.getCoin();
		activePlayer.addPenny(token.take());
		board.distributeBonusProduction(UsageParam.is().penny(1));
	}
	
	@Override
	public String getImage() {
		return "L03";
	}
	
	public static CloisterOffice make(Color color) {
		switch (color) {
		case RED:
			return new CloisterOfficeR();
		case GREEN:
			return new CloisterOfficeG();
		case BLUE:
			return new CloisterOfficeB();
		case WHITE:
			return new CloisterOfficeW();
		default:
			throw new RuntimeException("Unknown color " + color);
		}
	}
}
