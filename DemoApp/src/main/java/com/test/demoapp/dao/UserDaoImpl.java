package com.test.demoapp.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.demoapp.model.Employeee;
import com.test.demoapp.model.User;
import com.test.demoapp.utility.BCryptEncoder;
import com.test.demoapp.utility.PersistenceManager;

@Service
@Transactional
@SuppressWarnings("unchecked")
public class UserDaoImpl implements UserDao{
	BCryptEncoder bCryptEncoder = new BCryptEncoder();

	public String saveUser(User user) {
		EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
		Query query = em.createQuery("FROM User WHERE USERNAME=:username");
		query.setParameter("username", user.getUserName());
		int userExistence = query.getResultList().size();
		user.setPassword(bCryptEncoder.passwordEncoder(user.getPassword()));
		if(userExistence < 1) {
			em.getTransaction().begin();
			em.persist(user);
			em.getTransaction().commit();
			return "User created";
		}else {
			return "Username already exists. Please use another username !!";
		}
	}

	public String validateUser(String username, String password) {
		// TODO Auto-generated method stub
		EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
		//use Entity class name instead of table name
		Query query = em.createQuery("FROM User WHERE USERNAME=:username");
		query.setParameter("username", username);
		List<User> user = query.getResultList();
		if(user.size() == 0) {
			return "nonexist";
		}
		return bCryptEncoder.passwordMatcher(password,user.get(0).getPassword()) ? "matched" : "unmatched";
	}

	public User getUser(String username) {
		System.err.println("inside get user");
		EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
		Query query = em.createQuery("FROM User WHERE USERNAME=:username");
		query.setParameter("username", username);
		return (User)query.getSingleResult();
	}
}
