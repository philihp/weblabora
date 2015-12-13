package com.philihp.weblabora.model;

public class CommandConfig implements MoveCommand, InvalidDuringSettlement, SafeBeforeStart {

	@Override
	public void execute(Board board, CommandParameters params)
			throws WeblaboraException {
		switch(params.getUpperCased(0)) {
			case "LENGTH":
				board.setLength(GameLength.value(params.getUpperCased(1)));
				break;
			case "PLAYERS":
				board.setPlayers(GamePlayers.value(Integer.parseInt(params.getUpperCased(1))));
				break;
			case "COUNTRY":
				board.setCountry(GameCountry.value(params.getUpperCased(1)));
				break;
			default:
				throw new WeblaboraException("Config's first parameter must be LENGTH, PLAYERS, or COUNTRY");
		}
	}
}
