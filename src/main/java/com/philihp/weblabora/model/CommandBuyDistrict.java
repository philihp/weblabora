package com.philihp.weblabora.model;

import static com.philihp.weblabora.model.TerrainTypeEnum.*;

import java.util.Set;

import com.google.common.collect.ArrayTable;
import com.google.common.collect.DiscreteDomains;
import com.google.common.collect.Ranges;
import com.google.common.collect.Table;

public class CommandBuyDistrict implements MoveCommand {
	
	public static enum Side {
		HILLS(MOOR, FOREST, FOREST, HILLSIDE, HILLSIDE),
		PLAINS(FOREST, TerrainTypeEnum.PLAINS, TerrainTypeEnum.PLAINS, TerrainTypeEnum.PLAINS, HILLSIDE);
		
		private TerrainTypeEnum[] types;
		
		Side(TerrainTypeEnum... types) {
			this.types = types;
		}
		public TerrainTypeEnum getType(int column) {
			return types[column];
		}
	};

	@Override
	public void execute(Board board, CommandParameters params)
			throws WeblaboraException {
		
		execute(board,
				Integer.parseInt(params.get(0)),
				Side.valueOf(params.get(1)),
				params.getHistory().isNextDistrictFree()
				);
		params.getHistory().setNextDistrictFree(false);
	}

	public static void execute(Board board, int y, Side side, boolean free)
			throws WeblaboraException {
		Player player = board.getPlayer(board.getActivePlayer());
		
		int cost = board.purchaseDistrict();
		if(free) cost = 0;
		if(player.getCoins() < cost) 
			throw new WeblaboraException("Purchase price for a district is "+cost+", but player "+player.getColor()+" only has "+player.getCoins()+".");
		player.subtractCoins(cost);
		
		Landscape landscape = player.getLandscape();
		
		Table<Integer, Integer, Terrain> oldTerrain = landscape.getTerrain();
		
		checkForOverlap(oldTerrain, y);
		checkForConnection(oldTerrain, y);
		
		Set<Integer> oldRows = oldTerrain.rowKeySet();
		Set<Integer> oldColumns = oldTerrain.columnKeySet();
		
		int minRow = y;
		int maxRow = y;
		for(int i : oldRows) {
			if(i < minRow) minRow = i;
			if(i > maxRow) maxRow = i;
		}
		
		Set<Integer> newRows = Ranges.closed(minRow, maxRow).asSet(DiscreteDomains.integers());
		
		ArrayTable<Integer, Integer, Terrain> newTerrain = ArrayTable.create(newRows, oldColumns);
		for(Integer rowKey : newRows) {
			for(Integer columnKey : oldColumns) {
				if(oldTerrain.contains(rowKey, columnKey)) {
					newTerrain.put(rowKey, columnKey, oldTerrain.get(rowKey, columnKey));
				}
			}
		}
		
		for(Integer columnKey : Ranges.closed(0,4).asSet(DiscreteDomains.integers())) {
			newTerrain.put(y, columnKey, new Terrain(landscape, side.getType(columnKey), null, columnKey, y));	
		}
		
		landscape.setTerrain(newTerrain);
		
	}
	private static void checkForConnection(
			Table<Integer, Integer, Terrain> oldTerrain, int y) throws WeblaboraException {
		boolean west = oldTerrain.contains(y,-1)    && oldTerrain.get(y,-1) != null;
		boolean east = oldTerrain.contains(y,5)     && oldTerrain.get(y,5) != null;
		boolean north = oldTerrain.contains(y-1, 0) && oldTerrain.get(y-1,0) != null;
		boolean south = oldTerrain.contains(y+1, 0) && oldTerrain.get(y+1,0) != null;
		if( (north || south || east || west) == false) {
			throw new WeblaboraException("Cannot put a district at "+y+", as it does not connect to the rest of the landscape");
		}
		
	}

	private static void checkForOverlap(
			Table<Integer, Integer, Terrain> oldTerrain, int y) throws WeblaboraException {
		if(oldTerrain.contains(y, 0) && oldTerrain.get(y, 0) != null) {
			throw new WeblaboraException("Cannot put a district at "+y+", as it would overlap");
		}
		
	}
}
