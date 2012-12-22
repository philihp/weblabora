package com.philihp.weblabora.action;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.philihp.weblabora.form.GameForm;
import com.philihp.weblabora.jpa.Game;
import com.philihp.weblabora.jpa.Game.Stage;
import com.philihp.weblabora.jpa.User;

public class LeaveGame extends BaseAction {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response, User user) throws Exception {
		
		GameForm form = (GameForm)actionForm; 
		EntityManager em = (EntityManager)request.getAttribute("em");
		
		Game game = em.find(Game.class, form.getGameId());
		
		if(game == null) {
			ActionMessages messages = getMessages(request);
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.invalidGameId", form.getGameId()));
			saveMessages(request.getSession(), messages);
		}
		else if(game.getStage().equals(Stage.FINISHED)) {
			ActionMessages messages = getMessages(request);
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.cantLeave"));
			saveMessages(request.getSession(), messages);
		}
		else {
			for(Game.Player player : game.getAllPlayers()) {
				if(user.equals(player.getUser())) {
					player.setUser(null);
					if(game.getStage() == Game.Stage.IN_PROGRESS)
						game.setStage(Game.Stage.RECRUITING);
				}
			}
		}
		
		return mapping.findForward("root");
	}

}
