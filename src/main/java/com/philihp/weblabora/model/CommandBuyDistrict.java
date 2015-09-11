package com.philihp.weblabora.model;

import java.util.Set;

import com.google.common.collect.*;

public class CommandBuyDistrict implements MoveCommand {

	public static enum Side {
		HILLS(0),
		PLAINS(1);

		private TerrainTypeEnum[] types;
		private TerrainUseEnum[] uses;

		private Side(int type) {
			switch (type) {
			case 0:
				this.types = new TerrainTypeEnum[] {
					TerrainTypeEnum.PLAINS,
					TerrainTypeEnum.PLAINS,
					TerrainTypeEnum.PLAINS,
					TerrainTypeEnum.HILLSIDE,
					TerrainTypeEnum.HILLSIDE
				};
				this.uses = new TerrainUseEnum[] {
					TerrainUseEnum.MOOR,
					TerrainUseEnum.FOREST,
					TerrainUseEnum.FOREST,
					TerrainUseEnum.EMPTY,
					TerrainUseEnum.EMPTY
				};
				break;
			case 1:
				this.types = new TerrainTypeEnum[] {
					TerrainTypeEnum.PLAINS,
					TerrainTypeEnum.PLAINS,
					TerrainTypeEnum.PLAINS,
					TerrainTypeEnum.PLAINS,
					TerrainTypeEnum.HILLSIDE
				};
				this.uses = new TerrainUseEnum[] {
					TerrainUseEnum.FOREST,
					TerrainUseEnum.EMPTY,
					TerrainUseEnum.EMPTY,
					TerrainUseEnum.EMPTY,
					TerrainUseEnum.EMPTY
				};
				break;
			}
		}

		public TerrainTypeEnum getType(int column) {
			return types[column];
		}

		public TerrainUseEnum getUse(int column) {
			return uses[column];
		}
	};

	@Override
	public void execute(Board board, CommandParameters params)
			throws WeblaboraException {

		String param0 = params.get(0);

		if (param0.equals("")) {
			throw new WeblaboraException("Incorrect number of parameters for command D.");
		}

		execute(board,
				Integer.parseInt(param0),
				Side.valueOf(params.get(1)),
				params.getHistory().isNextDistrictFree()
				);
		params.getHistory().setNextDistrictFree(false);
	}

	public static void execute(Board board, int y, Side side, boolean free)
			throws WeblaboraException {
		Player player = board.getPlayer(board.getActivePlayer());

		int cost = board.getMode().purchaseDistrict();
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

		Set<Integer> newRows = ContiguousSet.create(Range.closed(minRow, maxRow),DiscreteDomain.integers());

		ArrayTable<Integer, Integer, Terrain> newTerrain = ArrayTable.create(newRows, oldColumns);
		for(Integer rowKey : newRows) {
			for(Integer columnKey : oldColumns) {
				if(oldTerrain.contains(rowKey, columnKey)) {
					newTerrain.put(rowKey, columnKey, oldTerrain.get(rowKey, columnKey));
				}
			}
		}

		for(Integer columnKey : ContiguousSet.create(Range.closed(0,4),DiscreteDomain.integers())) {
			newTerrain.put(y, columnKey, new Terrain(landscape, side.getType(columnKey), side.getUse(columnKey), null, columnKey, y));
		}

		landscape.setTerrain(newTerrain);

	}

	/**
	 * Ensure that the new district connects to the landscape on at least one edge.
	 *
	 * Per the rules, it is possible to have gaps in district and create holes in  your
	 * landscape, so there could be a gap in the districts. This is fine, as long as there are coastal or
	 * mountain landscapes that connect it.
	 *
	 * @param oldTerrain
	 * @param y
	 * @throws WeblaboraException
	 */
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
