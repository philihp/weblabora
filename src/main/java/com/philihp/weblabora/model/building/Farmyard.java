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
import com.philihp.weblabora.model.TerrainTypeEnum;
import com.philihp.weblabora.model.Token;
import com.philihp.weblabora.model.UsageParam;
import com.philihp.weblabora.model.WeblaboraException;
import com.philihp.weblabora.model.Wheel;

public class Farmyard extends Building {

	public Farmyard() {
		super("LX2", "L", 0, "Farmyard", BuildCost.is(), 2, 0, EnumSet.of(PLAINS), true);
	}

	@Override
	public void use(Board board, UsageParam input) throws WeblaboraException {
		Player player = board.getPlayer(board.getActivePlayer());
		Wheel wheel = board.getWheel();
		
		if(input.getSheep() != 0) {
			Token token = input.isWithJoker()?wheel.getJoker():wheel.getSheep();
			player.addSheep(token.take());
		}
		else if(input.getGrain() != 0) {
			Token token = input.isWithJoker()?wheel.getJoker():wheel.getGrain();
			player.addGrain(token.take());
		}
		else {
			throw new WeblaboraException("Usage of Farmyard must specify if Sheep or Grain is desired.");
		}
		
	}
}
