package com.test.demoapp.dao;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import com.test.demoapp.model.Employee;

public class EmployeeGatewayDaoTest {

	private EmployeeGatewayDao employeeGatewayDao;

	//these test cases invoke tables data from database for testing purpose

	//and all the following test cases were executed individually and passed

	//also as we will be executing case tests with the real time database one's write (delete or update)
	//operations affect the others' read (select) operations

	@Before
	public void setUp() throws Exception {
		employeeGatewayDao = new EmployeeGatewayDao();
	}

	//Testing for validating users
	@Test
	public void testCheckValidUserPositive() {
		assertTrue(employeeGatewayDao.checkValidUser("tilak", "tilak123"));
	}

	@Test
	public void testCheckValidUserNegative() {
		assertFalse(employeeGatewayDao.checkValidUser("anmol", "anmol"));
	}

	//Testing for inserting new employee record
	//for employee record insertion and updation, value of age and salary as '0' (Zero) is cosidered invalid
	@Test
	public void testUpdate() {
		Employee employeeF = new Employee();
		employeeF.setId(3);
		employeeF.setName("Bikram Balami");
		employeeF.setEmail("bikram.karki@hotmail.com");
		employeeF.setGender("Male");
		employeeF.setAge(10);
		employeeF.setSalary(9800);
		assertTrue(employeeGatewayDao.save(employeeF));
	}

	//commented during packaging
	
	/*@Test
	public void testInsert() {
		Employee employeeS = new Employee();
		employeeS.setName("Ramaaaaaaaaaaaaaaa Karki");
		employeeS.setEmail("rama.Karki@gmail.com");
		employeeS.setGender("Female");
		employeeS.setAge(10);
		employeeS.setSalary(1770);
		assertTrue(employeeGatewayDao.save(employeeS));
	}*/

	//Test for retrieving employee details based on employee id provided
	@Test
	public void testFindByIdFirst() {
		List<Employee> list = employeeGatewayDao.findById(2L);
		assertEquals("Anmol Karki", list.get(0).getName());
	}

	@Test
	public void testFindByIdSecond() {
		List<Employee> list = employeeGatewayDao.findById(2L);
		assertEquals(22, list.get(0).getAge());
	}

	//Test for deleting employee record from table for given employee id
	//commented during packaging
	/*
	 * @Test public void testDeleteFirst() {
	 * assertTrue(employeeGatewayDao.delete(31L)); }
	 */

	/*
	 * @Test public void testDeleteSecond() {
	 * assertFalse(employeeGatewayDao.delete(8L)); }
	 */

	//Test for retrieving all the records from employee table
	@Test
	public void testFindAllFirst() {
		List<Employee> list = new ArrayList<Employee>();
		list = employeeGatewayDao.findAll();
		assertEquals("Tilak Basnet", list.get(0).getName());
	}

	@Test
	public void testFindAllSecond() {
		List<Employee> list = new ArrayList<Employee>();
		list = employeeGatewayDao.findAll();
		assertEquals(22, list.get(1).getAge());
	}

	// Test for retrieving the total count of records in employee table
	@Test
	public void testCountFirst() {
		assertEquals(11,employeeGatewayDao.count());
	}

	@Test
	public void testCountSecond() {
		assertEquals(11,employeeGatewayDao.count());
	}

	//Test for retrieving the employee details based on sub query provided

	@Test
	public void testFindByQueryFirst() {
		List<Employee> list = new ArrayList<Employee>();
		list = employeeGatewayDao.find("name like 'Tilak%'");
		assertEquals(1, list.size());		
	}

	@Test
	public void testFindByQuerySecond() {
		List<Employee> list = new ArrayList<Employee>();
		list = employeeGatewayDao.find("age = 10");
		assertEquals(2, list.size());
	}
}
