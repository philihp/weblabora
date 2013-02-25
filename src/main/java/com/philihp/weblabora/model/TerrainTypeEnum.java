package com.philihp.weblabora.model;

public enum TerrainTypeEnum {
	WATER, COAST, PLAINS, HILLSIDE, MOUNTAIN,

	/**
	 * Used for cells that are merged with its north neighbor cell.
	 * 
	 * For example {@link #MOUNTAIN} cells span for two rows. The bottom (south)
	 * cell of such pair has the {@code MERGED_NORTH} terrain type.
	 */
	MERGED_NORTH;
	
	public String getProperCase() {
		String name = this.name();
		return Character.toUpperCase(name.charAt(0)) + name.substring(1).toLowerCase();
	}
	
	public String getRowspanAttr() {
		if(this.equals(MOUNTAIN)) {
			return " rowspan=\"2\"";
		}
		else {
			return "";
		}
	}

	/**
	 * Determines whether cell with this terrain type is merged to a different
	 * cell while being a slave cell.
	 * 
	 * {@code isMerged} function returns {@code true} for {@link #MERGED_NORTH}
	 * terrain type. It might seem redundant however it is thought off as a
	 * preparation for possible future extensions with horizontal merging of
	 * cells. Thanks to using this function other code will not have to be
	 * corrected once such cases are introduced.
	 * 
	 * Note that {@code isMerged} returns {@code false} for terrain types of the
	 * master cell in the merged range. For example it returns {@code false} for
	 * {@link #MOUNTAIN}.
	 * 
	 * @return {@code true} if cell of this terrain type is merged to a
	 *         different cell while being a slave cell in the merged range.
	 *         {@code false} otherwise.
	 * @see #getMasterCoordinateFrom(Coordinate)
	 * @see #getSlaveCoordinateFrom(Coordinate)
	 */
	public boolean isMerged() {
		return this==MERGED_NORTH;
	}

	/**
	 * Returns coordinates of a master cell for a slave cell with specified
	 * coordinates.
	 * 
	 * If this is not merged type then there is only one cell and the function
	 * returns {@code slave}.
	 * 
	 * @param slave Coordinates of the slave cell.
	 * @return Coordinates of a master cell for a slave cell with specified
	 *         coordinates.
	 * @see #isMerged()
	 * @see #getSlaveCoordinateFrom(Coordinate)
	 */
	public Coordinate getMasterCoordinateFrom(Coordinate slave) {
		switch(this) {
		case MERGED_NORTH:
			return new Coordinate(slave.getX(), slave.getY() - 1);
		default:
			return slave;
		}
	}

	/**
	 * Returns coordinates of a slave cell for a master cell with specified
	 * coordinates.
	 * 
	 * If this is not merged type then there is only one cell and the function
	 * returns {@code master}.
	 * 
	 * @param master Coordinates of the master cell.
	 * @return Coordinates of a slave cell for a master cell with specified
	 *         coordinates.
	 * @see #isMerged()
	 * @see #getMasterCoordinateFrom(Coordinate)
	 */
	public Coordinate getSlaveCoordinateFrom(Coordinate master) {
		switch(this) {
		case MERGED_NORTH:
			return new Coordinate(master.getX(), master.getY() + 1);
		default:
			return master;
		}
	}
}
