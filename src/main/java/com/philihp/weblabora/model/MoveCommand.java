package com.philihp.weblabora.model;

import java.util.List;

public interface MoveCommand {

	 public void execute(Board board, List<String> params) throws WeblaboraException;

}
