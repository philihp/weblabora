package com.philihp.weblabora.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Application Lifecycle Listener implementation class ApplicationScopeLoader
 * 
 */
public class ApplicationScopeLoader implements ServletContextListener {

	WheelArt wheelArt = new WheelArt();

	public ApplicationScopeLoader() {
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		sce.getServletContext().setAttribute("wheelArt", wheelArt);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
	}

}
