package com.glearning.employees.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.glearning.employees.model.*;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	
	List<Employee> findByfirstname(String firstname);

}
	