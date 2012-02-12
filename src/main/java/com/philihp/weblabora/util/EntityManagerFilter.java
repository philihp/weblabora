package com.philihp.weblabora.util;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class EntityManagerFilter implements Filter {

	private static EntityManagerFactory emf = null;

	public void init(FilterConfig filterConfig) throws ServletException {
		destroy();
		emf = (EntityManagerFactory) filterConfig.getServletContext().getAttribute(
				"emf");
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		EntityManager em = null;
		try {
			em = emf.createEntityManager();
			EntityManagerManager.set(em);
			em.getTransaction().begin();

			/* ******** STUFF HAPPENS ******* */
			chain.doFilter(request, response);
			/* ******** STUFF HAPPENS ******* */
			
			em.getTransaction().commit();
			EntityManagerManager.clear();
		} finally {
			if (em != null)
				em.close();
		}
	}
	

	public void destroy() {
	}

}
