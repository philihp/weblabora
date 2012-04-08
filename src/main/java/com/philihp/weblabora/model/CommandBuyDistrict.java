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
		public TerrainTypeEnum getType(int i) {
			return types[i];
		}
	};

	@Override
	public void execute(Board board, CommandParameters params)
			throws WeblaboraException {
		
		execute(board,
				Integer.parseInt(params.get(0)),
				Side.valueOf(params.get(1))
				);
	}

	public static void execute(Board board, int y, Side side)
			throws WeblaboraException {
		Player player = board.getPlayer(board.getActivePlayer());
		
		int cost = board.purchaseDistrict();
		if(player.getCoins() < cost) 
			throw new WeblaboraException("Purchase price for a plot is "+cost+", but player "+player.getColor()+" only has "+player.getCoins()+".");
		player.subtractCoins(cost);
		
		Landscape landscape = player.getLandscape();
		
		Table<Integer, Integer, Terrain> oldTerrain = landscape.getTerrain();
		
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
				else {
					newTerrain.put(rowKey, columnKey, new Terrain(landscape, side.getType(columnKey), null));
				}
			}
		}
		
		landscape.setTerrain(newTerrain);
		
	}
}
