package com.philihp.weblabora.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import com.philihp.weblabora.action.*;
import com.philihp.weblabora.jpa.User;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Application Lifecycle Listener implementation class SessionScopeLoader
 *
 */
public class SessionScopeLoader implements HttpSessionListener {

    /**
     * Default constructor. 
     */
    public SessionScopeLoader() {
    }

	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent se) {
    	String offlineId = (String)se.getSession().getServletContext().getAttribute("offline_id");
    	System.out.println("Session Created, OfflineID = "+offlineId);

		if (offlineId != null) {
			EntityManagerFactory emf = (EntityManagerFactory)se.getSession().getServletContext().getAttribute("emf");
			EntityManager em = emf.createEntityManager();
			User user = BaseAction.findUser(em, offlineId);
			se.getSession().setAttribute("user", user);
			em.close();
			emf.close();
			//TODO: not sure if this is the right thing here, not sure how else to get the em and emf.
		}
    }

	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent se) {
        // TODO Auto-generated method stub
    }
	
}
