package com.philihp.weblabora.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.philihp.weblabora.model.Board;

public class TakeGrain extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		Board board = (Board)request.getSession().getAttribute("board");
		if(board == null) { 
			board = new Board();
			request.getSession().setAttribute("board", board);
		}
		
		int got = board.getWheel().getGrain().take();
		request.getSession().setAttribute("message", "Gathered "+got+" grain");
		
		return mapping.findForward("default");
	}

}
