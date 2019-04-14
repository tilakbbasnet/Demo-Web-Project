package com.test.demoapp.controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.test.demoapp.dao.StudentDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.test.demoapp.dao.EmployeeDaoImpl;
import com.test.demoapp.dao.EmployeeGatewayDao;
import com.test.demoapp.dao.UserDaoImpl;
import com.test.demoapp.model.Employeee;
import com.test.demoapp.utility.BCryptEncoder;
import com.test.demoapp.utility.ExcelGenerator;
import com.test.demoapp.model.User;

@Controller
@SessionAttributes("username")
public class MainController {

	private EmployeeGatewayDao employeeGatewayDao = new EmployeeGatewayDao();
	private EmployeeDaoImpl empDao = new EmployeeDaoImpl();

//  redirecting admin to salary details page via rest web service 
	@PostMapping("/adminHome")
	public String adminLogin(Model model) {
		model.addAttribute("username","Admin");
		return "salarydetails";
	}

	@GetMapping("/")
	public String indexPage(Model model) {
		// adding model to bind new user to sign up
		model.addAttribute("newUser", new User());
		return "index";
	}

	// Redirecting normal user to login page from index page
	@GetMapping("/userLogin")
	public String normalUserLogin() {
		return "userlogin";
	}

	@PostMapping("/home")
	public String mainPage(@RequestParam("userName") String userName, @RequestParam("password") String password,
			@RequestParam(value = "rememberMe", defaultValue = "false") String isChecked, Model model,
			HttpServletResponse response) {
		UserDaoImpl userDao = new UserDaoImpl();
		String loginInfoMessage;
		String returnedDaoMessage = userDao.validateUser(userName, password);
		model.addAttribute("userName", userName);
		model.addAttribute("password", password);
		// checking for valid user
		if (("nonexist").equals(returnedDaoMessage)) {
			loginInfoMessage = "Username does not exist.";
			model.addAttribute("loginInfoMessage", loginInfoMessage);
			return "redirect:/userLogin";
		} else if (("unmatched").equals(returnedDaoMessage)) {
			loginInfoMessage = "Invalid password for very username.";
			model.addAttribute("loginInfoMessage", loginInfoMessage);
			return "redirect:/userLogin";
		} else {
			// setting cookies
			if (("true").equalsIgnoreCase(isChecked)) {
				System.err.println("checked");
				Cookie userCookie = new Cookie("userName", userName);
				Cookie passCookie = new Cookie("password", password);
				userCookie.setMaxAge(120);
				passCookie.setMaxAge(120);
				response.addCookie(userCookie);
				response.addCookie(passCookie);
			}

			// setting session as username variable
			//getting full name of currently logged in user
			User user = userDao.getUser(userName);
			model.addAttribute("username", user.getFirstName()+" "+user.getLastName());
			
			//getting all employee records using hibernate JPA
			List<Employeee> empList;
			empList = empDao.findAll();
			model.addAttribute("employeeList", empList);
			// retrieving total no of employees during home page loading
			int totalEmployee = empDao.count();
			model.addAttribute("totalemployee", Integer.toString(totalEmployee));
			return "mainpage";
		}
	}

	@GetMapping("/deleteemployee")
	public String deleteEmployee(@RequestParam("id") int id, Model model, HttpSession session) {
		if (validateSession(session)) {
			// returning to login page if user session doesnot exist
			return "redirect:/";
		}
		empDao.delete(id);
		model.addAttribute("msg", "Employee deleted successfully !");
		return "redirect:/refreshEmployeeList";
	}

	@PostMapping("/getemployeebyquery")
	public String findByQuery(@RequestParam("subQuery") String subQuery, Model model, HttpSession session) {
		if (validateSession(session)) {
			return "redirect:/";
		}
		List<Employeee> empList;
		empList = empDao.find(subQuery);
		if (empList.size() == 0) {
			model.addAttribute("filtermsg",
					"No record found for given filter or fiter query is syntactically wrong. Please verify.");
		}
		model.addAttribute("employeeList", empList);
		model.addAttribute("totalemployee", empList.size());
		model.addAttribute("filterCond", subQuery);
		return "mainpage";
	}

