package com.philihp.weblabora.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Runs through game 53955 and checks ending board.
 */
public class ConfigureGameTest {

  Board board;

  @Before
  public void setUp() throws Exception {
    board = new Board();
  }

  @Test
  public void testExceptionThrownIfNoStart() throws WeblaboraException {
    MoveProcessor.processMove(board, "config PLAYERS 2");
    MoveProcessor.processMove(board, "config LENGTH SHORT");
    MoveProcessor.processMove(board, "config COUNTRY FRANCE");
    boolean throwsException = false;
    try {
      MoveProcessor.processMove(board, "use LR3 Jo");
    }
    catch(WeblaboraException e) {
      throwsException = true;
    }
    assertTrue(throwsException);
  }

  @Test
  public void testNoExceptionThrownIfStart() throws WeblaboraException {
    MoveProcessor.processMove(board, "config PLAYERS 2");
    MoveProcessor.processMove(board, "config LENGTH SHORT");
    MoveProcessor.processMove(board, "config COUNTRY FRANCE");
    MoveProcessor.processMove(board, "start");
    MoveProcessor.processMove(board, "use LR3 Jo");
  }
}
