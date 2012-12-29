package com.philihp.weblabora.model.building;

import static com.philihp.weblabora.model.TerrainTypeEnum.COAST;
import static com.philihp.weblabora.model.TerrainTypeEnum.HILLSIDE;
import static com.philihp.weblabora.model.TerrainTypeEnum.PLAINS;

import java.util.EnumSet;

import com.philihp.weblabora.model.Board;
import com.philihp.weblabora.model.BuildCost;
import com.philihp.weblabora.model.Player;
import com.philihp.weblabora.model.SettlementRound;
import com.philihp.weblabora.model.UsageParamDouble;
import com.philihp.weblabora.model.UsageParamSingle;
import com.philihp.weblabora.model.WeblaboraException;

public class CloisterCourtyard extends BuildingDoubleUsage {

	public CloisterCourtyard() {
		super("G02", SettlementRound.S, 1, "Cloister Courtyard", BuildCost.is().wood(2), 4, 4, EnumSet.of(COAST, PLAINS, HILLSIDE), true);
	}

	@Override
	public void use(Board board, UsageParamDouble input) throws WeblaboraException {
		Player player = board.getPlayer(board.getActivePlayer());
		UsageParamSingle output = input.getSecondary();
		
		if((input.differentSingularGoods() == 3) == false) {
			throw new WeblaboraException(getName()+" requires three single, different goods, not "+input.differentSingularGoods());
		}
		if(output.differentSingularGoods() != 1) {
			throw new WeblaboraException(getName()+" requires exactly one item in its second parameter, not "+output.differentSingularGoods());
		}
		
		player.subtractAll(input);

		if(output.getPeat() >= 1) player.addPeat(6);
		else if(output.getClay() >= 1) player.addClay(6);
		else if(output.getWood() >= 1) player.addWood(6);
		else if(output.getSheep() >= 1) player.addSheep(6);
		else if(output.getGrain() >= 1) player.addGrain(6);
		else if(output.getPenny() >= 1) player.addPenny(6);
		else throw new WeblaboraException(getName()+" can only make basic goods, but had \""+output.getParam()+"\" for its second parameter");
	}
}
