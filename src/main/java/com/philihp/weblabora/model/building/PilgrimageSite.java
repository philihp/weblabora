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
import com.philihp.weblabora.model.TerrainTypeEnum;
import com.philihp.weblabora.model.UsageParam;
import com.philihp.weblabora.model.UsageParamDouble;
import com.philihp.weblabora.model.UsageParamSingle;
import com.philihp.weblabora.model.WeblaboraException;
import com.philihp.weblabora.model.Wheel;

public class PilgrimageSite extends BuildingDoubleUsage {

	public PilgrimageSite() {
		super("F36", "D", 3, "Pilgrimage Site", BuildCost.is().coin(6), 6, 2,
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
	
	private void doIt(Player player, UsageParam input) {
		if(input.getBook() == 1) {
			player.subtractBook(input.getBook());
			player.addPottery(input.getBook());
		}
		else if(input.getPottery() == 1) {
			player.subtractPottery(input.getPottery());
			player.addOrnament(input.getPottery());
		}
		else if(input.getOrnament() == 1) {
			player.subtractOrnament(input.getOrnament());
			player.addReliquary(input.getOrnament());
		}
	}
}