	@PostMapping("/getemployeebyid")
	public String findEmployeeById(@RequestParam("id") int id, Model model, HttpSession session) {
		if (validateSession(session)) {
			return "redirect:/";
		}
		List<Employeee> empList;
		empList = empDao.findById(id);
		if (empList.size() == 0) {
			model.addAttribute("filtermsg", "Employee doesnot exist for this id.");
		}
		model.addAttribute("employeeList", empList);
		model.addAttribute("totalemployee", empList.size());
		model.addAttribute("filteredId", id);
		return "mainpage";
	}

	// request mapping to open the form for edit operation
	@GetMapping("/editemployee")
	public String editEmployeeInfo(@RequestParam("id") int id, Model model, HttpSession session) {
		if (validateSession(session)) {
			return "redirect:/";
		}
		Employeee updatedEmployee = null;
		List<Employeee> empList = empDao.findById(id);
		Object[] array = empList.toArray();
		for (Object emp : array) {
			updatedEmployee = (Employeee) emp;
		}
		model.addAttribute("updatedEmployee", updatedEmployee);
		return "editemployee";
	}

	// request mapping to update employee info after changes are made
	@PostMapping("/editemployee")
	public String updateEmployeeInfo(@ModelAttribute("updatedEmployee") Employeee emp, Model model) {
		empDao.save(emp);
		model.addAttribute("msg", "Employee Record Updated Successfully !");
		return "redirect:/refreshEmployeeList";
	}

	// request mapping to open form for new employee registration or update existing
	// employee based on the available of emp id info
	@GetMapping("/addemployee")
	public String addEmployee(Model model, HttpSession session) {
		if (validateSession(session)) {
			return "redirect:/";
		}
		model.addAttribute("newEmployee", new Employeee());
		return "addemployee";
	}

	@PostMapping("/addemployee")
	public String saveEmployee(@ModelAttribute("newEmployee") Employeee emp, Model model) {
		//if (employeeGatewayDao.save(emp)) {
		empDao.save(emp);
		model.addAttribute("msg", "Employee Registered Successfully !");
		//} else {
			//model.addAttribute("msg", "Employee Not Registered !");
		//}
		return "redirect:/refreshEmployeeList";
	}

	@GetMapping("/refreshEmployeeList")
	public String refreshEmployeeList(Model model, HttpSession session) {
		if (validateSession(session)) {
			return "redirect:/";
		}
		List<Employeee> empList;
		empList = empDao.findAll();
		model.addAttribute("employeeList", empList);
		int totalEmployee = empDao.count();
		model.addAttribute("totalemployee", Integer.toString(totalEmployee));
		return "mainpage";
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		// destroying current session and logging out
		session.invalidate();
		return "userlogin";
	}

	// Excel generation request
	@GetMapping("/excelGeneration")
	public String excelGeneration() {
		ExcelGenerator excelGenerator = new ExcelGenerator();
		excelGenerator.getExcelOfQuery("");
		return "redirect:/refreshEmployeeList";
	}

	// new user sign up
	@PostMapping("/userSignUp")
	public String userSignUp(@ModelAttribute("newUser") User user, Model model) {
		String infoMsg;
		if (!user.getPassword().equals(user.getConfirmPassword())) {
			infoMsg = "Passwords do not match. Please verify.";
			model.addAttribute("User", user);
		} else {
			UserDaoImpl userDaoImpl = new UserDaoImpl();
			infoMsg = userDaoImpl.saveUser(user);
		}
		model.addAttribute("userSignInfo", infoMsg);
		return "redirect:/";
	}

	public boolean validateSession(HttpSession session) {
		if (session.getAttribute("username") == null) {
			return true;
		}
		return false;
	}
}
