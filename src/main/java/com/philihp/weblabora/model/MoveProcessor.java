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

import com.philihp.weblabora.jpa.State;

public final class MoveProcessor {


	private MoveProcessor() {
	}
	
	public static void processMoves(Board board, Iterable<State> allMoves, Integer endStateId) throws WeblaboraException {
		boolean breakNextIteration = false;
		for(State state : allMoves) {
			if(breakNextIteration) {
				board.setNextState(state);
				break;
			}
			System.out.println("State: "+state.getStateId());
			board.preMove(state);
			processActions(board,state.getToken());
			board.postMove();
			if(endStateId != null && state.getStateId() == endStateId) {
				breakNextIteration = true;
			}
		}
	}

	public static void processActions(Board board, String actions)
			throws WeblaboraException {
		if(board.isGameOver()) return;
		if(actions != null) actions = actions.trim();
		if(actions == null) return;
		
		MoveHistory history = new MoveHistory(board.isSettling());
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
			inner = move.substring(move.indexOf('(')+1, move.indexOf(')'));
			suffix = move.substring(move.indexOf(')')+1);
		}
		catch(Exception e) {
			throw new WeblaboraException("Every action must have exactly one '(' and one ')'.");
		}
		
		params.setCommand(Character.toUpperCase(prefix.charAt(0)));
		if(board.isExtraRound()) {
			params.setPlaceClergyman(false);
		}
		else {
			params.setPlaceClergyman(history.isPreviousUse() == false);
		}
		
		for(String param : inner.split(",",-1)) {
			params.getParams().add(param);
		}

		params.setSuffix(suffix);
		
		MoveCommand moveCommand = pickCommand(params.getCommand(), history);
		moveCommand.execute(board, params);
		
		history.setPreviousUse(moveCommand instanceof CommandUse);
		history.setPreviousBuild(moveCommand instanceof CommandBuild);
	}

	public static MoveCommand pickCommand(char commandChar, MoveHistory history) throws WeblaboraException {
		MoveCommand command = null;
		switch(commandChar) {
		case 'F':
			command = new CommandFellTrees();
			break;
		case 'C':
			command = new CommandCutPeat();
			break;
		case 'B':
			command = new CommandBuild();
			break;
		case 'U':
			command = new CommandUse();
			break;
		case 'W':
		case 'K':
			command = new CommandWorkorder();
			break;
		case 'D':
			command = new CommandBuyDistrict();
			break;
		case 'P':
			command = new CommandBuyPlot();
			break;
		case 'V':
			command = new CommandConvert();
			break;
		case 'S':
			command = new CommandSettle();
			break;
		default:
			throw new WeblaboraException("Unknown Command \""+commandChar+"\"");
		}
		
		if(history.isSettling() && (command instanceof InvalidDuringSettlement)) {
			throw new WeblaboraException("Invalid Command \""+commandChar+"\" during settlement.");
		}
		
		return command;
	}
}
