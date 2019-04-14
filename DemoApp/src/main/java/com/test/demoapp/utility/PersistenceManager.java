package com.test.demoapp.utility;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public enum PersistenceManager{
	INSTANCE;
	private EntityManagerFactory emFactory;

	private PersistenceManager() {
		emFactory = Persistence.createEntityManagerFactory("jpa-spring-student");
	}

	public  EntityManager getEntityManager() {
		return emFactory.createEntityManager();
	}

	public  void closeEntities() {
		if(emFactory != null) {
			emFactory.close();
		}
	}
}
