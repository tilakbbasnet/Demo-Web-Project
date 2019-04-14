package com.test.demoapp.service;

import java.util.List;
import com.test.demoapp.model.Salary;

public interface SalaryService {
	public void saveSalaryInfo(Salary salary);

	public List<Salary> findAllSalaryInfo();
}
