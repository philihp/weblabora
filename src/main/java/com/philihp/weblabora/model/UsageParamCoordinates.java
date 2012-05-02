package com.philihp.weblabora.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UsageParamCoordinates extends UsageParam {

	private List<Coordinate> coordinates = new ArrayList<Coordinate>();

	public UsageParamCoordinates(String in) throws WeblaboraException {
		super(in);
	}

	public Coordinate getCoordinate() {
		return coordinates.get(0);
	}

	public List<Coordinate> getCoordinates() {
		return Collections.unmodifiableList(coordinates);
	}

	public void pushCoordinate(Coordinate coordinate) {
		coordinates.add(coordinate);
	}

	public void pushCoordinate(int x, int y) {
		pushCoordinate(new Coordinate(x, y));
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder(""); 
		for(Coordinate coordinate : getCoordinates()) {
			builder.append("("+coordinate.getX()+","+coordinate.getY()+")");
		}
		return builder.toString();
	}

}
