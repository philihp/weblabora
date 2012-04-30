package com.philihp.weblabora.util;

import javax.persistence.EntityManager;

public class EntityManagerManager {
	/**
	 * It's a magical land where we can naively ignore threading, and do
	 * so with a smug grin on our face because we used ThreadLocal.
	 */
	private static final ThreadLocal<EntityManager>
		MAGICAL_THREAD_SAFE_LAND = new ThreadLocal<EntityManager>();
	
	public static void set(EntityManager em) {
		MAGICAL_THREAD_SAFE_LAND.set(em);
	}
	
	public static EntityManager get() {
		return MAGICAL_THREAD_SAFE_LAND.get();
	}
	
	public static void clear() {
		MAGICAL_THREAD_SAFE_LAND.get().clear();
		MAGICAL_THREAD_SAFE_LAND.remove();
	}
}
