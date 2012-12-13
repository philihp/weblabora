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
import org.apache.struts.validator.DynaValidatorForm;

import com.philihp.weblabora.form.AccountForm;
import com.philihp.weblabora.jpa.User;
import com.philihp.weblabora.util.UserUtil;

public class ShowAccount extends BaseAction {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response, User user)
			throws Exception {
		AccountForm form = (AccountForm)actionForm;
		
		form.setEmail(user.getEmail());

		//if the user never set a password (like if they just signed up with facebook
		//then let them just set a password without saying their current password.
		request.setAttribute("ALLOW_HARD_RESET", user.getPassword() == null);
		
		return mapping.findForward("view");
	}
}
