package com.test.demoapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "SALARY")
@Entity
public class Salary {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable=false)
    private int id;
	
	@Column(name = "EMPLOYEEID", nullable=false)
    private int employeeId;

    @Column(name = "BASICSALARY")
    private int basicSalary;

    public Salary() {}
    
    public Salary(int basicSalary) {
    	this.basicSalary = basicSalary;
    }

	public int getBasicSalary() {
		return basicSalary;
	}

	public void setBasicSalary(int basicSalary) {
		this.basicSalary = basicSalary;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
}
