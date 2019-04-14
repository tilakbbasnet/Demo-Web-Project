package com.test.demoapp.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.test.demoapp.dao.SalaryDaoImpl;
import com.test.demoapp.model.Salary;

@RestController
public class SalaryRestfulController {
	
    @PostMapping("/adminHomee")
    public ModelAndView adminLoginn(Model model) {
    	ModelAndView salaryMV = new ModelAndView("salarydetails");
    	SalaryDaoImpl salaryDao = new SalaryDaoImpl();
    	salaryMV.addObject("listSalary", salaryDao.findAllSalaryInfo());
    	System.err.println(salaryDao.findAllSalaryInfo().size());
    	System.err.println(salaryDao.findAllSalaryInfo().get(0).getBasicSalary());
    	System.err.println(salaryDao.findAllSalaryInfo().get(1).getBasicSalary());
    	return salaryMV;
    }
    
    @PostMapping("/adminHomeeeeee")
    public List<Salary> adminLogin() {
    	SalaryDaoImpl salaryDao = new SalaryDaoImpl();
    	return salaryDao.findAllSalaryInfo();
    }
}
