package com.philihp.weblabora.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.philihp.weblabora.model.Board;

public class ShowBoard extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		Board board = (Board)request.getSession().getAttribute("board");
		if(board == null) { 
			board = new Board();
			request.getSession().setAttribute("board", board);
		}
		
		request.setAttribute("board", board);
		request.setAttribute("wheel", board.getWheel());
		request.setAttribute("arm", board.getWheel().getArm());
		request.setAttribute("grain", board.getWheel().getGrain());
		request.setAttribute("sheep", board.getWheel().getSheep());
		
		return mapping.findForward("default");
	}

}
