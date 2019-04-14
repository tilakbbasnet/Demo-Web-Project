package com.test.demoapp.dao;

import java.util.List;
import com.test.demoapp.model.Employeee;

public interface EmployeeDao {
    public List<Employeee> findById(int id);
    
    public List<Employeee> find(String subquery);
     
    public void save(Employeee employee);
     
    public void delete(int id);
 
    public List<Employeee> findAll();
    
    public int count();
}
