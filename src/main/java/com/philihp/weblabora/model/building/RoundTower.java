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
import com.philihp.weblabora.model.UsageParamSingle;
import com.philihp.weblabora.model.WeblaboraException;

public class RoundTower extends BuildingSingleUsage {

	public RoundTower() {
		super("I35", SettlementRound.D, 1, "Round Tower", BuildCost.is().stone(4), 9, 6, EnumSet.of(HILLSIDE), false);
	}

	@Override
	public void use(Board board, UsageParamSingle input) throws WeblaboraException {
		Player player = board.getPlayer(board.getActivePlayer());
		int whiskey = input.getWhiskey();
		int nickel = input.getNickel();
		int penny = input.getPenny();
		int books = input.getBook();
		int pottery = input.getPottery();
		int ornament = input.getOrnament();
		int relic = input.getReliquary();

		if(penny == 5) {
			player.subtractPenny(5);
			penny-=5;
		}
		else if(nickel >= 1) {
			player.subtractNickel(1);
			nickel--;
		}
		else throw new WeblaboraException(getName()+" requires 5 coins, in either penny or nickel form.");

		if(whiskey < 1)
			throw new WeblaboraException(getName()+" requires at least 1 whiskey.");
		else {
			player.subtractWhiskey(1);
			whiskey--;
		}

		int rawPoints = whiskey * nickel*2 + books*2 + pottery*3 + ornament*4 + relic*8;

		if(rawPoints < 14)
			throw new WeblaboraException(getName()+" requires at least 14 points in items (after a whiskey and a nickel are taken out), but only "+rawPoints+" points were given");

		player.subtractWhiskey(whiskey);
		player.subtractNickel(nickel);
		player.subtractBook(books);
		player.subtractPottery(pottery);
		player.subtractOrnament(ornament);
		player.subtractReliquary(relic);
		player.claimWonder(board.claimWonder());
	}
}
