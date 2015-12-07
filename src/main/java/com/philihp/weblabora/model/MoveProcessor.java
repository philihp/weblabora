package com.philihp.weblabora.model;


public final class MoveProcessor {


  private MoveProcessor() {
  }

  public static void processMove(Board board, String move) throws WeblaboraException {
    TurnHistory history = board.getTurnHistory();

    CommandParameters params = new CommandParameters(history);

    String[] args = move.split(" +");

    params.setCommand(args[0]);

    if (!board.isGameStarted() || board.isExtraRound() && board.getMode().isPriorSpecialInExtraRound()) {
      params.setPlaceClergyman(false);
    } else {
      params.setPlaceClergyman(history.isPreviousUse() == false);
    }

    params.setSuffix("");
    for(int i = 1; i < args.length; i++) {
      if(i == args.length-1 && "*".equals(args[i])) {
        params.setSuffix("*");
      }
      else {
        params.getParams().add(args[i]);
      }
    }

    MoveCommand moveCommand = pickCommand(params.getCommand(), history);

    if((board.isGameStarted() == false) && (moveCommand instanceof SafeBeforeStart == false)) {
      throw new WeblaboraException("Game has not started yet. Can only run CONFIG and START commands.");
    }

    moveCommand.execute(board, params);

    history.setPreviousUse(moveCommand instanceof CommandUse);
    history.setPreviousBuild(moveCommand instanceof CommandBuild);
  }

  private static MoveCommand pickCommand(String commandString, TurnHistory history) throws WeblaboraException {
    MoveCommand command;
    switch (commandString.toLowerCase().trim()) {
      case "config":
        if(history.isStarted())
          throw new WeblaboraException("Unable to configure game once it has begun");
        command = new CommandConfig();
        break;
      case "fell_trees":
        command = new CommandFellTrees();
        break;
      case "cut_peat":
        command = new CommandCutPeat();
        break;
      case "build":
        command = new CommandBuild();
        break;
      case "use":
        command = new CommandUse();
        break;
      case "work_contract":
        command = new CommandWorkorder();
        break;
      case "buy_district":
        command = new CommandBuyDistrict();
        break;
      case "buy_plot":
        command = new CommandBuyPlot();
        break;
      case "convert":
        command = new CommandConvert();
        break;
      case "settle":
        command = new CommandSettle();
        break;
      case "start":
        command = new CommandStart();
        break;
      case "commit":
        command = new CommandCommit();
        break;
      case "with":
        command = new CommandWith();
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
