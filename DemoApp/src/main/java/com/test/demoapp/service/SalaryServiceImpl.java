package com.test.demoapp.service;

import java.util.List;
import com.test.demoapp.dao.SalaryDaoImpl;
import com.test.demoapp.model.Salary;

public class SalaryServiceImpl implements SalaryService{
	SalaryDaoImpl salaryDao = new SalaryDaoImpl();
	public void saveSalaryInfo(Salary salary) {
		salaryDao.saveSalaryInfo(salary);
	}

	public List<Salary> findAllSalaryInfo() {
		return salaryDao.findAllSalaryInfo();
	}

}
