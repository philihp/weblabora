package com.philihp.weblabora.model;

public class CommandStart implements InvalidDuringSettlement, MoveCommand, SafeBeforeStart {

  @Override
  public void execute(Board board, CommandParameters params) throws WeblaboraException {
    board.start();
    board.preMove();

    TurnHistory history = new TurnHistory(board.isSettling(), board.isNeutralBuildingPhase(), board.isGameStarted());
    board.setTurnHistory(history);
  }

}
