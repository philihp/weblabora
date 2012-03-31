package com.philihp.weblabora.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;

import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;

public final class MoveProcessor {
	private MoveProcessor() {
	}

	public static void processMoves(Board board, String moves)
			throws WeblaboraException {
		board.currentMove++;

		for (String move : moves.split("\\|")) {
			System.out.println("move: " + move);
			processSingleMove(board, move);
		}

		if (board.currentMove % 5 == 1) {
			board.getWheel().pushArm();
			for(Player player : board.getPlayers()) {
				if(player.isClergymenAllPlaced())
					player.resetClergymen();
			}
		}
		
		board.nextActivePlayer();
	}

	public static void processSingleMove(Board board, String move)
			throws WeblaboraException {
		CommandParameters params = new CommandParameters();
		
		String prefix = move.substring(0, move.indexOf('('));
		String inner = move.substring(move.indexOf('(')+1, move.indexOf(')'));
		String suffix = move.substring(move.indexOf(')')+1);
		
		params.setCommand(Character.toUpperCase(prefix.charAt(0)));
		
		Scanner scanner = new Scanner(inner);
		scanner.useDelimiter(",");
		while (scanner.hasNext()) {
			String param = scanner.next();
			params.getParams().add(param);
		}

		params.setSuffix(suffix);
		
		MoveCommand moveCommand = pickCommand(params.getCommand());
		moveCommand.execute(board, params);

	}

	public static MoveCommand pickCommand(char commandChar) throws WeblaboraException {
		switch(commandChar) {
		case 'F':
			return new CommandFellTrees();
		case 'C':
			return new CommandCutPeat();
		case 'B':
			return new CommandBuild();
		case 'U':
			return new CommandUse();
		case 'W':
		case 'K':
			return new CommandWorkorder();
		default:
			throw new WeblaboraException("Unknown Command \""+commandChar+"\"");
		}
	}
}
