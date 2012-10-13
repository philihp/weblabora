package com.philihp.weblabora.model;

/**
 * Types of terrain use.
 * 
 * Basically this represents a kind of card that lays on the board spot.
 * 
 * @author Adam Badura
 */
public enum TerrainUseEnum {
	/**
	 * No card lays on the spot.
	 */
	EMPTY,

	/**
	 * Moor card lays on the spot.
	 */
	MOOR,

	/**
	 * Forest card lays on the spot.
	 */
	FOREST,

	/**
	 * Building card lays on the spot.
	 * 
	 * {@link Terrain} class determines which specific building it is.
	 * 
	 * @see Terrain#getErection()
	 */
	BUILDING;

}
