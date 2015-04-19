package com.philihp.weblabora.model;


public final class MoveProcessor {


  private MoveProcessor() {
  }

  public static void processMoves(Board board, Iterable<String> allMoves) throws WeblaboraException {
    for (String move : allMoves) {
      if (move == null) continue; //ignore the first null state.
      processMove(board, move);
    }
  }

  public static void processMove(Board board, String move) throws WeblaboraException {
    if (move == null) return;
    board.preMove(move);
    processActions(board, move);
    board.postMove();
  }

  public static void processActions(Board board, String actions)
      throws WeblaboraException {
    if (board.isGameOver()) return;
    if (actions != null) actions = actions.trim();
    if (actions == null) return;

    boolean neutral = (board.getMode() != null) ? board.getMode().isNeutralBuildingPhase() : false;
    MoveHistory history = new MoveHistory(board.isSettling(), neutral, board.isGameStarted());
    for (String action : actions.split("\\|")) {
      processSingleAction(board, action, history);
    }
  }

  public static void processSingleAction(Board board, String move, MoveHistory history)
      throws WeblaboraException {
    CommandParameters params = new CommandParameters(history);

    String prefix;
    String inner;
    String suffix;
    try {
      prefix = move.substring(0, move.indexOf('('));
      inner = move.substring(move.indexOf('(') + 1, move.indexOf(')'));
      suffix = move.substring(move.indexOf(')') + 1);
    } catch (Exception e) {
      throw new WeblaboraException("Every action must have exactly one '(' and one ')'.");
    }

    params.setCommand(prefix);
    if (!board.isGameStarted() || board.isExtraRound() && board.getMode().isPriorSpecialInExtraRound()) {
      params.setPlaceClergyman(false);
    } else {
      params.setPlaceClergyman(history.isPreviousUse() == false);
    }

    for (String param : inner.split(",", -1)) {
      params.getParams().add(param);
    }

    params.setSuffix(suffix);

    MoveCommand moveCommand = pickCommand(params.getCommand(), history);
    moveCommand.execute(board, params);

    history.setPreviousUse(moveCommand instanceof CommandUse);
    history.setPreviousBuild(moveCommand instanceof CommandBuild);
  }

  private static MoveCommand pickCommand(String commandString, MoveHistory history) throws WeblaboraException {
    MoveCommand command = null;
    switch (commandString.toUpperCase().trim()) {
      case "CONFIG":
        if(history.isStarted())
          throw new WeblaboraException("Unable to configure game once it has begun");
        command = new CommandConfig();
        break;
      case "F":
      case "FELL":
      case "FELLTREES":
        command = new CommandFellTrees();
        break;
      case "C":
      case "CUT":
      case "CUTPEAT":
        command = new CommandCutPeat();
        break;
      case "B":
      case "BUILD":
        command = new CommandBuild();
        break;
      case "U":
      case "USE":
        command = new CommandUse();
        break;
      case "W":
      case "WORK":
      case "WORKORDER":
      case "K":
        command = new CommandWorkorder();
        break;
      case "D":
      case "DISTRICT":
        command = new CommandBuyDistrict();
        break;
      case "P":
      case "PLOT":
        command = new CommandBuyPlot();
        break;
      case "V":
      case "CONVERT":
        command = new CommandConvert();
        break;
      case "S":
      case "SETTLE":
        command = new CommandSettle();
        break;
      default:
        throw new WeblaboraException("Unknown Command \"" + commandString + "\"");
    }

    if (history.isNeutralBuildingPhase() == false && history.isSettling() && (command instanceof InvalidDuringSettlement)) {
      throw new WeblaboraException("Invalid Command \"" + commandString + "\" during settlement.");
    }

    return command;
  }
}
