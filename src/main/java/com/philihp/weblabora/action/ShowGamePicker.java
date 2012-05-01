package com.philihp.weblabora.action;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.philihp.weblabora.form.GameForm;
import com.philihp.weblabora.jpa.Game;
import com.philihp.weblabora.jpa.User;
import com.philihp.weblabora.model.Board;
import com.philihp.weblabora.model.MoveProcessor;
import com.philihp.weblabora.model.WeblaboraException;
import com.philihp.weblabora.util.EntityManagerManager;
import com.philihp.weblabora.util.FacebookCredentials;

public class ShowGamePicker extends BaseAction {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response, User user) throws Exception {

		request.setAttribute("myGames", ShowBoard.findGamesForUser(user));
		
		return mapping.findForward("view");
	}

}
