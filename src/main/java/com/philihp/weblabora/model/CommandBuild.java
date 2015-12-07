package com.philihp.weblabora.model;

import com.philihp.weblabora.model.building.Building;
import com.philihp.weblabora.model.building.Erection;

public class CommandBuild implements MoveCommand, InvalidDuringSettlement {

	@Override
	public void execute(Board board, CommandParameters params)
			throws WeblaboraException {
		
		execute(board,
				params.get(0),
				Integer.parseInt(params.get(1)),
				Integer.parseInt(params.get(2)));
		
		//System.out.println("Building "+params.get(0)+" at ("+params.get(1)+","+params.get(2)+")");
	}
	
	public static void execute(Board board, String buildingId, int x, int y)
			throws WeblaboraException {
		Building newBuilding = null;
		Player player = board.getPlayer(board.getActivePlayer());
		if(board.getMode().isNeutralBuildingPhase()) {
			player = board.getMode().getNeutralPlayer();
		}
		
		Terrain location = player.getLandscape().getTerrain().get(y, x);
		
		//remove the building from the unused buildings
		for(Building possibleBuilding : board.getUnbuiltBuildings()) {
			if(possibleBuilding.getId().equals(buildingId)) {
				newBuilding = possibleBuilding;
				board.getUnbuiltBuildings().remove(newBuilding);
				break;
			}
		}
		
		if(newBuilding == null) {
			throw new WeblaboraException("Building "+buildingId+" was not be found in unbuilt buildings");
		}
		
		if(location == null) {
			throw new WeblaboraException("The landscape location at ("+x+","+y+") does not exist or has not been purchased yet");
		}		
		
		if(board.getMode().isNeutralBuildingPhase()) {
			Erection oldErection = location.getErection();
			
			if(oldErection != null && oldErection instanceof Building) {
				Building oldBuilding = (Building)oldErection;
				if(oldBuilding.isCloister() == true && newBuilding.isCloister() == false) {
					throw new WeblaboraException("Can't overbuild the cloister building \""+oldBuilding.getName()+"\" with the non-cloister building \""+newBuilding.getName()+"\". Only cloister buildings can overbuild other cloister buildings.");
				}
				if(oldBuilding.isCloister() == false && newBuilding.isCloister() == true) {
					throw new WeblaboraException("Can't overbuild the non-cloister building \""+oldBuilding.getName()+"\" with the cloister building \""+newBuilding.getName()+"\". Only non-cloister buildings can be overbuilt with other non-cloister buildings.");
				}
			}

			player.addAll(newBuilding.getBuildCost());
		}
		
		if(board.getMode().isNeutralBuildingPhase() == false && location.getErection() != null) {
			throw new WeblaboraException("There is already an erection at ("+x+","+y+"): "+location.getErection());
		}
		
		if(newBuilding.getTerrains().contains(location.getTerrainType()) == false && board.getMode().isNeutralBuildingPhase() == false) {
			throw new WeblaboraException("The location at ("+x+","+y+") has a terrain of "+location.getTerrainType()+", but "+newBuilding.getName()+" can only be built on "+newBuilding.getTerrains());
		}
		
		if(newBuilding.isCloister() && !location.isCloisterLinked()) {
			throw new WeblaboraException("The location at ("+x+","+y+") is not a neighbor to a cloister erection, but "+newBuilding.getName()+" is a cloister erection");
		}
		
		if(player.canAffordCost(newBuilding.getBuildCost()) == false) {
			throw new WeblaboraException("Player does not have "+newBuilding.getBuildCost()+" necessary to build "+newBuilding.getName());
		}
		
		player.subtractAll(newBuilding.getBuildCost());
		location.setErection(newBuilding);
		
		//call the building's hook, in case it needs to do something. i think this is just the winery.
		newBuilding.build(board);
	}
}
