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
import com.philihp.weblabora.model.WeblaboraException;

public class SacredSite extends BuildingDoubleUsage {

	public SacredSite() {
		super("I14", SettlementRound.A, 1, "Sacred Site", BuildCost.is().stone(1), 6, 3, EnumSet.of(COAST, PLAINS, HILLSIDE), false);
	}

	@Override
	public void use(Board board, UsageParamDouble input) throws WeblaboraException {
		Player player = board.getPlayer(board.getActivePlayer());
		
		int grain = input.getGrain();
		int hops = input.getHops();
		if(grain == 0 && hops == 0)
			throw new WeblaboraException(getName()+" needs to know to produce hops or grain by specifying it in the first parameter.");
		if(grain > 0 && hops > 0)
			throw new WeblaboraException(getName()+" was told to produce both hops and grain in the first parameter, but it can only do one.");
		if(input.getBeer() != 0)
			throw new WeblaboraException(getName()+" takes beer in the second parameter, not the first.");
		if(input.getWhiskey() != 0)
			throw new WeblaboraException(getName()+" takes whiskey in the second parameter, not the first.");
		
		if(grain != 0) player.addGrain(2);
		else if(hops != 0) player.addHops(2);
		
		int beer = input.getSecondary().getBeer();
		int whiskey = input.getSecondary().getWhiskey();
		if(beer == 0 && whiskey == 0)
			throw new WeblaboraException(getName()+" needs to know to produce beer or whiskey by specifying it in the second parameter.");
		if(whiskey > 0 && beer > 0)
			throw new WeblaboraException(getName()+" was told to produce both beer and whiskey in the second parameter, but it can only do one.");
		if(input.getSecondary().getGrain() != 0)
			throw new WeblaboraException(getName()+" takes grain in the first parameter, not the second.");
		if(input.getSecondary().getHops() != 0)
			throw new WeblaboraException(getName()+" takes hops in the first parameter, not the second.");
		
		if(beer != 0) player.addBeer(1);
		else if(whiskey != 0) player.addWhiskey(1);
		
		player.addBooks(1);

	}
}
