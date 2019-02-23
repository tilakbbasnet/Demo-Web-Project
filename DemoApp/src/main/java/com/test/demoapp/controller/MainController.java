package com.test.demoapp.controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.test.demoapp.dao.EmployeeGatewayDao;
import com.test.demoapp.model.Employee;

@Controller
@SessionAttributes("username")
public class MainController {

	private EmployeeGatewayDao employeeGatewayDao = new EmployeeGatewayDao();

	@GetMapping("/")
	public String login(HttpSession session) {
		if(session.getAttribute("username")!=null) {
			//redirect to mainpage if user session exists i.e, user is currently logging in
			return "redirect:/refreshEmployeeList";
		}
		return "index";
	}
 
	@PostMapping("/home")
	public String mainPage(@RequestParam("username") String username,@RequestParam("password") String password, Model model, HttpServletResponse response) {
		// checking for valid user
		if(employeeGatewayDao.checkValidUser(username, password)){
			List<Employee> empList;
			//setting cookies
			Cookie userCookie = new Cookie("username", "raamu");
			Cookie passCookie = new Cookie("password", password);
			userCookie.setMaxAge(60);
			passCookie.setMaxAge(60);
			response.addCookie(userCookie);
			response.addCookie(passCookie);
						
			model.addAttribute("username", username);
			//retrieving all employee records
			empList = employeeGatewayDao.findAll();
			model.addAttribute("employeeList", empList);
			//retrieving total no of employees during home page loading
			int totalEmployee = employeeGatewayDao.count();
			model.addAttribute("totalemployee", Integer.toString(totalEmployee));
			return "mainpage";
		}else {
			model.addAttribute("loginfailmsg","Invalid username or password");
			return "redirect:/";
		}
	}

	@GetMapping("/deleteemployee")
	public String deleteEmployee(@RequestParam("id") Long id,  Model model, HttpSession session) {
		if(validateSession(session)) {
			//returning to login page if user session doesnot exist
			return "redirect:/";
		}
		if(employeeGatewayDao.delete(id)) {
			model.addAttribute("msg","Student deleted successfully !");
		}else {
			model.addAttribute("msg","Student not deleted");
		}
		return "redirect:/refreshEmployeeList";
	}

	@PostMapping("/getemployeebyquery")
	public String findByQuery(@RequestParam("subQuery") String subQuery, Model model, HttpSession session) {
		if(validateSession(session)) {
			return "redirect:/";
		}
		List<Employee> empList;
		empList = employeeGatewayDao.find(subQuery);
		if(empList.size()==0) {
			model.addAttribute("filtermsg","No record found for given filter or fiter query is syntatically wrong. Please verify.");
		}
		model.addAttribute("employeeList", empList);
		model.addAttribute("totalemployee", empList.size());
		model.addAttribute("filterCond", subQuery);
		return "mainpage";
	}

	@PostMapping("/getemployeebyid")
	public String findEmployeeById(@RequestParam("id") Long id, Model model, HttpSession session) {
		if(validateSession(session)) {
			return "redirect:/";
		}
		List<Employee> empList;
		empList = employeeGatewayDao.findById(id);
		if(empList.size() == 0) {
			model.addAttribute("filtermsg", "Employee doesnot exist for this id.");
		}
		model.addAttribute("employeeList", empList);
		model.addAttribute("totalemployee", empList.size());
		model.addAttribute("filteredId", id);
		return "mainpage";
	}

	//request mapping to open the form for edit operation
	@GetMapping("/editemployee")
	public String editEmployeeInfo(@RequestParam("id") Long id, Model model,  HttpSession session) {
		if(validateSession(session)) {
			return "redirect:/";
		}
		Employee updatedEmployee=null;
		List<Employee> empList = employeeGatewayDao.findById(id);
		Object[] array = empList.toArray();
		for(Object emp : array) {
			updatedEmployee=(Employee) emp;
		}
		model.addAttribute("updatedEmployee", updatedEmployee);
		return "editemployee";
	}

	//request mapping to update student info after changes are made
	@PostMapping("/editemployee")
	public String updateEmployeeInfo(@ModelAttribute("updatedEmployee") Employee emp, Model model) {
		if(employeeGatewayDao.save(emp)) {
			model.addAttribute("msg", "Employee Record Updated Successfully !");
		}else {
			model.addAttribute("msg", "Employee Not Updated, Does very employee id exist ?");
		}
		return "redirect:/refreshEmployeeList";
	}

	//request mapping to open form for new employee registration or update existing employee based on the available of emp id info
	@GetMapping("/addemployee")
	public String addEmployee(Model model, HttpSession session) {
		if(validateSession(session)) {
			return "redirect:/";
		}
		model.addAttribute("newEmployee", new Employee());
		return "addemployee";
	}

	@PostMapping("/addemployee")
	public String saveEmployee(@ModelAttribute("newEmployee") Employee emp, Model model) {
		if(employeeGatewayDao.save(emp)) {
			model.addAttribute("msg", "Employee Registered Successfully !");
		}else {
			model.addAttribute("msg", "Employee Not Registered !");
		}
		return "redirect:/refreshEmployeeList";
	}

	@GetMapping("/refreshEmployeeList")
	public String refreshEmployeeList(Model model, HttpSession session) {
		if(validateSession(session)) {
			return "redirect:/";
		}
		List<Employee> empList;
		empList = employeeGatewayDao.findAll();
		model.addAttribute("employeeList", empList);
		int totalEmployee = employeeGatewayDao.count();
		model.addAttribute("totalemployee", Integer.toString(totalEmployee));
		return "mainpage";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		//destroying current session and logging out
		session.invalidate();
		return "index";
	}
	
	public boolean validateSession(HttpSession session) {
	if(session.getAttribute("username")==null) {
		return true;
	}
	return false;
	}
}
