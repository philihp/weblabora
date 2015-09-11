package com.philihp.weblabora.model;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Runs through game 53955 and checks ending board.
 */
public class JsonTest {

  Board board;

  @Before
  public void setUp() throws Exception {
    board = new Board();
    // try to marshal json
  }

  @Test
  public void testEndingScores() throws WeblaboraException {
    assertThat(board.isGameOver(), is(false));
  }
}
