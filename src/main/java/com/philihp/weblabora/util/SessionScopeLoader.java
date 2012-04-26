package com.philihp.weblabora.util;

import javax.persistence.EntityManager;
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
			User user = BaseAction.findUser(offlineId);
			se.getSession().setAttribute("user", user);
		}
    }

	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent se) {
        // TODO Auto-generated method stub
    }
	
}
