package com.philihp.weblabora.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.philihp.weblabora.model.TerrainTypeEnum.*;

import com.google.common.collect.ArrayTable;
import com.google.common.collect.Table;
import com.philihp.weblabora.model.building.ClayMound;
import com.philihp.weblabora.model.building.CloisterOffice;
import com.philihp.weblabora.model.building.Farmyard;

public class Landscape {

	private ArrayTable<Integer, Integer, Terrain> terrain;

	private Player owner;

	public Landscape(Player owner) {
		this.owner = owner;

		ClayMound clayMound = new ClayMound();
		Farmyard farmyard = new Farmyard();
		CloisterOffice cloisterOffice = new CloisterOffice();

		this.terrain = ArrayTable.create(Arrays.asList(0, 1),
				Arrays.asList(-2, -1, 0, 1, 2, 3, 4, 5, 6));
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

		clayMound.setOwner(this);
		farmyard.setOwner(this);
		cloisterOffice.setOwner(this);
	}

	public Table<Integer, Integer, Terrain> getTerrain() {
		return terrain;
	}

	public Terrain[][] getTable() {
		return terrain.toArray(Terrain.class);
	}
	
	public Terrain getTerrainAt(int x,int y) {
		return terrain.get(y, x);
	}

	public Player getOwner() {
		return owner;
	}

}
