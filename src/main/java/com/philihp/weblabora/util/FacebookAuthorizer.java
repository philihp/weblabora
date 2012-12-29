package com.philihp.weblabora.util;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;
import org.apache.struts.chain.contexts.ServletActionContext;
import org.apache.struts.config.ActionConfig;
import org.apache.struts.config.ModuleConfig;

public class FacebookAuthorizer implements Command {

	public boolean execute(Context context) throws Exception {
		return execute((ServletActionContext) context);
	}

	public boolean execute(ServletActionContext saContext) throws Exception {
		ActionConfig actionConfig = saContext.getActionConfig();

		List<String> roles = Arrays.asList(actionConfig.getRoleNames());

		if (!roles.contains("public") && isLoggedIn(saContext) == false) {
			ModuleConfig moduleConfig = saContext.getModuleConfig();
			System.out.println("short circuiting to login");
			actionConfig = moduleConfig.findActionConfig("/authenticate");
			saContext.setActionConfig(actionConfig);
		}
		
		return false;
	}

	@SuppressWarnings("unchecked")
	protected boolean isLoggedIn(ServletActionContext saContext) {
		String accessToken = (String)saContext.getSessionScope().get("accessToken");
		Date expires = (Date)saContext.getSessionScope().get("accessExpires");
		
		if(expires != null && expires.before(new Date())) {
			System.out.println("access token expired");
			saContext.getSessionScope().put("accessToken", null);
			saContext.getSessionScope().put("accessExpires", null);
			return false;
		}
		else {
			return accessToken != null;
		}
	}

}
