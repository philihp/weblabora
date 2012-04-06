package com.philihp.weblabora.util;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.philihp.weblabora.jpa.Config;

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

		EntityManager em = null;
		try {
			EntityManagerFactory emf = (EntityManagerFactory) sce
					.getServletContext().getAttribute("emf");
			em = emf.createEntityManager();
			em.getTransaction().begin();
			
			for(Config config : em.createNamedQuery("getAllConfigs", Config.class).getResultList()) {
				String name = config.getName();
				String value = config.getValue();
				sce.getServletContext().setAttribute(name, value);
				System.out.println("Loaded Config: ["+ name + "] => ["+value+"]");
			}
			
			em.getTransaction().commit();
		} finally {
			if (em != null)
				em.close();
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
	}

}
