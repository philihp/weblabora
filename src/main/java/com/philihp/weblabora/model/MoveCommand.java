package com.philihp.weblabora.model;

import java.util.List;

public interface MoveCommand {

	 public void execute(Board board, CommandParameters params) throws WeblaboraException;

}
