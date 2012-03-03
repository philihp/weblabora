package com.philihp.weblabora.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.philihp.weblabora.model.TerrainTypeEnum.*;

import com.google.common.collect.ArrayTable;
import com.google.common.collect.Table;

public class Landscape {

	private Table<Integer, Integer, Terrain> terrain;

	public Landscape() {
		this.terrain = ArrayTable.create(Arrays.asList(-2, -1, 0, 1, 2, 3, 4, 5, 6), Arrays.asList(0, 1));
		terrain.put(0, 0, new Terrain(this, MOOR, null));
		terrain.put(1, 0, new Terrain(this, FOREST, null));
		terrain.put(2, 0, new Terrain(this, FOREST, null));
		terrain.put(3, 0, new Terrain(this, PLAINS, null));
		terrain.put(4, 0, new Terrain(this, PLAINS, null));
		terrain.put(0, 1, new Terrain(this, MOOR, null));
		terrain.put(1, 1, new Terrain(this, FOREST, null));
		terrain.put(2, 1, new Terrain(this, PLAINS, null));
		terrain.put(3, 1, new Terrain(this, PLAINS, null));
		terrain.put(4, 1, new Terrain(this, PLAINS, null));
	}

	public Table<Integer, Integer, Terrain> getTerrain() {
		return terrain;
	}

}
