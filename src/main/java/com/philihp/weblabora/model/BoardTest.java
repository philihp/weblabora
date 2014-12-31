package com.philihp.weblabora.model;

import org.junit.Before;
import org.junit.Test;

public class BoardTest {
	
	Board board;

	@Before
	public void setUp() throws Exception {
		board = new Board(GamePlayers.THREE, GameLength.LONG, GameCountry.FRANCE);
	}
	

	@Test
	public void test() {
		System.out.println(board);
	}

}
