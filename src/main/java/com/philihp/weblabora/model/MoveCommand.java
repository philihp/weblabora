package com.philihp.weblabora.model;


public interface MoveCommand {

	 public void execute(Board board, CommandParameters params) throws WeblaboraException;

}
