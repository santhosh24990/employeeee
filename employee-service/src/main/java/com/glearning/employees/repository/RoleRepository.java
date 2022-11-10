package com.glearning.employees.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.glearning.employees.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {

	List<Role> findByroleName(String roleName);

}
