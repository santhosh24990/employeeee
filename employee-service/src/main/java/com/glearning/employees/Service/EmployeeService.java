package com.glearning.employees.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.glearning.employees.model.Employee;
import com.glearning.employees.model.Role;
import com.glearning.employees.repository.EmployeeRepository;
import com.glearning.employees.repository.RoleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeerepository;
	@Autowired
	private RoleRepository rolerepository;

	public Employee saveEmployee(Employee employee) {
		return this.employeerepository.save(employee);
	}

	public Role saveRole(Role role) {
		return this.rolerepository.save(role);
	}

	public Set<Employee> fetchAllEmployees() {
		return new HashSet<>(this.employeerepository.findAll());
	}

	public Employee fetchEmployeeById(long empId) {
		return this.employeerepository.findById(empId).orElseThrow();
	}

	public void deleteEmployeeById(long empId) {
		this.employeerepository.deleteById(empId);
	}

	// TO SORT BY FIRST NAME ASCENDING
	public List<Employee> sortbyfirstnameasc() {

		return this.employeerepository.findAll(Sort.by("firstname").ascending());

	}

	// TO SORT BY FIRSTNAME DESCENDING
	public List<Employee> sortbyfirstnamedsc() {

		return this.employeerepository.findAll(Sort.by("firstname").descending());

	}

	// GET EMPLOYEES BY FIRSTNAME
	public List<Employee> findbyfirstname(String name) {

		return this.employeerepository.findByfirstname(name);
	}

	public List<Role> findbyname(String roleName) {

		return this.rolerepository.findByroleName(roleName);
	}
}
