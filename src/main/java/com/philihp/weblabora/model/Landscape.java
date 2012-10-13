package com.philihp.weblabora.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.google.common.collect.ArrayTable;
import com.google.common.collect.DiscreteDomains;
import com.google.common.collect.Ranges;
import com.google.common.collect.Table;
import com.philihp.weblabora.model.building.Building;
import com.philihp.weblabora.model.building.ClayMound;
import com.philihp.weblabora.model.building.CloisterOffice;
import com.philihp.weblabora.model.building.Erection;
import com.philihp.weblabora.model.building.Farmyard;
import com.philihp.weblabora.model.building.Settlement;

public class Landscape {

	private ArrayTable<Integer, Integer, Terrain> terrain;

	private Player player;

	public Landscape(Player player) {
		this.player = player;

		ClayMound clayMound = ClayMound.make(player.getColor());
		Farmyard farmyard = Farmyard.make(player.getColor());
		CloisterOffice cloisterOffice = CloisterOffice.make(player.getColor());
		
		Set<Integer> rows = Ranges.closed(0, 1).asSet(DiscreteDomains.integers());
		Set<Integer> cols = Ranges.closed(0, 4).asSet(DiscreteDomains.integers());
		this.terrain = ArrayTable.create(rows,cols);
		
		terrainPut(0, 0, TerrainTypeEnum.PLAINS,   TerrainUseEnum.MOOR,    null);
		terrainPut(0, 1, TerrainTypeEnum.PLAINS,   TerrainUseEnum.FOREST,   null);
		terrainPut(0, 2, TerrainTypeEnum.PLAINS,   TerrainUseEnum.FOREST,   null);
		terrainPut(0, 3, TerrainTypeEnum.PLAINS,   TerrainUseEnum.EMPTY,    null);
		terrainPut(0, 4, TerrainTypeEnum.HILLSIDE, TerrainUseEnum.BUILDING, clayMound);
		terrainPut(1, 0, TerrainTypeEnum.PLAINS,   TerrainUseEnum.MOOR,     null);
		terrainPut(1, 1, TerrainTypeEnum.PLAINS,   TerrainUseEnum.FOREST,   null);
		terrainPut(1, 2, TerrainTypeEnum.PLAINS,   TerrainUseEnum.BUILDING, farmyard);
		terrainPut(1, 3, TerrainTypeEnum.PLAINS,   TerrainUseEnum.EMPTY,    null);
		terrainPut(1, 4, TerrainTypeEnum.PLAINS,   TerrainUseEnum.BUILDING, cloisterOffice);
	}
	
	private void terrainPut(int y, int x, TerrainTypeEnum type, TerrainUseEnum use, Erection erection) {
		if ((erection != null) && (use != TerrainUseEnum.BUILDING))
			throw new IllegalArgumentException();
		terrain.put(y, x, new Terrain(this, type, use, erection, x, y));
	}
	
	public Table<Integer, Integer, Terrain> getTerrain() {
		return terrain;
	}
	
	public void setTerrain(ArrayTable<Integer, Integer, Terrain> terrain) {
		this.terrain = terrain;
	}

	public Terrain[][] getTable() {
		return terrain.toArray(Terrain.class);
	}

	private int getNumberOfTerrain(TerrainUseEnum use) {
		if(use == null) return 0;

		int count = 0;
		for(Terrain[] row : getTable()) {
			for(Terrain cell : row) {
				if(cell != null && use == cell.getTerrainUse())
					count++;
			}
		}

		return count;	
	}

	public int getNumberOfForests() {
		return getNumberOfTerrain(TerrainUseEnum.FOREST);
	}
	public int getNumberOfMoors() {
		return getNumberOfTerrain(TerrainUseEnum.MOOR);
	}

	public List<Erection> getErections() {
		List<Erection> list = new ArrayList<Erection>(3);
		for (Terrain[] row : getTable()) {
			for (Terrain cell : row) {
				if(cell != null && cell.getErection() != null) 
					list.add(cell.getErection());
			}
		}
		return list;
	}
	
	public List<Settlement> getSettlements() {
		List<Settlement> list = new ArrayList<Settlement>(8);
		for(Erection erection : getErections()) {
			if(erection instanceof Settlement) {
				list.add((Settlement)erection);
			}
		}
		return list;
	}

	public List<Building> getBuildings() {
		List<Building> list = new ArrayList<Building>(3);
		for (Erection erection : getErections()) {
			if (erection instanceof Building) {
				list.add((Building) erection);
			}
		}
		return list;
	}

	public Terrain getTerrainAt(Coordinate coordinate) {
		if(terrain.contains(coordinate.getY(), coordinate.getX()))
			return terrain.get(coordinate.getY(), coordinate.getX());
		return null;
	}

	public Player getPlayer() {
		return player;
	}

	public void checkValidity() {
		//TODO: check the validity of the arrangement of the landscape. plots need to be touching a district/homeland
		
		//might not be necessary since i did such a swell job of plot and district commands :)
	}
}
