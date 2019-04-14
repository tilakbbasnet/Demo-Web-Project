package com.test.demoapp.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.test.demoapp.model.Employeee;
import com.test.demoapp.utility.PersistenceManager;

@Service
@Repository("sDao")
@Transactional
@SuppressWarnings("unchecked")
public class EmployeeDaoImpl implements EmployeeDao{

	public List<Employeee> findById(int id) {
		EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
		Query query = em.createQuery("FROM Employeee WHERE ID=:id");
		query.setParameter("id", id);
		return query.getResultList();
	}

	public List<Employeee> find(String subquery) {
		EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
		Query query = em.createQuery("FROM Employeee WHERE "+subquery);
		return (List<Employeee>) query.getResultList();
	}

	public void save(Employeee employee) {
		EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
		em.getTransaction().begin();
		em.merge(employee);
		em.getTransaction().commit();
	}

	public void delete(int id) {
		EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
	    em.getTransaction().begin();
	    Query query = em.createQuery("DELETE FROM Employeee WHERE ID=:id");
	    query.setParameter("id", id);
	    query.executeUpdate();
	    em.getTransaction().commit();
	}

	public List<Employeee> findAll() {
		EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
		Query query = em.createQuery("from Employeee");
		return query.getResultList();
	}

	public int count() {
		EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
		Query query = em.createQuery("from Employeee");
		return query.getResultList().size();
	}

}
