package com.philihp.weblabora.model.building;

import static com.philihp.weblabora.model.TerrainTypeEnum.COAST;
import static com.philihp.weblabora.model.TerrainTypeEnum.HILLSIDE;
import static com.philihp.weblabora.model.TerrainTypeEnum.PLAINS;

import java.util.EnumSet;

import com.philihp.weblabora.model.Board;
import com.philihp.weblabora.model.BuildCost;
import com.philihp.weblabora.model.Player;
import com.philihp.weblabora.model.SettlementRound;
import com.philihp.weblabora.model.UsageParam;
import com.philihp.weblabora.model.UsageParamDouble;
import com.philihp.weblabora.model.UsageParamSingle;
import com.philihp.weblabora.model.WeblaboraException;

public class PilgrimageSite extends BuildingDoubleUsage {

	public PilgrimageSite() {
		super("F36", SettlementRound.D, 3, "Pilgrimage Site", BuildCost.is().coin(6), 6, 2,
				EnumSet.of(PLAINS, HILLSIDE, COAST), false);
	}

	@Override
	public void use(Board board, UsageParamDouble first) throws WeblaboraException {
		Player player = board.getPlayer(board.getActivePlayer());
		UsageParamSingle second = first.getSecondary();

		if(first.getBook() + first.getPottery() + first.getOrnament() > 1)
			throw new WeblaboraException("First parameter may only have one book, pottery, or ornament");
		
		if(second.getBook() + second.getPottery() + second.getOrnament() > 1)
			throw new WeblaboraException("Second parameter may only have one book, pottery, or ornament");
		
		doIt(player, first);
		doIt(player, second);
	}
	
	private void doIt(Player player, UsageParam input) throws WeblaboraException {
		if(input.getBook() == 1) {
			if(player.getBook() <= 0) throw new WeblaboraException(getName()+" couldn't find a book to convert");
			player.subtractBook(input.getBook());
			player.addPottery(input.getBook());
		}
		else if(input.getPottery() == 1) {
			if(player.getPottery() <= 0) throw new WeblaboraException(getName()+" couldn't find a ceramic to convert");
			player.subtractPottery(input.getPottery());
			player.addOrnament(input.getPottery());
		}
		else if(input.getOrnament() == 1) {
			if(player.getOrnament() <= 0) throw new WeblaboraException(getName()+" couldn't find an ornament to convert");
			player.subtractOrnament(input.getOrnament());
			player.addReliquary(input.getOrnament());
		}
	}
}
