package com.test.demoapp.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.test.demoapp.model.Salary;
import com.test.demoapp.utility.PersistenceManager;

public class SalaryDaoImpl implements SalaryDao{

	public void saveSalaryInfo(Salary salary) {
		EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
		em.getTransaction().begin();
		em.merge(salary);
		em.getTransaction().commit();
	}

	@SuppressWarnings("unchecked")
	public List<Salary> findAllSalaryInfo() {
		EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
		Query query = em.createQuery("from Salary");
		return query.getResultList();
	}

}
