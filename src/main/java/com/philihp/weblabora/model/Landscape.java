package com.philihp.weblabora.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.philihp.weblabora.model.Terrain.*;

import com.google.common.collect.ArrayTable;
import com.google.common.collect.Table;

public class Landscape {

	private Table<Integer, Integer, Terrain> terrain;
	
	public Landscape() {
		this.terrain = ArrayTable.create(Arrays.asList(0,1), Arrays.asList(-2,-1,0,1,2,3,4,5,6));
		terrain.put(0,0,PEAT);
		terrain.put(0,1,FOREST);
		terrain.put(0,2,FOREST);
		terrain.put(0,3,PLAINS);
		terrain.put(0,4,PLAINS);
		terrain.put(1,0,PEAT);
		terrain.put(1,1,FOREST);
		terrain.put(1,2,PLAINS);
		terrain.put(1,3,PLAINS);
		terrain.put(1,4,PLAINS);
	}
	
	
	
}
