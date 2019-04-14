package com.test.demoapp.service;

import java.util.List;
import com.test.demoapp.model.Employeee;

public interface EmployeeService {
    public List<Employeee> findById(int id);
    
    public List<Employeee> find(String subquery);
     
    public void save(Employeee employee);
     
    public void delete(int id);
 
    public List<Employeee> findAll();
    
    public int count();
}
