package com.glearning.employees.Controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.glearning.employees.Service.EmployeeService;
import com.glearning.employees.model.Employee;
import com.glearning.employees.model.Role;

import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/employees")
public class EmployeeController {
	
	
	  private final EmployeeService employeeservice;
  
	@PostMapping
	public Employee SaveEmployee(@RequestBody Employee employee) {
		return this.employeeservice.saveEmployee(employee);
	}
	
	@GetMapping
	public Set<Employee> fetchAllEmployee(){
		return this.employeeservice.fetchAllEmployees();
	}
	
	@GetMapping("/{id}")
	public Employee fetchEmployeeById(@PathVariable("id") long empId){
		return this.employeeservice.fetchEmployeeById(empId);
	}
	
	@DeleteMapping("/{id}")
	@ResponseBody
	public void deleteEmployee(@PathVariable("id") long empId) {
		this.employeeservice.deleteEmployeeById(empId);
	}
	@GetMapping("/asc")	
	public List<Employee> sortemployeesbyasc() {
		return this.employeeservice.sortbyfirstnameasc();
	}
		
	@GetMapping("/dsc")
		public List<Employee> sortemployeesbydsc() {
	   return this.employeeservice.sortbyfirstnamedsc();
	}
	
	@GetMapping("firstname/{firstname}")
	public List<Employee> getbyname(@PathVariable String firstname)
	{
		return this.employeeservice.findbyfirstname(firstname);
		
	}


}
