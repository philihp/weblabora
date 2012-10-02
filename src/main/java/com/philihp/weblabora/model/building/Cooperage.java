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
import com.philihp.weblabora.model.SettlementRound;
import com.philihp.weblabora.model.TerrainTypeEnum;
import com.philihp.weblabora.model.UsageParam;
import com.philihp.weblabora.model.UsageParamDouble;
import com.philihp.weblabora.model.UsageParamSingle;
import com.philihp.weblabora.model.WeblaboraException;
import com.philihp.weblabora.model.Wheel;

public class Cooperage extends BuildingDoubleUsage {

	public Cooperage() {
		super("I33", SettlementRound.C, 1, "Cooperage", BuildCost.is().straw(1).clay(3), 3, 5,
				EnumSet.of(COAST, PLAINS, HILLSIDE), false);
	}

	@Override
	public void use(Board board, UsageParamDouble input) throws WeblaboraException {
		Player activePlayer = board.getPlayer(board.getActivePlayer());
		
		if(input.getWood() != 3) {
			throw new WeblaboraException("Not enough wood. "+getName()+" needs 3 wood, but was given "+input.getWood()+" instead.");
		}
		activePlayer.subtractWood(input.getWood());
		
		UsageParamSingle output = input.getSecondary();
		
		int amount = board.getWheel().getJoker().take();
		if(output.getBeer() == 1 && output.getWhiskey() == 1)
			throw new WeblaboraException(getName()+" was asked to output both beer and whiskey, but it can only do one, not both.");

		if(output.getBeer() == 1) {
			activePlayer.addBeer(amount);
			if(board.getMode().isProductionBonusActive()) {
				for(Player player : board.getPlayers()) {
					player.addBeer(1);
				}
			}
		}
		else if(output.getWhiskey() == 1) {
			activePlayer.addWhiskey(amount);
			if(board.getMode().isProductionBonusActive()) {
				for(Player player : board.getPlayers()) {
					player.addWhiskey(1);
				}
			}
		}
		else throw new WeblaboraException(getName()+" needs to know if it should give beer or whiskey. Its second parameter needs to be 1 beer or 1 whiskey.");
	}
}
