package com.test.demoapp.dao;

import java.util.List;
import com.test.demoapp.model.Salary;

public interface SalaryDao {
	public void saveSalaryInfo(Salary salary);

	public List<Salary> findAllSalaryInfo();
}
