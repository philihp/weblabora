package com.philihp.weblabora.model;

public class CommandWith implements InvalidDuringSettlement, MoveCommand {

  @Override
  public void execute(Board board, CommandParameters params) throws WeblaboraException {
    if(params.getParams().size() != 1)
      throw new WeblaboraException("WITH command takes 1 parameter, either LAYBROTHER or PRIOR");

    Clergyman.Type type = Clergyman.Type.valueOf(params.getParams().get(0));

    if(type == Clergyman.Type.PRIOR)
      params.setMustBePrior(true);

  }

}
