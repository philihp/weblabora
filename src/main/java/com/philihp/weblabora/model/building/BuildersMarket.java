package com.philihp.weblabora.model.building;

import static com.philihp.weblabora.model.TerrainTypeEnum.COAST;
import static com.philihp.weblabora.model.TerrainTypeEnum.HILLSIDE;
import static com.philihp.weblabora.model.TerrainTypeEnum.PLAINS;

import java.util.EnumSet;
import java.util.Set;

import com.philihp.weblabora.model.Board;
import com.philihp.weblabora.model.BuildCost;
import com.philihp.weblabora.model.Player;
import com.philihp.weblabora.model.SettlementRound;
import com.philihp.weblabora.model.TerrainTypeEnum;
import com.philihp.weblabora.model.UsageParam;
import com.philihp.weblabora.model.WeblaboraException;

public class BuildersMarket extends Building {

	public BuildersMarket() {
		super("G13", SettlementRound.S, 4, "Builders' Market", BuildCost.is().clay(2), 1, 6, EnumSet.of(COAST, PLAINS, HILLSIDE), false);
	}

	@Override
	public void use(Board board, UsageParam input) throws WeblaboraException {
		Player activePlayer = board.getPlayer(board.getActivePlayer());
		
		if(input.getCoins() == 2) {
			activePlayer.subtractMoney(input);
			activePlayer.addWood(2);
			activePlayer.addClay(2);
			activePlayer.addStone(1);
			activePlayer.addStraw(1);
		}
		else {
			throw new WeblaboraException("Builders' Market takes two coins, but it was given "+input.getCoins());
		}
		
	}
}
