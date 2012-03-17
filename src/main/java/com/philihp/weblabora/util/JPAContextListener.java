package com.philihp.weblabora.util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 
 * This class is necessary in order to get JPA to function properly on Tomcat.
 * Since Tomcat is not a true J2EE container, it doesn't do all that black magic
 * necessary for JPA to work its own magic (I guess it would be blue magic
 * because of all the slight of hand involved... J2EE magic must be black magic,
 * because it is ultimately evil, even though done with the best intent).
 * 
 * @author Philihp
 */
public class JPAContextListener implements ServletContextListener {

	private EntityManagerFactory emf;

	public JPAContextListener() {
	}

	public void contextInitialized(ServletContextEvent event) {
		emf = Persistence.createEntityManagerFactory("labora-pu");
		event.getServletContext().setAttribute("emf", emf);
	}

	public void contextDestroyed(ServletContextEvent event) {
		if (emf != null)
			emf.close();
		emf = null;
	}

}
