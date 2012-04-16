package com.philihp.weblabora.model.building;

import static com.philihp.weblabora.model.TerrainTypeEnum.COAST;
import static com.philihp.weblabora.model.TerrainTypeEnum.HILLSIDE;
import static com.philihp.weblabora.model.TerrainTypeEnum.PLAINS;

import java.util.EnumSet;
import java.util.Set;

import com.philihp.weblabora.model.Board;
import com.philihp.weblabora.model.BuildCost;
import com.philihp.weblabora.model.Player;
import com.philihp.weblabora.model.TerrainTypeEnum;
import com.philihp.weblabora.model.UsageParam;
import com.philihp.weblabora.model.WeblaboraException;

public class CloisterCourtyard extends Building {

	public CloisterCourtyard() {
		super("G02", "", 2, "Cloister Courtyard", BuildCost.is().wood(2), 4, 4, EnumSet.of(COAST, PLAINS, HILLSIDE), true);
	}

	@Override
	public void use(Board board, UsageParam param) throws WeblaboraException {
		Player player = board.getPlayer(board.getActivePlayer());
		
		if((param.differentSingularGoods() == 3 || param.differentSingularGoods() == 2 && param.hasSomethingWith(7)) == false) {
			throw new WeblaboraException("Cloister Courtyard requires three single, different goods. Not "+param.differentSingularGoods());
		}
		
		if(param.getPeat() == 1) player.subtractPeat(1);
		if(param.getClay() == 1) player.subtractClay(1);
		if(param.getWood() == 1) player.subtractWood(1);
		if(param.getGrain() == 1) player.subtractGrain(1);
		if(param.getSheep() == 1) player.subtractSheep(1);
		if(param.getStone() == 1) player.subtractStone(1);
		if(param.getFlour() == 1) player.subtractFlour(1);
		if(param.getGrapes() == 1) player.subtractGrapes(1);
		if(param.getPenny() == 1) player.subtractCoins(1);
		if(param.getNickel() == 1) player.subtractNickel(1);
		if(param.getHops() == 1) player.subtractHops(1);
		if(param.getCoal() == 1) player.subtractCoal(1);
		if(param.getBook() == 1) player.subtractBook(1);
		if(param.getPottery() == 1) player.subtractPottery(1);
		if(param.getWhiskey() == 1) player.subtractWhiskey(1);
		if(param.getStraw() == 1) player.subtractStraw(1);
		if(param.getMeat() == 1) player.subtractMeat(1);
		if(param.getOrnament() == 1) player.subtractOrnament(1);
		if(param.getBread() == 1) player.subtractBread(1);
		if(param.getWine() == 1) player.subtractWine(1);
		if(param.getBeer() == 1) player.subtractBeer(1);
		if(param.getReliquary() == 1) player.subtractReliquary(1);

		if(param.getPeat() == 6) player.addPeat(6);
		else if(param.getClay() == 6) player.addClay(6);
		else if(param.getWood() == 6) player.addWood(6);
		else if(param.getGrain() == 6) player.addGrain(6);
		else if(param.getSheep() == 6) player.addSheep(6);
		else if(param.getStone() == 6) player.addStone(6);
		else if(param.getFlour() == 6) player.addFlour(6);
		else if(param.getGrapes() == 6) player.addGrapes(6);
		else if(param.getPenny() == 6) player.addPenny(6);
		else if(param.getNickel() == 6) player.addNickel(6);
		else if(param.getHops() == 6) player.addHops(6);
		else if(param.getCoal() == 6) player.addCoal(6);
		else if(param.getBook() == 6) player.addBooks(6);
		else if(param.getPottery() == 6) player.addPottery(6);
		else if(param.getWhiskey() == 6) player.addWhiskey(6);
		else if(param.getStraw() == 6) player.addStraw(6);
		else if(param.getMeat() == 6) player.addMeat(6);
		else if(param.getOrnament() == 6) player.addOrnament(6);
		else if(param.getBread() == 6) player.addBread(6);
		else if(param.getWine() == 6) player.addWine(6);
		else if(param.getBeer() == 6) player.addBeer(6);
		else if(param.getReliquary() == 6) player.addReliquary(6);

		else if(param.getPeat() == 7) player.addPeat(5);
		else if(param.getClay() == 7) player.addClay(5);
		else if(param.getWood() == 7) player.addWood(5);
		else if(param.getGrain() == 7) player.addGrain(5);
		else if(param.getSheep() == 7) player.addSheep(5);
		else if(param.getStone() == 7) player.addStone(5);
		else if(param.getFlour() == 7) player.addFlour(5);
		else if(param.getGrapes() == 7) player.addGrapes(5);
		else if(param.getPenny() == 7) player.addPenny(5);
		else if(param.getNickel() == 7) player.addNickel(5);
		else if(param.getHops() == 7) player.addHops(5);
		else if(param.getCoal() == 7) player.addCoal(5);
		else if(param.getBook() == 7) player.addBooks(5);
		else if(param.getPottery() == 7) player.addPottery(5);
		else if(param.getWhiskey() == 7) player.addWhiskey(5);
		else if(param.getStraw() == 7) player.addStraw(5);
		else if(param.getMeat() == 7) player.addMeat(5);
		else if(param.getOrnament() == 7) player.addOrnament(5);
		else if(param.getBread() == 7) player.addBread(5);
		else if(param.getWine() == 7) player.addWine(5);
		else if(param.getBeer() == 7) player.addBeer(5);
		else if(param.getReliquary() == 7) player.addReliquary(5);
	}
}
