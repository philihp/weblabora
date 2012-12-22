package com.philihp.weblabora.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.philihp.weblabora.form.ResetAccountPasswordForm;
import com.philihp.weblabora.jpa.User;
import com.philihp.weblabora.util.UserUtil;

public class EditAccountPassword extends BaseAction {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response, User user)
			throws Exception {

		ResetAccountPasswordForm form = (ResetAccountPasswordForm)actionForm;
		
		String userPassword = user.getPassword();
		String currentPassword = UserUtil.md5(form.getCurrentPassword());
		String newPassword = UserUtil.md5(form.getNewPassword());
		
		if(userPassword != null && userPassword.equals(currentPassword) == false) {
			ActionMessages errors = getErrors(request);
			errors.add("currentPassword", new ActionMessage("errors.passwordIncorrect"));
			saveErrors(request.getSession(), errors);
			
			return mapping.findForward("failure");
		}
		else {
			user.setPassword(newPassword);
			
			ActionMessages messages = getMessages(request);
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.passwordChanged"));
			saveMessages(request.getSession(), messages);
			
			return mapping.findForward("success");
		}
		
	}
}
