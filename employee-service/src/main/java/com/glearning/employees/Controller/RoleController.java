package com.glearning.employees.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.glearning.employees.Service.EmployeeService;
import com.glearning.employees.model.Role;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/role")
public class RoleController {
	
	@Autowired
	  private EmployeeService employeeservice;
	
	@PostMapping
	public Role SaveRole(@RequestBody Role role) {
		return this.employeeservice.saveRole(role);
	}
	@GetMapping("/roleName/{roleName}")
	public List<Role> getbyname(@PathVariable String roleName)
	{
		return this.employeeservice.findbyname(roleName);
		
	}

}
