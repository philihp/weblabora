package com.philihp.weblabora.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.philihp.weblabora.model.Board;
import com.philihp.weblabora.model.Wheel;

public class PushArm extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		Board board = (Board)request.getSession().getAttribute("board");
		if(board == null) { 
			board = new Board();
			request.getSession().setAttribute("board", board);
		}
		
		board.getWheel().pushArm();
		request.getSession().setAttribute("message", "Arm pushed");
		
		return mapping.findForward("default");
	}

}
