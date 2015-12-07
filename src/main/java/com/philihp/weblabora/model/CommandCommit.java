package com.philihp.weblabora.model;

public class CommandCommit implements MoveCommand {

  @Override
  public void execute(Board board, CommandParameters params) throws WeblaboraException {
    board.postMove();

    TurnHistory history = new TurnHistory(board.isSettling(), board.isNeutralBuildingPhase(), board.isGameStarted());
    board.setTurnHistory(history);

    board.preMove();
  }

}
