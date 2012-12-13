package com.philihp.weblabora.action;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.util.MD5Encoder;
import org.apache.commons.codec.binary.Hex;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.taglib.html.Constants;
import org.apache.struts.validator.DynaValidatorForm;

import com.philihp.weblabora.form.LoginForm;
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
			errors.add("currentPassword", new ActionMessage("message.detail", "Current password was incorrect. Password was not changed."));
			saveErrors(request.getSession(), errors);
			
			return mapping.findForward("failure");
		}
		else {
			user.setPassword(newPassword);
			
			ActionMessages messages = getMessages(request);
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.detail", "Password has been changed."));
			saveMessages(request.getSession(), messages);
			
			return mapping.findForward("success");
		}
		
	}
}
