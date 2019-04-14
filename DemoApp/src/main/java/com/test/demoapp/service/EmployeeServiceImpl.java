package com.test.demoapp.service;

import java.util.List;

import com.test.demoapp.dao.EmployeeDaoImpl;
import com.test.demoapp.model.Employeee;

public class EmployeeServiceImpl implements EmployeeService{

	EmployeeDaoImpl empDao = new EmployeeDaoImpl();
	public List<Employeee> findById(int id) {
		return empDao.findById(id);
	}

	public List<Employeee> find(String subquery) {
		return empDao.find(subquery);
	}

	public void save(Employeee employee) {
		empDao.save(employee);
	}

	public void delete(int id) {
		empDao.delete(id);
	}

	public List<Employeee> findAll() {
		return empDao.findAll();
	}

	public int count() {
		return empDao.count();
	}
}
