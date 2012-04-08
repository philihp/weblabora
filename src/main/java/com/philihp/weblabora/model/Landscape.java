package com.philihp.weblabora.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.philihp.weblabora.model.TerrainTypeEnum.*;

import com.google.common.collect.ArrayTable;
import com.google.common.collect.DiscreteDomains;
import com.google.common.collect.Ranges;
import com.google.common.collect.Table;
import com.philihp.weblabora.model.building.Building;
import com.philihp.weblabora.model.building.ClayMound;
import com.philihp.weblabora.model.building.CloisterOffice;
import com.philihp.weblabora.model.building.Erection;
import com.philihp.weblabora.model.building.Farmyard;

public class Landscape {

	private ArrayTable<Integer, Integer, Terrain> terrain;

	private Player player;

	public Landscape(Player player) {
		this.player = player;

		ClayMound clayMound = new ClayMound();
		Farmyard farmyard = new Farmyard();
		CloisterOffice cloisterOffice = new CloisterOffice();


		Set<Integer> rows = Ranges.closed(0, 1).asSet(DiscreteDomains.integers());
		Set<Integer> cols = Ranges.closed(0, 4).asSet(DiscreteDomains.integers());
		this.terrain = ArrayTable.create(rows,cols);
		
		terrain.put(0, 0, new Terrain(this, MOOR, null));
		terrain.put(0, 1, new Terrain(this, FOREST, null));
		terrain.put(0, 2, new Terrain(this, FOREST, null));
		terrain.put(0, 3, new Terrain(this, PLAINS, null));
		terrain.put(0, 4, new Terrain(this, HILLSIDE, clayMound));
		terrain.put(1, 0, new Terrain(this, MOOR, null));
		terrain.put(1, 1, new Terrain(this, FOREST, null));
		terrain.put(1, 2, new Terrain(this, PLAINS, farmyard));
		terrain.put(1, 3, new Terrain(this, PLAINS, null));
		terrain.put(1, 4, new Terrain(this, PLAINS, cloisterOffice));
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

	public List<Erection> getErections() {
		List<Erection> list = new ArrayList<Erection>(3);
		for (Terrain[] row : getTable()) {
			for (Terrain cell : row) {
				list.add(cell.getErection());
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
		return terrain.get(coordinate.getY(), coordinate.getX());
	}

	public Player getPlayer() {
		return player;
	}

	public void checkValidity() {
		//TODO: check the validity of the arrangement of the landscape. plots need to be touching a district/homeland
	}

}
