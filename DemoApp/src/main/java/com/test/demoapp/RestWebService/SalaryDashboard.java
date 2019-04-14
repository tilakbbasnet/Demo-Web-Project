package com.test.demoapp.RestWebService;

import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sun.jersey.spi.resource.Singleton;
import com.test.demoapp.dao.EmployeeGatewayDao;
import com.test.demoapp.dao.SalaryDaoImpl;
import com.test.demoapp.model.Salary;

@Path("/salary")
public class SalaryDashboard {

	@POST
	@Path("/mm")
	public Map<String, List<List<String>>> adminLogin(final Map<String,String> params) {
		System.err.println("inside restful services");
    	EmployeeGatewayDao salaryDao = new EmployeeGatewayDao();
    	System.err.println(params.get("client"));
    	System.err.println(salaryDao.findAllSalaryInfo().size());
    	return salaryDao.findAllSalaryInfo();
	}
	
}
